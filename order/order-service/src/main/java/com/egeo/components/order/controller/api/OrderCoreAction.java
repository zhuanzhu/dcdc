package com.egeo.components.order.controller.api;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.bean.CreateOrderHelper;
import com.egeo.components.order.business.*;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.vo.*;
import com.egeo.components.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.scheduler.SoShedulerFacade;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/order/core")
public class OrderCoreAction extends BaseSpringController {
	@Resource(name = "so")
	private SoManage soManage;

	@Resource(name = "soItem")
	private SoItemManage soItemManage;

	@Resource(name = "soInvoice")
	private SoInvoiceManage invoiceManage;
	@Resource
	private SoShedulerFacade soShedulerFacade;

	@Autowired
	private JedisUtil jedisUtil;
	private String CANCEL_LOCK_VALUE = "order_cancel";
	private String COUPON_UNIT_LOCK_VALUE = "coupon_unit";
	@Autowired
	private JdUtils jdUtils;

	@Autowired
	private PushOrderManage pushOrderManage;

	@Resource
	private SoChildReadService soChildReadService;

	@RequestMapping(value = "/testmail", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> testmail(Integer type) {
		if(type.intValue()==1) {
			soShedulerFacade.checkJdAccount(jedisUtil);
		}
		return JsonResult.success();
	}

	/**
	 * 商品确认
	 *
	 * @param type
	 *            0:直接购买 1:购物车下单
	 * @param cartItemIds
	 * @param num
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderConfirm", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> orderConfirm(Long storeId,Integer type, String cartItemIds, Long puId, Integer num,
			Long addrId, String phone, Integer couponType, Long couponUnitId,String channelProductId,Integer source, HttpServletRequest req) {
		logger.info("商品确认");
		logger.info("参数storeId:"+storeId);
		logger.info("参数type:"+type);
		logger.info("参数cartItemIds:"+cartItemIds);
		logger.info("参数puId"+puId);
		logger.info("参数couponType"+couponType);
		logger.info("参数couponUnitId"+couponUnitId);
		logger.info("参数channelProductId"+channelProductId);
		logger.info("参数source"+source);
		logger.info("参数addrId"+addrId);
		logger.info("参数phone"+phone);

		if ((EmptyUtil.isNotEmpty(couponType) && EmptyUtil.isEmpty(couponUnitId))
				|| (EmptyUtil.isEmpty(couponType) && EmptyUtil.isNotEmpty(couponUnitId))) {
			return JsonResult.fail("优惠卷类型和优惠卷unit的id参数错误");
		}
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		Long companyId = userCache.getCompanyId(); // 公司id
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		if(EmptyUtil.isEmpty(storeId)){
			if(platformId==7){
				logger.info("大厨管家订单");
				storeId=1L;
			}
			if(platformId==2){
				logger.info("富宏云采订单");
				storeId = 2L;
			}
		}
		if(EmptyUtil.isEmpty(storeId)){
			return JsonResult.fail("门店id不能为空");
		}
		String clientIdStr = req.getHeader("clientId");

		if(EmptyUtil.isEmpty(clientIdStr)) {
			return JsonResult.fail("clientId 参数错误");
		}
		Long clientId = Long.valueOf(clientIdStr);

		return soManage.orderConfirmNew(storeId,type, cartItemIds, puId, num, addrId, userId, platformId, companyId, phone,
				couponType, couponUnitId,clientId,channelProductId,source);
	}

	@RequestMapping(value = "/queryOrderPayInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> queryOrderPayInfo(String orderCode) {
		if (EmptyUtil.isEmpty(orderCode)) {
			return JsonResult.fail("订单号orderCode必选项");
		}
		return soManage.queryOrderPayInfo(orderCode);
	}

	/**
	 * 创建订单
	 *
	 * @param receiveAddressId
	 * @param remark 示例：{"1":"自营商品用户备注","2":"三方商品用户备注"}
	 * @param type
	 *            订单类型 0:直接下单 1:购物车
	 * @param cartItemIds
	 * @param num
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createOrder")
	@ResponseBody
	public JsonResult<Map<String, Object>> createOrder(Long exchangeId,Long exchangeCouponUnitId,Long exchangeCouponBatchId,Integer orderType,Long storeId, Long receiveAddressId, Integer type,
			String cartItemIds, Long puId, Integer num, Integer useFubi, String remark, Long invoiceId, String phone,
			Integer couponType, Long couponUnitId, String deliveryPrice,String channelProductId,Integer source, HttpServletRequest req) {

		logger.info("[创建订单]参数.exchangeId="+exchangeId);
		logger.info("[创建订单]参数.exchangeCouponUnitId="+exchangeCouponUnitId);
		logger.info("[创建订单]参数.exchangeCouponBatchId="+exchangeCouponBatchId);
		logger.info("[创建订单]参数.orderType="+orderType);
		logger.info("[创建订单]参数.receiveAddressId="+receiveAddressId);
		logger.info("[创建订单]参数.type="+type);
		logger.info("[创建订单]参数.cartItemIds="+cartItemIds);
		logger.info("[创建订单]参数.puId="+puId);
		logger.info("[创建订单]参数.num="+num);
		logger.info("[创建订单]参数.useFubi="+useFubi);
		logger.info("[创建订单]参数.remark="+remark);
		logger.info("[创建订单]参数.invoiceId="+invoiceId);
		logger.info("[创建订单]参数.couponType="+couponType);
		logger.info("[创建订单]参数.couponUnitId="+couponUnitId);
		logger.info("[创建订单]参数.deliveryPrice="+deliveryPrice);
		logger.info("[创建订单]参数.channelProductId="+channelProductId);
		logger.info("[创建订单]参数.source="+source);
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		Long companyId = userCache.getCompanyId();// 用户公司id
		String userName = userCache.getName();// 用户名称
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		String ip = HostUtils.getClientIP(req);

		if(EmptyUtil.isNotEmpty(puId)&&puId==0){
			puId=null;
			num=null;
		}

		if(EmptyUtil.isEmpty(storeId)){
			if(platformId==7){
				logger.info("大厨管家订单");
				storeId=1L;
			}
			if(platformId==2){
				logger.info("富宏云采订单");
				storeId = 2L;
			}
		}
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}
		String f_ = req.getHeader("f");
		if (EmptyUtil.isEmpty(f_))
			return JsonResult.fail("渠道来源参数缺失");
		Integer f = Integer.parseInt(f_);
		String v = req.getHeader("v");
		if (EmptyUtil.isEmpty(v))
			return JsonResult.fail("应用版本号参数缺失");
		String phoneModel = req.getHeader("phoneModel");
		if (EmptyUtil.isEmpty(phoneModel))
			return JsonResult.fail("手机型号参数缺失");
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return JsonResult.fail("设备编号参数缺失");
		String os = req.getHeader("os");
		if (EmptyUtil.isEmpty(os))
			return JsonResult.fail("操作系统参数缺失");
		logger.info("创建订单-" + "platformId=" + platformId + "-ip" + ip + "-f" + f + "-v" + v + "-os" + os + "-phoneModel"
				+ phoneModel + "-deviceId" + deviceId);


		//orderType为8时,表示以旧换新补差价
		if(EmptyUtil.isNotEmpty(orderType)&&orderType.equals(8)){
			if(EmptyUtil.isEmpty(exchangeCouponBatchId)||EmptyUtil.isEmpty(exchangeCouponUnitId)){
				return JsonResult.fail("以旧换新数据丢失");
			}
			CouponUnitDTO dto=soManage.findCouponUnitById(exchangeCouponUnitId);
			if(EmptyUtil.isEmpty(dto)){
				return JsonResult.fail("以旧换新的旧unit不存在");
			}
			logger.info("开始进行加锁");
			//锁定当前的旧unit
			boolean lock=true;
			try {
				lock=jedisUtil.lockWithParam(JedisUtil.COUPON_UNIT_LOCK_PRE+dto.getCouponUnitCode(),COUPON_UNIT_LOCK_VALUE,JedisUtil.COUPON_UNIT_LOCK_EXPIRE_TIME);
			} catch (InterruptedException e) {
				logger.info("[创建订单,以旧换新旧unit加锁异常],couponunitCode="+dto.getCouponUnitCode());
				jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+dto.getCouponUnitCode());
				e.printStackTrace();
			}
			if(!lock){
				return JsonResult.fail("当前旧优惠券unit已存在订单,如需重新兑换请先取消原订单");
			}
			/*int i=soManage.updateCouponUnitLockedByCouponUnitId(exchangeCouponUnitId);
			logger.info("加锁完成,i="+i);
			if(i==0){
				return JsonResult.fail("当前旧优惠券unit已存在订单,如需重新兑换请先取消原订单");
			}*/
		}

