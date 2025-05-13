package com.egeo.components.pay.controller.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.pay.business.PayCoreManage;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.vo.AliCallBackVo;
import com.egeo.components.pay.vo.StandardOrderQueryResultVO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.entity.CacheUser;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.ReflectUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.pay.PayUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/pay/core")
public class PayCoreAction extends BaseSpringController {

	@Resource(name = "payCore")
	private PayCoreManage payCoreManage;
	private static String PAY_LOCK_VALUE = "order_pay";

	@Autowired
	private JedisUtil jedisUtil;
	@Autowired
	private PayUtil payUtil;
	/**
	 * 微信H5支付签名 参考文档:
	 * https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_20&index=1
	 *
	 * @param orderId
	 * @param req
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = "h5wxPaySign", method = RequestMethod.POST)
	public JsonResult<Map<String, Object>> h5paySign(String orderCode, HttpServletRequest req) {
		if(EmptyUtil.isBlank(orderCode)) {
			return fail("请选择订单");
		}
		JsonResult<StandardOrderQueryResultVO> order=payCoreManage.standardOrderQuery(orderCode);
		if(order.getCode()!=0) {
			return fail(order.getCode(),order.getError());
		}
		//ip的获取:先获取nginx请求头中的真实ip
		String userIp=req.getHeader("X-Real-IP");
		if(EmptyUtil.isBlank(userIp) || "unknown".equalsIgnoreCase(userIp)) {
			//如果为空,在使用getRemoteAddr获取ip(可能是局域网ip)
			userIp=req.getRemoteAddr();
		}
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);


		return payCoreManage.h5WXPaySign(order.getData(), userIp,platformId);
	}*/