		//创建订单操作
		JsonResult<Map<String, Object>> result = null;
		List<Long> puIdList = new ArrayList<>();
		try{
			result=soManage.createOrderNew(req,puIdList,exchangeId,exchangeCouponUnitId,exchangeCouponBatchId,orderType,storeId, receiveAddressId, type,
					cartItemIds, puId, num, useFubi, remark, invoiceId, userId, platformId, deviceId, f, ip, os, phoneModel,
					v, userName, mac, companyId, phone, couponType, couponUnitId, deliveryPrice,channelProductId,source,null);
		}catch (Exception e){
			logger.info("创建订单异常,e",e);
			throw e;
		}finally {
			//清除pu锁
			for(Long id:puIdList){
				logger.info("[创建订单]删除pu锁,puId="+id);
				jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+id);
			}
		}

		//记录创建订单日志
		if (result.getCode() == 0) {
			SoDTO newSoDTO = soManage.querySoByOrderCode((String) result.getData().get("orderCode"));
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("OrderCoreAction_createOrder");
			log.setMsgId(LogConstant.ORDER_NEW.getStatus());
			log.setType(LogTypeConstant.SO.getStatus());
			log.setOperatorObjId(newSoDTO.getId());
			log.setOperatorObjCode(newSoDTO.getOrderCode());
			log.setNewObj(newSoDTO);

			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
				//logger.info("创建订单发送消息日志:{}",JSON.toJSONString(log));
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}

		}
		logger.info("创建订单正常结束，返回结果:{}",JSON.toJSONString(result));
		return result;

	}

	/**
	 * 查询订单详情
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderDetail(String orderCode, HttpServletRequest req) {
		logger.info("根据id查询订单详情");
		String clientId_ = req.getHeader("clientId");
		String f_ = req.getHeader("f");
		CacheUser cacheUser = getCacheUser();
		return soManage.orderDetail(orderCode, cacheUser.getPlatformId(), cacheUser.getId(), Long.parseLong(f_), Long.parseLong(clientId_));
	}

	/**
	 * 查询子订单详情
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/childOrderDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> childOrderDetail(String orderCode, HttpServletRequest req) {
		logger.info("根据id查询订单详情");
		String clientId_ = req.getHeader("clientId");
		String f_ = req.getHeader("f");
		CacheUser cacheUser = getCacheUser();
        JsonResult<Map<String, Object>> result= soManage.childOrderDetail(orderCode, cacheUser.getPlatformId(), cacheUser.getId(), Long.parseLong(f_), Long.parseLong(clientId_));
        logger.info("childOrderDetail:orderCode-{},result-{}",orderCode,JSONObject.toJSONString(result));
        return result;
    }

	/**
	 * 取消订单
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder")
	@ResponseBody
	public JsonResult<Map<String, Object>> cancelOrder(String orderCode, HttpServletRequest req) {
		logger.info("取消订单,订单号:{}",orderCode);
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);

		String ip = HostUtils.getClientIP(req);
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		boolean lock = true;
		try {
			lock=jedisUtil.lockWithParam(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode,CANCEL_LOCK_VALUE,JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("获取订单取消锁异常,orderCode="+orderCode);
			jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
			e.printStackTrace();
		}
		if(!lock){
			return JsonResult.fail("当前订单正在取消中,不可重复操作");
		}
		JsonResult<Map<String, Object>> result=null;
		try {
			 result = soManage.cancelOrder(orderCode, userId, platformId, ip, userName, mac, req);
		}catch (Exception e){
			logger.info("取消订单异常");
			throw e;
		}finally {
			jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
		}


		return result;
	}


	/**
	 * 取消子订单
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/cancelChildOrder")
	@ResponseBody
	public JsonResult<Map<String, Object>> cancelChildOrder(String orderCode, HttpServletRequest req) {
		logger.info("取消子订单,子订单号:{}",orderCode);
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);

		String ip = HostUtils.getClientIP(req);
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		boolean lock = true;
		try {
			lock=jedisUtil.lockWithParam(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode,CANCEL_LOCK_VALUE,JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("获取订单取消锁异常,orderCode="+orderCode);
			jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
			e.printStackTrace();
		}
		if(!lock){
			return JsonResult.fail("当前订单正在取消中,不可重复操作");
		}
		JsonResult<Map<String, Object>> result=null;
		try {
			result = soManage.cancelChildOrder(orderCode, userId, platformId, ip, userName, mac, req);
		}catch (Exception e){
			logger.info("取消订单异常");
			throw e;
		}finally {
			jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
		}


		return result;
	}

	/**
	 * 根据用户id查询用户不同订单状态列表
	 *
	 * @param orderStatus
	 *            -99全部 0未付款 1已付款 2已发货 4已完成
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findOrderByUserAndStatus")
	@ResponseBody
	public JsonResult<PageResult<SoDetailVO>> findOrderByUserAndStatus(Integer orderStatus,Pagination page,
			HttpServletRequest req) {
		logger.info("根据用户id查询用户不同订单状态列表");
		logger.info("[订单列表]参数:status="+orderStatus);
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		String clientId_ = req.getHeader("clientId");
		String f_ = req.getHeader("f");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		if (EmptyUtil.isEmpty(clientId_))
			return JsonResult.fail("clientId为空");
		if (EmptyUtil.isEmpty(f_))
			return JsonResult.fail("f为空");

		Long platformId = Long.parseLong(platformId_);
		Long clientId = Long.parseLong(clientId_);
		Long f = Long.parseLong(f_);

		PageResult<SoDetailVO> so = soManage.findOrderByUserAndStatus(userId, orderStatus, platformId,
				f, clientId, page);
		return JsonResult.success(so);
	}

	/**
	 * 根据用户id查询用户不同订单状态列表
	 *
	 * @param orderStatus
	 *            -99全部 0未付款 1已付款 2已发货 4已完成
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getOrderByUserAndStatus")
	@ResponseBody
	public JsonResult<PageResult<SoDetailVO>> getOrderByUserAndStatus(Integer orderStatus,Pagination page,
																	   HttpServletRequest req) {
		logger.info("根据用户id查询用户不同订单状态列表");
		logger.info("[订单列表]参数:status="+orderStatus);
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		String clientId_ = req.getHeader("clientId");
		String f_ = req.getHeader("f");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		if (EmptyUtil.isEmpty(clientId_))
			return JsonResult.fail("clientId为空");
		if (EmptyUtil.isEmpty(f_))
			return JsonResult.fail("f为空");

		Long platformId = Long.parseLong(platformId_);
		Long clientId = Long.parseLong(clientId_);
		Long f = Long.parseLong(f_);

		PageResult<SoDetailVO> so = soManage.getOrderByUserAndStatus(userId, orderStatus, platformId,
				f, clientId, page);
		return JsonResult.success(so);
	}

	/**
	 * 确认收货
	 *
	 * @return
	 */
	@RequestMapping(value = "/affirmOrderBySoId")
	@ResponseBody
	public JsonResult<String> affirmOrderBySoId(String orderCode, HttpServletRequest req) {
		logger.info("根据订单id确认收货");
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);

		// 记录订单完成日志
		SoDTO oldSoDTO = soManage.querySoByOrderCode(orderCode);
		if(oldSoDTO == null){
			return JsonResult.fail("未找到相应的订单");
		}
		// 确认订单
		String type = soManage.affirmOrderBySoId(orderCode, platformId, userId);

		SoDTO newSoDTO = soManage.querySoByOrderCode(orderCode);
		EgeoLog log = new EgeoLog();
		log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
		log.setOperObject("OrderCoreAction_affirmOrderBySoId");
		log.setMsgId(LogConstant.ORDER_CONFIRM.getStatus());
		log.setType(LogTypeConstant.SO.getStatus());
		log.setOperatorObjId(newSoDTO.getId());
		log.setOperatorObjCode(newSoDTO.getOrderCode());
		log.setNewObj(newSoDTO);
		log.setOldObj(oldSoDTO);

		EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}

		return JsonResult.success(type);
	}


	/**
	 * 确认收货
	 *
	 * @return
	 */
	@RequestMapping(value = "/affirmOrderByChildCode")
	@ResponseBody
	public JsonResult<String> affirmOrderByChildCode(String orderCode, HttpServletRequest req) {
		logger.info("根据子订单订单orderCode确认收货{}",orderCode);
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);

		// 记录订单完成日志
		SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);
		if(soChildDTO == null){
			return JsonResult.fail("子订单未找到");
		}

		// 确认订单
		String type = soManage.affirmOrderByChildCode(orderCode, platformId, userId);

		SoChildDTO newSoDTO = soChildReadService.querySoChildByChildCode(orderCode);
		EgeoLog log = new EgeoLog();
		log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
		log.setOperObject("OrderCoreAction_affirmOrderByChildCode");
		log.setMsgId(LogConstant.ORDER_CONFIRM.getStatus());
		log.setType(LogTypeConstant.SO.getStatus());
		log.setOperatorObjId(newSoDTO.getId());
		log.setOperatorObjCode(newSoDTO.getChildCode());
		log.setNewObj(newSoDTO);
		log.setOldObj(soChildDTO);

		EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}

		return JsonResult.success(type);
	}
	/**
	 * 查询订单剩余支付时间
	 *
	 * @return
	 */
	@RequestMapping(value = "/orderPayEndTime")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderPayEndTime(String orderCode) {
		return soManage.orderPayEndTime(orderCode);
	}

	/**
	 * 查询订单支付状态
	 *
	 * @param orderCode
	 * @return
	 */
	@RequestMapping(value = "/orderPayConfirm")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderPayConfirm(String orderCode, HttpServletRequest req) {
		return soManage.orderPayConfirm(orderCode);
	}

	/**
	 * 发票提示
	 *
	 * @return
	 */
	@RequestMapping(value = "/invoiceHint")
	@ResponseBody
	public JsonResult<Map<String, Object>> invoiceHint(HttpServletRequest req) {
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		return invoiceManage.invoiceHint(platformId, userId);
	}

	/**
	 * 保存发票信息
	 *
	 * @param invoiceForm
	 *            发票形式 0:纸质发票 1:电子发票
	 * @param invoiceTitleType
	 *            抬头类型 0：个人；1：公司
	 * @param title
	 *            纳税人识别号
	 * @param contentType
	 *            发票明细类型 0:商品明细 1:商品类别
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveInvoice")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveInvoice(Long id, Integer invoiceForm, Integer invoiceTitleType,
			Integer contentType, String title, Long commonInvoiceId, HttpServletRequest req) {
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id

		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		return invoiceManage.saveInvoice(id, invoiceForm, invoiceTitleType, contentType, userId,
				 title,commonInvoiceId, platformId);
	}

	@RequestMapping(value = "/invoiceDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> invoiceDetail(Long id) {
		return invoiceManage.invoiceDetail(id);
	}

	/**
	 * 删除订单
	 *
	 * @param orderCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public JsonResult<Map<String, Object>> deleteOrder(String orderCode, HttpServletRequest req) {
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		return soManage.deleteByOrderCode(orderCode, userId);
	}

	/**
	 * 删除子订单
	 *
	 * @param orderCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteChildOrder")
	@ResponseBody
	public JsonResult<Map<String, Object>> deleteChildOrder(String orderCode, HttpServletRequest req) {
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		return soManage.deleteByChildOrderCode(orderCode, userId);
	}

	/**
	 * 查询已开的电子发票列表
	 * @param orderCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryElecInvoice")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> queryElecInvoice(String orderCode, HttpServletRequest req) {
		if (orderCode == null)
			return JsonResult.fail("订单编号不能为空");
		CacheUser userCache = getCacheUser();
		SoDTO soDTO = soManage.querySoByOrderCode(orderCode);

		SoInvoiceDTO dto = new SoInvoiceDTO();
		dto.setSoId(soDTO.getId());
		dto.setUserId(userCache.getId());

		return JsonResult.success(invoiceManage.queryElecInvoice(dto));
	}

	/**
	 * 下载发票
	 * @param invoiceIds
	 * @return
	 */
	@RequestMapping(value = "/downloadInvoice")
	@ResponseBody
	public JsonResult<Long> downloadInvoice(String invoiceIds, String email) {
		CacheUser cacheUser = getCacheUser();

		if (EmptyUtil.isEmpty(invoiceIds))
			return JsonResult.fail("订单发票id不能为空");
		if (EmptyUtil.isEmpty(email))
			return JsonResult.fail("邮箱不能为空");
		if (StringUtils.isNotEmail(email))
			return JsonResult.fail("邮箱格式不正确");

		List<Long> invoiceIdList = new ArrayList<>();
		try {
			invoiceIdList = StringUtils.string2IdList(invoiceIds, ",");
		} catch (Exception e) {
			return JsonResult.fail("订单发票id参数错误");
		}

		return invoiceManage.downloadInvoice(invoiceIdList, email, cacheUser.getLoginName());
	}

	/**
	 * 查询用户订单/优惠卷的统计信息
	 * @return
	 */
	@RequestMapping(value = "/queryUserStatisticalInfo")
	@ResponseBody
	public JsonResult<Map<String,Object>> queryUserStatisticalInfo(HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");

		Long platformId = Long.parseLong(platformId_);
		return soManage.queryUserStatisticalInfo(cacheUser.getId(),platformId);
	}


	/**
	 * 查询代付款订单数量(暂时废除storeid)
	 */
	@RequestMapping("/getUnPayNum")
	@ResponseBody
	public JsonResult<Long> getUnPayNum(Long storeId,HttpServletRequest req){
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");

		Long platformId = Long.parseLong(platformId_);

		return soManage.getUnPayNum(storeId,platformId,userId);
	}

	/**
	 * 查询已付款订单数量(暂时废除storeid)
	 */
	@RequestMapping("/getPaiedNum")
	@ResponseBody
	public  JsonResult<Long> getPaiedNum(Long storeId,HttpServletRequest req){
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");

		Long platformId = Long.parseLong(platformId_);
		return soManage.getPaiedNum(storeId,platformId,userId);
	}

	/**
	 * 查询已发货订单数量(暂时废除storeid)
	 */
	@RequestMapping("/getSendedNum")
	@ResponseBody
	public JsonResult<Long> getSendedNum(Long storeId,HttpServletRequest req){
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");

		Long platformId = Long.parseLong(platformId_);
		return soManage.getSendedNum(storeId,platformId,userId);
	}

	@RequestMapping("/repairOrderData")
	@ResponseBody
	public JsonResult<String> repairOrderData(Long storeId,HttpServletRequest req){
		soManage.repairOrderDataWithTx();
		return JsonResult.success("修复成功");
	}

	//已废弃
	@RequestMapping("/getDeliveryPriceFromJd")
	@ResponseBody
	public JsonResult<Map<String,String>> getDeliveryPriceFromJd(String address,List< SkuInfo > skuInfoList){
		Map<String, String> deliveryPriceFromJd=soManage.getDeliveryPriceFromJd(address,skuInfoList);
		Map<String, String> result = new HashMap<>();
		//获取运费成功
		if(deliveryPriceFromJd.get("success").equals(1)){
			result.put("deliveryPrice", deliveryPriceFromJd.get("deliveryPrice"));
		}else{
			//获取运费失败
			result.put("jdDeliveryError", "抱歉，因为网络问题，运费计算有误,请以订单最终收取的运费为准");
		}
		return JsonResult.success(result);
	}


	//转换京东地址
	@RequestMapping("/getJdAddress")
	@ResponseBody
	public JsonResult getJdAddress(String address){
		String accessToken = jdUtils.getAccessToken(jedisUtil);
		String s = jdUtils.parseAddress(address, accessToken);
		if(EmptyUtil.isNotEmpty(s)){
			JSONObject resultObj = JSONObject.parseObject(s);
			Boolean success = resultObj.getBoolean("success");
			if (EmptyUtil.isNotEmpty(success) && success) {
				return JsonResult.success(resultObj.getJSONObject("result"));
			}
		}
		return JsonResult.success("null");
	}

	@Autowired
	private CreateOrderHelper createOrderHelper;

	@Resource(name = "createOrderManage")
	private CreateOrderManage createOrderManage;

	/**
	 * 重构创建订单
	 * @param createOrderRequestVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createOrder2")
	@ResponseBody
	public JsonResult<Map<String, Object>> createOrder2(CreateOrderRequestVO createOrderRequestVO, HttpServletRequest req) {
		logger.info("[重构-创建订单]参数:"+JSON.toJSONString(createOrderRequestVO));
		CacheUser userCache = getCacheUser();

		//检查参数
		JsonResult checkRt = createOrderHelper.checkCreateOrderRequest(createOrderRequestVO,req);
		if(Objects.nonNull(checkRt)){
			return checkRt;
		}

		//转换子类处理支持更多参数传递,考虑子类更多是为了区分哪些是真正从前端传递的参数
		CreateOrderRequestExtendsVO vo = createOrderHelper.toCreateOrderExtendsVO(createOrderRequestVO,userCache,req);
		logger.info("重构-创建订单-" + "platformId=" + vo.getPlatformId() + "-ip" + vo.getIp() + "-f" + vo.getF() + "-v" + vo.getV() + "-os" + vo.getOs() + "-phoneModel"
				+ vo.getPhoneModel() + "-deviceId" + vo.getDeviceId());

		//锁定以旧换新
		checkRt = createOrderHelper.lockOldToNew(vo);
		//以旧换新锁定失败
		if(Objects.nonNull(checkRt)){
			return checkRt;
		}

		//创建订单逻辑
		JsonResult<Map<String, Object>> result = createOrder(vo,req);
		//保存日志
		createOrderHelper.saveCreateOrderLog(result,req);
		return result;
	}

	private JsonResult<Map<String, Object>> createOrder(CreateOrderRequestExtendsVO vo, HttpServletRequest req){
		//创建订单操作
		JsonResult<Map<String, Object>> result = null;
		try{
			result = createOrderManage.createOrderNew(vo,req);
		}catch (Exception e){
			logger.info("重构-创建订单异常,e",e);
			throw e;
		}finally {
			//清除pu锁
			for(Long id:vo.getPuIdList()){
				logger.info("[重构-创建订单]删除pu锁,puId="+id);
				jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+id);
			}
		}
		return result;
	}


	/**
	 * [检查订单]参数
	 * @param vo
	 * @param req
	 * @return
	 */
	/*@RequestMapping(value = "/buyCardUseCheck")
	@ResponseBody
	public JsonResult<BuyCardUseCheckRespVO> buyCardUseCheck(BuyCardUseCheckReqVO vo, HttpServletRequest req) {
		logger.info("[检查订单]参数:"+JSON.toJSONString(vo));
		CacheUser userCache = getCacheUser();
		vo.setUserId(userCache.getId());
		return soManage.buyCardUseCheck(vo);
	}*/

	/**
	 * [检查订单]参数
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/buyCardUseCheck")
	@ResponseBody
	public JsonResult<Integer> buyCardUseCheck(BuyCardUseCheckReqVO vo, HttpServletRequest req) {
		logger.info("[检查订单]参数:"+JSON.toJSONString(vo));
		return soManage.buyCardUseCheckNum(vo);
	}

	/**
	 * [检查订单]参数
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCanUserCards")
	@ResponseBody
	public JsonResult<CardPayCanUseRespVO> findCanUserCards(CardPayCanUseReqVO vo, HttpServletRequest req) {
		logger.info("[检查订单]参数:"+JSON.toJSONString(vo));
		return soManage.findCanUserCards(vo);
	}
}