	/**
	 * 阿里h5支付页面生成
	 *
	 * @param orderCode
	 * @param httpRequest
	 * @param httpResponse
	 *            参考文档: https://docs.open.alipay.com/203/105285/#%23fauny
	 */
	@RequestMapping(value = "h5aliPayForm", method = RequestMethod.POST)
	public void h5aliPayForm(String orderCode, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String result = payCoreManage.genAliPayHtml(orderCode);
		httpResponse.setContentType("Content-type:text/html;charset=" + payUtil.getProperty("ali.request.charset"));
		httpResponse.setHeader("Content-type:text/html;charset=utf-8", result);
		PrintWriter writer = null;
		try {
			writer = httpResponse.getWriter();
			writer.write(result);// 直接将完整的表单html输出到页面
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	//*********************************************
	//原生支付以及新的支付流程逻辑

	/**
	 * 支付发起页面的标准订单查询接口
	 * @param orderCode
	 * @param useFubi
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "standardOrderQueryAtPayInvoke", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> standardOrderQueryAtPayInvoke(String orderCode,Integer payType,HttpServletRequest req){
		logger.info("[支付发起接口,invoke]orderCode="+orderCode);
		String clientId = req.getHeader("clientId");
		//同一笔订单只能进行一条支付,在此处上锁,积分支付在结束后解锁,现金支付,加入等待队列后解锁
		boolean lock = true;
		try {
			lock = jedisUtil.lockWithParam(JedisUtil.PAY_LOCK_KEY_PRE+orderCode,clientId,JedisUtil.ORDER_PAY_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("获取锁异常");
			jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
			e.printStackTrace();
		}
		if(lock){
			JsonResult<Map<String, Object>> result = payCoreManage.standardOrderQueryAtPayInvoke(clientId,orderCode,payType, req);
			if(BusinessExceptionConstant.ORDER_IN_QUEUE==result.getCode()){
				//等待队列时解锁
				jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
				logger.info("等待队列解锁成功");
			}
			return result;
		}else{
			logger.info("该订单正在支付中，请确认是否重新支付,code="+BusinessExceptionConstant.PAY_LOCKED);
			return fail(BusinessExceptionConstant.PAY_LOCKED,"该订单正在支付中，请确认是否重新支付");
		}
	}

	/**
	 * 支付方式选择页面的标准订单查询接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "standardOrderQueryAtPayMethod", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> standardOrderQueryAtPayMethod(String orderCode,HttpServletRequest req){

		/*//校验当前操作是否存在锁
		//获取当前操作客户端
		String clientId = req.getHeader("clientId");
		String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		if(EmptyUtil.isEmpty(keyValue)||!keyValue.equals(clientId)){
			logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
			return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
		}*/

		/*//同一笔订单只能进行一条支付,在此处上锁,积分支付在结束后解锁,现金支付,加入等待队列后解锁
		boolean lock = false;
		try {
			lock = jedisUtil.lockWithParam(JedisUtil.PAY_LOCK_KEY_PRE+orderCode,PAY_LOCK_VALUE,JedisUtil.ORDER_PAY_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("获取锁异常");
			jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
			e.printStackTrace();
		}
		if(lock){*/
		JsonResult<Map<String, Object>> result = payCoreManage.standardOrderQueryAtPayMethod(orderCode);
		return result;
		/*}else{
			return fail("当前订单在其他客户端有正在支付的行为,请继续完成");
		}*/
	}

	/**
	 * 原生支付宝签名接口
	 * 参考:https://docs.open.alipay.com/api_1/alipay.trade.app.pay/
	 * @param orderCode
	 * @param signPlatform 签名平台 1:原生 2:h5 3:公众号 4:扫码支付 5:电脑网站 默认1
	 * @param httpRequest
	 * @param httpResponse
	 */
	@ResponseBody
	@RequestMapping(value = "nativeAlipaySign", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> nativeAlipaySign(String orderCode,Integer signPlatform,HttpServletRequest req,HttpServletResponse rse){
		//校验锁是否还存在
		String clientId = req.getHeader("clientId");
		String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		if(EmptyUtil.isNotEmpty(keyValue)&&!keyValue.equals(clientId)){
			logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
			return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
		}




		if(EmptyUtil.isBlank(orderCode)) {
			return fail("请选择订单");
		}
		//赋默认值
		if(EmptyUtil.isEmpty(signPlatform)){
			signPlatform=1;
		}
		JsonResult<StandardOrderQueryResultVO> order=payCoreManage.standardOrderQuery(orderCode);
		if(order.getCode()!=0) {
			return fail(order.getCode(),order.getError());
		}


		//支付宝签名生成
		return payCoreManage.nativeAlipaySign(order.getData(),signPlatform,req,rse);
	}

	/**
	 * 支付宝扫码签名接口(此功能暂不开放)
	 * 参考:https://docs.open.alipay.com/api_1/alipay.trade.precreate/
	 * @param orderCode
	 * @param httpRequest
	 * @param httpResponse
	 */
	@ResponseBody
	@RequestMapping(value = "scanCodeAlipaySign", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> scanCodeAlipaySign(String orderCode){
		if(EmptyUtil.isBlank(orderCode)) {
			return fail("请选择订单");
		}
		JsonResult<StandardOrderQueryResultVO> order=payCoreManage.standardOrderQuery(orderCode);
		if(order.getCode()!=0) {
			return fail(order.getCode(),order.getError());
		}
		//支付宝扫码签名接口
		return payCoreManage.scanCodeAlipaySign(order.getData());
	}

	/**
	 * 标准订单支付取消接口
	 * 删除队列取消冻结调用取消接口
	 * @param orderCode
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "standardPayCancle", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> standardPayCancle(Integer cancelType,String orderCode,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id
		if(EmptyUtil.isEmpty(cancelType)||cancelType!=1){
			//校验当前操作是否存在锁
			//获取当前操作客户端
			String clientId = req.getHeader("clientId");
			String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
			if(EmptyUtil.isNotEmpty(keyValue)&&!keyValue.equals(clientId)){
				logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
				return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
			}
		}

		return payCoreManage.standardPayCancle(orderCode,userId);
	}

	/**
	 * 取消现金支付并保留积分支付
	 * @param orderCode
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cashPayCancel", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> cashPayCancel(String orderCode,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id
		return payCoreManage.cashPayCancel(orderCode,userId);
	}

	/**
	 * 支付宝回调
	 *
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "aliPayCall")
	public void aliPayCall(HttpServletRequest req, HttpServletResponse res) {
		// 获取所有请求参数
		//Map<String, String> params = RequestUtil.getRequestParams(req);
		//System.out.println(params);
		//logger.info("支付宝支付回调日志:" + params);

		/*
		 * 这段代码参考https://docs.open.alipay.com/54/106370/
		 */
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = req.getParameterMap();
		logger.debug("支付宝回调");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		logger.debug(params.toString());
		//*********************************

		// 支付宝支付成功日志记录
		AliCallBackVo log_ = null;
		String orderCode = null;
		try {
			log_ = ReflectUtil.map2JavaBean(params, AliCallBackVo.class, true);
			orderCode = log_.getOutTradeNo();
		} catch (InstantiationException | IllegalAccessException | SecurityException e) {
		}
		SoDTO oldSoDTO = EmptyUtil.isNotBlank(orderCode) ? payCoreManage.queryOrderByOrderCode(orderCode) : null;

		boolean orderConfirm = payCoreManage.handleAliPayCallBack(params, req);
		try {
			if (orderConfirm) {

				if (oldSoDTO.getOrderPayStatus() == 0) {
					// 订单初始状态为未付款
					SoDTO newSoDTO = payCoreManage.queryOrderByOrderCode(orderCode);
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("PayCoreAction_aliPayCall");
					log.setMsgId(LogConstant.ORDER_PAY_SUCCESS.getStatus());
					log.setType(LogTypeConstant.SO.getStatus());
					log.setOperatorObjId(newSoDTO.getId());
					log.setOperatorObjCode(newSoDTO.getOrderCode());
					log.setNewObj(newSoDTO);
					log.setOldObj(oldSoDTO);
					log.setOperatorId(newSoDTO.getUserId());
					UserDTO userDTO = payCoreManage.queryUserById(newSoDTO.getUserId());
					log.setOperatorName(userDTO != null ? userDTO.getLoginName() : null);

					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}

				res.getWriter().write("success");
			} else {
				res.getWriter().write("failure");
			}
		} catch (Exception e) {
			logger.error("aliPayCall",e);
			try {
				res.getWriter().write("failure");
			} catch (IOException e1) {
				logger.error("aliPayCall",e1);
			}
		}
	}

	/**
	 * 微信原生支付签名
	 * @param orderCode
	 * @param signPlatform 签名平台 1:原生 2:h5 3:公众号 4:扫码支付 5:电脑网站 默认1
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "nativeWXpaySign", method = RequestMethod.POST)
	public JsonResult<Map<String,Object>> nativeWXpaySign(String orderCode,Integer signPlatform, HttpServletRequest req, HttpServletResponse rse){
		//校验锁是否存在
		String clientId = req.getHeader("clientId");
		String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		if(EmptyUtil.isNotEmpty(keyValue)&&!keyValue.equals(clientId)){
			logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
			return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
		}


		String weiXinOpenId_ = req.getHeader("weiXinOpenId");
		//赋默认值
		if(EmptyUtil.isEmpty(signPlatform)){
			signPlatform=1;
		}
		if(signPlatform == 3){
			if (EmptyUtil.isEmpty(weiXinOpenId_)){
				logger.info("weiXinOpenId为空");
				return fail("weiXinOpenId为空");
			}
		}
		//如果是企业微信端的微信支付修改支付plat为5
		if(clientId.equals("4")){
			logger.info("企业微信支付");
			if (EmptyUtil.isEmpty(weiXinOpenId_)){
				logger.info("weiXinOpenId为空");
				return fail("weiXinOpenId为空");
			}
			signPlatform = 5;

		}
		if(EmptyUtil.isBlank(orderCode)) {
			return fail("请选择订单");
		}
		JsonResult<StandardOrderQueryResultVO> order=payCoreManage.standardOrderQuery(orderCode);
		if(order.getCode()!=0) {
			return fail(order.getCode(),order.getError());
		}
		//ip的获取:先获取nginx请求头中的真实ip
		String userIp=req.getHeader("X-Real-IP");
		if(EmptyUtil.isBlank(userIp) || "unknown".equalsIgnoreCase(userIp)) {
			//如果为空,在使用getRemoteAddr获取ip(可能是局域网ip)
			userIp=req.getRemoteAddr();
		}
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		//签名生成
		return payCoreManage.nativeWXpaySign(clientId,order.getData(),userIp,platformId,signPlatform,weiXinOpenId_,req,rse);
	}

	/**
	 * 微信支付回调
	 *
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "wxPayCall")
	public void wxPayCall(HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("utf-8");
			InputStream ins = req.getInputStream();
			String xmlStr = payUtil.byteToString(ins);
			logger.info("微信回调");
			logger.info(xmlStr);

			// 微信支付成功日志记录
			PayWeixinLogDTO wxLog = null;
			Map<String,String> responseParam=null;
			String orderCode = null;
			try {
				responseParam = payUtil.parseResponseXml2Map(xmlStr);
				wxLog = ReflectUtil.map2JavaBean(responseParam, PayWeixinLogDTO.class, true);
				orderCode = wxLog.getOutTradeNo().substring(0, wxLog.getOutTradeNo().length()-3);
			} catch (Exception e) {
			}
			SoDTO oldSoDTO = EmptyUtil.isNotBlank(orderCode) ? payCoreManage.queryOrderByOrderCode(orderCode) : null;

			boolean orderConfirm = payCoreManage.handleWxPayCallBack(xmlStr, req);
			// 商户处理后同步返回给微信参数
			if (orderConfirm) {
				if (oldSoDTO.getOrderPayStatus() == 0) {
					// 订单初始状态为未付款
					SoDTO newSoDTO = payCoreManage.queryOrderByOrderCode(orderCode);
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("PayCoreAction_wxPayCall");
					log.setMsgId(LogConstant.ORDER_PAY_SUCCESS.getStatus());
					log.setType(LogTypeConstant.SO.getStatus());
					log.setOperatorObjId(newSoDTO.getId());
					log.setOperatorObjCode(newSoDTO.getOrderCode());
					log.setNewObj(newSoDTO);
					log.setOldObj(oldSoDTO);
					log.setOperatorId(newSoDTO.getUserId());
					UserDTO userDTO = payCoreManage.queryUserById(newSoDTO.getUserId());
					log.setOperatorName(userDTO != null ? userDTO.getLoginName() : null);

					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}

				res.getWriter().write(
						"<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			}else {
				res.getWriter().write(
						"<xml><return_code><![CDATA[FAIL]]></return_code></xml>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("wxPayCallError",e.getMessage());
			return;
		}
	}


	@RequestMapping("/removeOrderPayLock")
	@ResponseBody
	public JsonResult removeOrderPayLock(String orderCode,HttpServletRequest req){
		logger.info("[积分支付解锁]orderCode="+orderCode);
		//校验锁是否存在
		String clientId = req.getHeader("clientId");
		String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		if(EmptyUtil.isNotEmpty(keyValue)&&!keyValue.equals(clientId)){
			logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
			return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
		}
		//进行解锁
		jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		logger.info("[积分支付解锁结果]");
		return JsonResult.success("解锁成功");
	}



}
