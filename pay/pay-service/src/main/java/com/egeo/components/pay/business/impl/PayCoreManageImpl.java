package com.egeo.components.pay.business.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dto.QmOrderDTO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.pay.business.PayCoreManage;
import com.egeo.components.pay.converter.AliCallBackConverter;
import com.egeo.components.pay.converter.SoItemConverter;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.dto.PayAliLogDTO;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.pay.facade.AccountFacade;
import com.egeo.components.pay.facade.AwaitQueueFacade;
import com.egeo.components.pay.facade.OrderFacade;
import com.egeo.components.pay.facade.PayCoreFacade;
import com.egeo.components.pay.facade.SoFacade;
import com.egeo.components.pay.facade.UserFacade;
import com.egeo.components.pay.vo.AliCallBackVo;
import com.egeo.components.pay.vo.SoItemVO;
import com.egeo.components.pay.vo.StandardOrderQueryResultVO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.WxOpenidDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.ReflectUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.str.UUID;
import com.egeo.web.JsonResult;

@Service("payCore")
public class PayCoreManageImpl implements PayCoreManage {

	XLogger logger = XLogger.getLogger(this.getClass().getName());

	@Resource(name = "payCoreFacade")
	private PayCoreFacade payCoreFacade;

	@Resource(name = "soFacade")
	private SoFacade soFacade;

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name="accountFacade")
	private AccountFacade accountFacade;

	@Resource(name="userFacade")
	private UserFacade userFacade;

	@Resource(name="awaitQueueFacade")
	private AwaitQueueFacade awaitQueueFacade;

	@Autowired
	private JedisUtil jedisUtil;


	@Autowired
	private UserClient userReadService;
	@Autowired
	private PayUtil payUtil;
	@Override
	public JsonResult<Map<String, Object>> nativeWXpaySign(String clientId, StandardOrderQueryResultVO order, String userIp,
														   Long platformId, Integer signPlatform, String weiXinOpenId, HttpServletRequest req,
														   HttpServletResponse response) {
		//变更订单现金支付方式,插入等待队列
		String randomNumber = null;
		String itemName = null;
		try {
			randomNumber = StringUtils.getRandomByCount(3);
			itemName = genPaySubject(order.getItems());
			/*prop = PayUtil.payConfig;*/
			// signPlatform 签名平台 1:原生 2:h5 3:公众号 4:扫码支付 5:电脑网站 默认1
			switch (signPlatform) {
				case 1:
					payCoreFacade.orderPaySigned(order,2,randomNumber,payUtil.SIGN_PLATFORM_NATIVE);
					break;
				case 3:
					payCoreFacade.orderPaySigned(order,2,randomNumber,payUtil.SIGN_PLATFORM_JSAPI);
					break;
				case 4:
					payCoreFacade.orderPaySigned(order,2,randomNumber,payUtil.SIGN_PLATFORM_NATIVE);
					break;
				case 5:
					payCoreFacade.orderPaySigned(order,2,randomNumber,payUtil.SIGN_PLATFORM_WORK_JSAPI);
					break;

				default:
					break;
			}
		}catch (Exception e){
			logger.info("获取微信签名加入支付等待队列异常,e:",e);
			throw e;
		}finally {
			//解锁订单
			Long aLong = jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+order.getOrderCode());
			logger.info(order.getOrderCode()+"解锁成功,结果:"+aLong);
		}


		SoDTO so=orderFacade.querySoByOrderCode(order.getOrderCode());
		Integer companyType = userFacade.findCompanyTypeByUserId(so.getUserId());

		List<CompanyConfigDTO>  configs = userReadService.findUserCompanyConfigs(so.getUserId());

		String appId = null;
		String appSecret = null;
		String redirectUrl = null;
		String toWeixinLoginPage = null;

		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.domain.name")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
				toWeixinLoginPage = config.getValue();
			}
		}
		if(companyType != 0){
			// 插入日志,更改订单支付状态
			// cashPayType 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 5 跳过
			// companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
			payCoreFacade.insertWxCallBackLogAndOrderPayConfirm(null, so,5,companyType, req);
			Map<String,Object> data=new HashMap<>();
			data.put("pay", false);
			return JsonResult.success(data);
		}else{
			//调用微信支付网关
			// 组织签名
			Map<String, String> paraMap = new HashMap<>();

			/**
			 * 根据签名平台确定配置属性
			 */
			PayUtil.WxPayConfig wxPayConfig=payUtil.wxPayConfigBySignPlatform(signPlatform,platformId);
			if(com.egeo.utils.StringUtils.isBlank(appId)) {
				appId=wxPayConfig.getAppId();
			}
			/*if(clientId.equals("4")){
				Long companyId = so.getCompanyId();
				CompanyDTO companyDTO=userFacade.findCompanyById(companyId);
				appId = companyDTO.getCorpid();
			}
*/
			String mchId=wxPayConfig.getMchId();
			WxOpenidDTO wxOpenid = new WxOpenidDTO();
			wxOpenid.setUserId(so.getUserId());
			wxOpenid.setWxOpenid(weiXinOpenId);
			List<WxOpenidDTO> wxOpenidDTOs = userReadService.queryWxOpenid(wxOpenid);
			if(wxOpenidDTOs!=null && wxOpenidDTOs.size()>0) {
				appId = wxOpenidDTOs.get(0).getWxAppid();
			}
			paraMap.put("appid", appId);
			paraMap.put("mch_id", mchId);
			paraMap.put("nonce_str", UUID.uuid());
			paraMap.put("sign_type", wxPayConfig.getSignType());
			paraMap.put("body", itemName);
			// 生成3位随机数
			paraMap.put("out_trade_no",order.getOrderCode() + randomNumber);
			BigDecimal needPayCash=order.getNeedPayCash();
			paraMap.put("total_fee",payUtil.priceDecimal2IntString(needPayCash));
			paraMap.put("spbill_create_ip", userIp);
			paraMap.put("notify_url", wxPayConfig.getNotifyUrl());
			paraMap.put("trade_type", wxPayConfig.getTradeType());
			if(signPlatform == 3||signPlatform==5){
				logger.info("weiXinOpenId:"+weiXinOpenId);
				paraMap.put("openid", weiXinOpenId);
			}
			// 打统一下单接口,做字典排序,生成签名,获取h5调取的url
			Map<String, String> responseParams = null;
			PayWeixinLogDTO log=null;
			try {
				String apiName=payUtil.getProperty("wx.unifiedorder.url");
				responseParams = payUtil.wxPayBuildXmlRequest(paraMap,apiName, wxPayConfig.getPrivateKey());

				log = ReflectUtil.map2JavaBean(responseParams, PayWeixinLogDTO.class, true);
				log.setOutTradeNo(order.getOrderCode());
				log.setCashFee(needPayCash.longValue());
				log.setPlatformId(platformId);
				// 插入日志记录
				payCoreFacade.insertWxPayLog(log);
				String returnCode = responseParams.get("return_code");
				if (EmptyUtil.isNotEmpty(returnCode) && !returnCode.equals("SUCCESS")){
					logger.info("签名失败,原因是" + responseParams.get("return_msg"));
					return JsonResult.fail("签名失败,原因是" + responseParams.get("return_msg"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("签名错误",e);
				return JsonResult.fail("签名错误");
			}
			//返回生成的签名信息
			String prePayId=log.getPrepayId();
			// signPlatform 签名平台 1:原生 2:h5 3:公众号 4:扫码支付 5:企业微信 默认1
			String timestamp=String.valueOf(System.currentTimeMillis()/1000l);
			String nonstr=UUID.uuid();
			switch (signPlatform) {
			case 1:
				//二次签名参数
				Map<String,String> secSignMap=new HashMap<>();
				secSignMap.put("prepayid", prePayId);
				secSignMap.put("appid", appId);
				secSignMap.put("partnerid", mchId);
				secSignMap.put("noncestr", nonstr);
				secSignMap.put("timestamp", timestamp);
				secSignMap.put("package", "Sign=WXPay");
				//第二次签名结果
				String signSec=payUtil.wxSign(secSignMap, wxPayConfig.getPrivateKey());

				Map<String,Object> data=new HashMap<>();
				data.put("appId",appId);
				data.put("pack","Sign=WXPay");
				data.put("partnerId",mchId);
				data.put("prepayId",prePayId);
				data.put("nonstr",nonstr);
				data.put("timestamp",timestamp);
				data.put("signResult",signSec);
				data.put("pay", true);
				logger.info(JSONArray.toJSONString(data));
				return JsonResult.success(data);
				case 3: case 5:
				//二次签名参数
				Map<String,String> secSignMap3=new HashMap<>();
				secSignMap3.put("appId", appId);
				secSignMap3.put("timeStamp", timestamp);
				secSignMap3.put("nonceStr", nonstr);
				secSignMap3.put("package", "prepay_id="+prePayId);
				secSignMap3.put("signType", "MD5");
				//第二次签名结果
				String signSec3=payUtil.wxSign(secSignMap3, wxPayConfig.getPrivateKey());

				Map<String,Object> data3=new HashMap<>();
				data3.put("appId",appId);
				data3.put("prepayId",prePayId);
				data3.put("nonstr",nonstr);
				data3.put("timestamp",timestamp);
				data3.put("signResult",signSec3);
				logger.info(JSONArray.toJSONString(data3));
				return JsonResult.success(data3);
			case 4:
				// 获取二维码字符串
				String urlCode = responseParams.get("code_url");
				Map<String,Object> data4=new HashMap<>();
				data4.put("appId",appId);
				data4.put("prepayId",prePayId);
				data4.put("nonstr",nonstr);
				data4.put("timestamp",timestamp);
				data4.put("urlCode", urlCode);
				logger.info(JSONArray.toJSONString(data4));
				return JsonResult.success(data4);

			default:
				return JsonResult.fail("未知签名平台");
			}
		}

	}
	public void outPrint(HttpServletResponse response, String result) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	@Override
	public JsonResult<Map<String, Object>> h5WXPaySign(StandardOrderQueryResultVO order, String userIp,Long platformId) {
		//变更订单现金支付方式,插入等待队列
		String randomNumber = StringUtils.getRandomByCount(3);
		payCoreFacade.orderPaySigned(order,2,randomNumber,payUtil.SIGN_PLATFORM_H5);
		String itemName = genPaySubject(order.getItems());
		/*Properties prop = PayUtil.payConfig;*/



//		if (EmptyUtil.isBlank(orderCode))
//			return JsonResult.fail("请选择订单");
//		// 根据订单id查询订单
//		SoDTO order = orderFacade.querySoByOrderCode(orderCode);
//		if (order == null)
//			return JsonResult.fail("订单不存在");
//
//		// 校验订单支付状态
//		if (order.getOrderStatus().intValue() == 1)
//			return JsonResult.fail("订单已经支付");
//		if (order.getOrderStatus().intValue() != 0)
//			return JsonResult.fail("订单状态有误");
//		// 校验订单支付超时
//		Long createTime = order.getCreateTime().getTime();
//
//		if (orderPayOutOfTime(createTime)) {
//			orderFacade.updateOrderStatusAndUnlockStock(orderCode);
//			return JsonResult.fail("订单支付超时");
//		}
//
//		int orderAmount = 0;
//		// 根据订单id查询订单冻结积分金额
//		BigDecimal soFreezeBalance = payCoreFacade.findSoFreezeBalanceBySoId(order.getId());
//		if (EmptyUtil.isNotEmpty(soFreezeBalance)) {
//			// 订单冻结积分不为空，则订单总额减去冻结积分等于应付金额
//			BigDecimal subtract = order.getOrderAmount().subtract(soFreezeBalance);
//			orderAmount = (int) Math.floor(subtract.doubleValue() * 100);
//		} else {
//			orderAmount = (int) Math.floor(order.getOrderAmount().doubleValue() * 100);
//		}
//		// Long userId_ = order.getUserId();
//		// 判断订单与用户
//		// 取分为单位的订单总额
//		List<SoItemDTO> soItems = orderFacade.querySoItemBySoId(order.getId());
//		if (soItems.size() == 0)
//			return JsonResult.fail("订单异常");
//		String itemName = genPaySubject("H5支付-", soItems);
//		// 组织签名
//		Properties prop = PayUtil.payConfig;
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("appid", payUtil.getProperty("wx.app.id"));
		paraMap.put("mch_id", payUtil.getProperty("wx.mch.id"));
		paraMap.put("nonce_str", UUID.uuid());
		paraMap.put("sign_type", payUtil.getProperty("wx.sign.type"));
		paraMap.put("body", itemName);
		// 生成3位随机数
		paraMap.put("out_trade_no", String.valueOf(order.getOrderCode() + randomNumber));
		BigDecimal needPayCash=order.getNeedPayCash();
		paraMap.put("total_fee",payUtil.priceDecimal2IntString(needPayCash));
		paraMap.put("spbill_create_ip", userIp);
		paraMap.put("notify_url", payUtil.getProperty("wx.notify.url"));
		paraMap.put("trade_type", payUtil.getProperty("wx.trade.type"));
		paraMap.put("scene_info", payUtil.getProperty("wx.scene.info"));
		// 打统一下单接口,做字典排序,生成签名,获取h5调取的url
		Map<String, String> responseParams = null;
		try {
			String apiName=payUtil.getProperty("wx.unifiedorder.url");
			responseParams = payUtil.wxPayBuildXmlRequest(paraMap,apiName,payUtil.getProperty("wx.private.key"));

			PayWeixinLogDTO log = ReflectUtil.map2JavaBean(responseParams, PayWeixinLogDTO.class, true);
			log.setOutTradeNo(order.getOrderCode());
			log.setCashFee(needPayCash.longValue());
			log.setPlatformId(platformId);
			// 插入日志记录
			payCoreFacade.insertWxPayLog(log);
			String returnCode = responseParams.get("return_code");
			if (EmptyUtil.isNotEmpty(returnCode) && !returnCode.equals("SUCCESS"))
				return JsonResult.fail("签名失败,原因是" + responseParams.get("return_msg"));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("签名错误");
		}

		Map<String, Object> data = new HashMap<>();
		try {
			String returnUrl = payUtil.getProperty("wx.return.url") + "?out_trade_no=" + order.getOrderCode()
					+ "&type=wxPay&appScheme=WelfarePlus://";
			String suffix = "&redirect_url=" + URLEncoder.encode(returnUrl, "utf-8");
			data.put("mwebUrl", responseParams.get("mweb_url") + suffix);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		data.put("sign", responseParams.get("sign"));
		return JsonResult.success(data);
	}

	/**
	 * 判断订单支付超时
	 *
	 * @param createTime
	 * @return
	 */
	private boolean orderPayOutOfTime(Long createTime) {
		Long curr = System.currentTimeMillis();
		return curr >= createTime + PayUtil.ORDER_EXIST_TIME_MS || curr <= createTime;
		// 30*60*1000l=1800000l 30分钟
	}

	/**
	 * 生成微信/支付宝所需的商品描述
	 *
	 * @param soItems
	 * @return
	 */
	private String genPaySubject(String prefix, List<SoItemDTO> soItems) {
		StringBuilder result = new StringBuilder();
		List<Long> puIds = new ArrayList<>();
		result.append(prefix);
		for (SoItemDTO si : soItems) {
			puIds.add(si.getPuId());
		}
		// 根据puid集合查询pu信息
		List<CommodityProductUnitDTO> productUnitList = payCoreFacade.findByPUIds(puIds);
		for (CommodityProductUnitDTO commodityProductUnitDTO : productUnitList) {
			result.append(commodityProductUnitDTO.getName()).append(" ");
		}

		String s = result.toString();
		if (s.length() > 10) {
			s = s.substring(0, 10) + "...";
		}
		return s;
	}

	/**
	 * 生成微信/支付宝所需的商品描述_原生支付下的逻辑
	 * @param items
	 * @return
	 */
	private String genPaySubject(List<SoItemVO> items) {
		StringBuilder result = new StringBuilder();
		List<Long> puIds = new ArrayList<>();
		for (SoItemVO si : items) {
			puIds.add(si.getPuId());
		}
		// 根据puid集合查询pu信息
		List<CommodityProductUnitDTO> productUnitList = payCoreFacade.findByPUIds(puIds);
		for (CommodityProductUnitDTO commodityProductUnitDTO : productUnitList) {
			result.append(commodityProductUnitDTO.getName()).append(" ");
		}
		if(productUnitList==null || productUnitList.size()==0) {
			for (SoItemVO item : items) {
				if(item.getPuName()!=null) {
					result.append(item.getPuName()).append(" ");
				}
			}
		}
		String s = result.toString();
		if (s.length() > 10) {
			s = s.substring(0, 10) + "...";
		}
		return s;
	}

	@Override
	public boolean handleWxPayCallBack(String xmlStr, HttpServletRequest req) {
		PayWeixinLogDTO wxLog = null;
		Map<String,String> responseParam=null;
		try {
			responseParam = payUtil.parseResponseXml2Map(xmlStr);
			wxLog = ReflectUtil.map2JavaBean(responseParam, PayWeixinLogDTO.class, true);
		} catch (Exception e) {
			logger.error("handleWxPayCallBack",e);
			return false;
		}
		//appId校验
		String appId=wxLog.getAppid();
		// 签名平台 1:原生 2:h5 3:公众号 默认1
		int type = 1;
		//订单校验
		String orderCode=wxLog.getOutTradeNo();
		//删除三位随机尾缀
		orderCode=orderCode.substring(0, orderCode.length()-3);
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);;
		if (so==null){
			logger.error("微信回调订单不存在");
			return false;
		}
		String wxPrivateKey = null;
		PayUtil.WxPayConfig wxPayConfig=payUtil.wxPayConfigBySignPlatform(so.getSignPlatform(),so.getPlatformId());
		if (EmptyUtil.isNotEmpty(wxPayConfig)){
			wxPrivateKey=wxPayConfig.getPrivateKey();
		}
		if (EmptyUtil.isEmpty(wxPrivateKey)){
			// 根据appid不同处理修改
			if(payUtil.getProperty("wx.app.id.native").equals(appId)){
				wxPrivateKey = payUtil.getProperty("wx.private.key.native");
			}
			else if(payUtil.getProperty("wx.app.id").equals(appId)){
				wxPrivateKey = payUtil.getProperty("wx.private.key");
			}
			else if(payUtil.getProperty("myy.wx.app.id").equals(appId)){
				wxPrivateKey = payUtil.getProperty("myy.wx.private.key");
			}
		}
		//验签
		boolean signValid=payUtil.wxCallbackSignValidate(responseParam,wxPrivateKey);
		if(!signValid) {
			logger.error("微信回调验签失败");
			return false;
		}
		//回调状态校验
		String resultCode=wxLog.getResultCode();
		if(!"SUCCESS".equals(resultCode)) {
			logger.error("微信支付失败:状态码="+resultCode+",订单="+orderCode);
			AwaitQueueDTO awaitQueueDTO = new AwaitQueueDTO();
			// 根据订单id修改其订单等待队列中支付是否成功的值
			awaitQueueDTO.setSoId(so.getId());
			awaitQueueDTO.setIsPayTrue(0);
			awaitQueueFacade.updateAwaitQueueBySoIdWithTx(awaitQueueDTO);
			return false;
		}

		if (so.getOrderPayStatus() != OrderConstant.ORDER_STATUS_UNPAY.getStatus()) {
			//这个判断是防止已经成功回调的订单被多次回调,多次插入记录和更改订单支付信息
			return true;
		}

		// 插入日志,更改订单支付状态
		// cashPayType 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
		// companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
		payCoreFacade.insertWxCallBackLogAndOrderPayConfirm(wxLog, so,2,0, req);


		return true;
	}

	@Override
	public String genAliPayHtml(String orderCode) {
		if (EmptyUtil.isBlank(orderCode))
			return "请选择订单";
		SoDTO order = orderFacade.querySoByOrderCode(orderCode);
		if (order == null)
			return "订单不存在";
		// 校验订单支付状态
		if (order.getOrderStatus().intValue() == 1)
			return "订单已经支付";
		if (order.getOrderStatus().intValue() != 0)
			return "订单状态有误";
		// 校验订单支付超时
		Long createTime = order.getCreateTime().getTime();

		if (orderPayOutOfTime(createTime)) {
			orderFacade.updateOrderStatusAndUnlockStock(orderCode);
			return "订单支付超时";
		}

		BigDecimal orderAmount = BigDecimal.valueOf(0d);
		// 根据订单id查询订单冻结积分金额
		BigDecimal soFreezeBalance = payCoreFacade.findSoFreezeBalanceBySoId(order.getId());
		if (EmptyUtil.isNotEmpty(soFreezeBalance)) {
			// 订单冻结积分不为空，则订单总额减去冻结积分等于应付金额
			orderAmount =order.getOrderAmount().subtract(soFreezeBalance);
		} else {
			orderAmount = order.getOrderAmount();
		}

		// Long platformId = order.getPlatformId();
		List<SoItemDTO> orderItems = orderFacade.querySoItemBySoId(order.getId());
		if (orderItems.size() == 0)
			return "订单异常";
		String itemName = genPaySubject("", orderItems);
		/**
		 * 参考 https://docs.open.alipay.com/203/105285/
		 */
		String form=payUtil.aliBuildH5TradeRequest(orderCode,itemName,orderAmount);
		if("FAIL".equals(form)) {
			return "支付宝接口调用失败";
		}
		// 添加入支付等待队列
		AwaitQueueDTO awaitQueueDTO = new AwaitQueueDTO();
		awaitQueueDTO.setCashPayType(1);
		awaitQueueDTO.setOrderCode(orderCode);
		awaitQueueDTO.setSoId(order.getId());
		payCoreFacade.insertAwaitQueueWithTx(awaitQueueDTO,order.getOrderPayStatus());
		return form;
	}

	@Override
	public boolean handleAliPayCallBack(Map<String, String> params, HttpServletRequest req) {
		//log对象
		AliCallBackVo log_;
		try {
			log_ = ReflectUtil.map2JavaBean(params, AliCallBackVo.class, true);
		} catch (InstantiationException | IllegalAccessException | SecurityException e) {
			logger.error("handleAliPayCallBack",e);
			return false;
		}
		if (log_ == null)
			return false;
		PayAliLogDTO payLog = AliCallBackConverter.toDTO(log_);
		// 验签
		/**
		 * 调用支付宝sdk验签方法参考 https://docs.open.alipay.com/54/106370/ 中的JAVA服务端验证异步通知信息参数示例
		 * 验签失败可以参考 https://openclub.alipay.com/read.php?tid=1598&fid=46
		 */
		try {
			boolean signCheck = AlipaySignature.rsaCheckV1(params, payUtil.getProperty("ali.public.key"),
					payUtil.getProperty("ali.request.charset"), payUtil.getProperty("ali.sign.type"));
			if (!signCheck) {
				logger.error("验签失败");
				return false;
			}
		} catch (AlipayApiException e) {
			logger.error("handleAliPayCallBack",e);
			logger.error("验签失败");
			return false;
		}

		String appid = payLog.getAppId();
		if (!appid.equals(payUtil.getProperty("ali.app.id"))) {
			logger.error("支付宝appid不正确");
			return false;
		}
		String orderCode = payLog.getOutTradeNo();
		SoDTO order = orderFacade.querySoByOrderCode(orderCode);
		if (order == null) {
			logger.error("支付宝回调订单不存在");
			return false;
		}
		/*
		 * BigDecimal orderAccount = order.getOrderAmount(); BigDecimal totalAmount =
		 * log.getTotalAmount(); if (orderAccount.doubleValue() !=
		 * totalAmount.doubleValue()) { System.out.println("订单金额有误"); return false; }
		 */
		String tradeStatus = payLog.getTradeStatus();
		if (!tradeStatus.equals("TRADE_SUCCESS") && !tradeStatus.equals("TRADE_FINISHED")) {
			logger.error("支付宝回调状态不正确");
			AwaitQueueDTO awaitQueueDTO = new AwaitQueueDTO();
			// 根据订单id修改其订单等待队列中支付是否成功的值
			awaitQueueDTO.setSoId(order.getId());
			awaitQueueDTO.setIsPayTrue(0);
			awaitQueueFacade.updateAwaitQueueBySoIdWithTx(awaitQueueDTO);
			return false;
		}

		if (order.getOrderPayStatus() != OrderConstant.ORDER_STATUS_UNPAY.getStatus()) {
			//这个判断是防止已经成功回调的订单被多次回调,多次插入记录和更改订单支付信息
			return true;
		}

		// 实际应有第4步验证,但这里sellerId唯一,故略过
		/*
		 * 参考蚂蚁金服开发文档: 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方 （有的时候，一个商户可能有多个seller_id/seller_email），
		 * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过， 则表明本次通知是异常通知，务必忽略。
		 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理， 并且过滤重复的通知结果数据。在支付宝的业务通知中，
		 * 只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
		 *
		 * https://docs.open.alipay.com/203/105286/
		 */
		// 插入log记录
		// 更改交易状态
		// 完成资金流动
		payCoreFacade.insertAilCallBackLogAndOrderPayConfirm(payLog, order.getId(),req);
		return true;
	}


	/**
	 * 给会员赋予权限
	 * @return
	 */
	private void giveUserMembership(SoDTO order){
		Long soId=order.getId();
		SoItemDTO soItemDTO=soFacade.finSoItemBySoId(soId);
		//如果是会籍购买,为user付会籍权限
		if(order.getSaleWay()==5){
			MembershipDTO membershipDTO=soFacade.findMembershipBySkuId(soItemDTO.getSkuId(),order.getPlatformId());
			//是会籍商品购买
			Long membershipId = membershipDTO.getId();
			MembershipUserDTO membershipUserDTO = new MembershipUserDTO();

			Date start = new Date();
			//有效期单位
			int unit = membershipDTO.getValidPeriodUnit();
			//有效期值
			int val = membershipDTO.getValidPeriodVal();
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int mouth = now.get(Calendar.MONTH) + 1;
			int day = now.get(Calendar.DAY_OF_MONTH);
			Long value = 0L;
			switch (unit) {
				case 1:
					year = year + 1;
					break;
				case 2:
					mouth = mouth + 1;
					break;
				case 3:
					day = day + 1;
					break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String str = year + "" + mouth + day;
			Date end = new Date();
			try {
				end = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			membershipUserDTO.setStartTime(start);
			membershipUserDTO.setEndTime(end);
			membershipUserDTO.setUserId(order.getUserId());
			membershipUserDTO.setMembershipId(membershipId);
			membershipUserDTO.setStatusCode(1);
			soFacade.insertMembershipUser(membershipUserDTO);

		}
	}

//	/**
//	 * 签名验证
//	 *
//	 * @param params
//	 * @param prop
//	 * @return
//	 */
//	private boolean signCheck(Map<String, String> params, Properties prop) {
//		// Map<String,String> signMap=new HashMap<>();
//		String sign0 = params.get("sign");
//		System.out.println("sign0==" + sign0);
//		StringBuilder signContent = new StringBuilder();
//		String charset = payUtil.getProperty("ali.request.charset");
//		String signType = payUtil.getProperty("ali.sign.type");
//		signContent.append("alipay_sdk=").append("alipay-sdk-java-dynamicVersionNo").append("&").append("app_id=")
//				.append(params.get("app_id")).append("&").append("biz_content=")
//				.append("{ \"out_trade_no\":\"" + params.get("out_trade_no") + "\", \"total_amount\":\""
//						+ params.get("total_amount") + "\", \"subject\":\"" + params.get("subject")
//						+ "\", \"product_code\":\"QUICK_WAP_PAY\" }")
//				.append("&").append("charset=").append(charset).append("&").append("format=")
//				.append(payUtil.getProperty("ali.request.content.type")).append("&").append("method=")
//				.append("alipay.trade.wap.pay").append("&").append("notify_url=")
//				.append(payUtil.getProperty("ali.notify.url")).append("&").append("return_url=")
//				.append(payUtil.getProperty("ali.return.url")).append("&").append("sign_type=").append(signType)
//				.append("&").append("timestamp=").append(params.get("gmt_create")).append("&").append("version=")
//				.append(params.get("version"));
//		try {
//			System.out.println("callBackSignContent==" + signContent);
//			String sign1 = AlipaySignature.rsaSign(signContent.toString(), aliPayPrivateKey, charset, signType);
//			System.out.println("sign1==" + sign1);
//			return sign1.equals(sign0);
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	@Override
	public JsonResult<StandardOrderQueryResultVO> standardOrderQuery(String orderCode) {
		//查询订单
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		/*if(so.getOrderPayStatus()!=0) {
			return JsonResult.fail("订单不是待支付订单");
		}
		if(so.getOrderConfirmStatus()==2) {
			return JsonResult.fail("订单已取消,请重新提交订单");
		}
		if(so.getOrderConfirmStatus()==3) {
			return JsonResult.fail("订单已完成");
		}*/
		if(so.getOrderConfirmStatus()==3||so.getOrderConfirmStatus()==2||so.getOrderPayStatus()!=0){
			return JsonResult.fail("该订单支付状态异常",BusinessExceptionConstant.ORDER_STATUS_EXCEPTION);
		}

		//先简单处理
		StandardOrderQueryResultVO res=new StandardOrderQueryResultVO();
		//查询订单冻结的积分数量,为空则视为冻结了0
		Long soId=so.getId();
		BigDecimal soFreezeBalance=orderFacade.findSoFreezeBalanceBySoId(soId);
		if(soFreezeBalance==null) {
			soFreezeBalance=BigDecimal.valueOf(0.0);
		}
		if(so.getLimitFuBiPayAmount().compareTo(soFreezeBalance)>0){
			return JsonResult.fail("存在仅积分支付商品");
		}
		//待付款现金金额为订单总价-冻结数量
		BigDecimal orderAmount=so.getOrderAmount().subtract(so.getOrderPromotionDiscount());
		BigDecimal needPayCash=orderAmount.subtract(soFreezeBalance);
		if(needPayCash.compareTo(BigDecimal.valueOf(0.0))!=1)
			return JsonResult.fail("订单需付现金为0");
		res.setNeedPayCash(needPayCash);
		res.setOrderCode(so.getOrderCode());
		res.setSoId(soId);
		res.setOrderPayStatus(so.getOrderPayStatus());
		//查询订单项列表
		List<SoItemDTO> items=orderFacade.querySoItemBySoId(soId);
		List<SoItemVO> itemVOs=SoItemConverter.toVO(items);
		res.setItems(itemVOs);
		return JsonResult.success(res);
	}

	@Override
	public JsonResult<Map<String, Object>> nativeAlipaySign(StandardOrderQueryResultVO order,Integer signPlatform,HttpServletRequest req,HttpServletResponse rse) {
		try {
			//变更订单现金支付方式,插入等待队列
			payCoreFacade.orderPaySigned(order, 1, null, PayUtil.SIGN_PLATFORM_NATIVE);
		}catch (Exception e){
			logger.info("获取签名加入支付等待队列异常,e:",e);
			throw e;
		}finally {
			//加入等待队列后解锁订单
			Long aLong = jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+order.getOrderCode());
			logger.info("解锁成功,结果:"+aLong);
		}


		SoDTO so=orderFacade.querySoByOrderCode(order.getOrderCode());
		Integer companyType = userFacade.findCompanyTypeByUserId(so.getUserId());
		if(companyType != 0){
			// 插入日志,更改订单支付状态
			// cashPayType 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 5 跳过
			// companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
			payCoreFacade.insertWxCallBackLogAndOrderPayConfirm(null, so,5,companyType, req);
			//返回生成的签名信息
			Map<String, Object> map = new HashMap<>();
			map.put("pay", false);
			return JsonResult.success(map);
		}else{
			String itemName = genPaySubject(order.getItems());

			switch (signPlatform) {
			case 1:
				//构建支付宝下单请求,并获取响应值
				String responseSign=payUtil.aliBuildTradeRequest(order.getOrderCode(),itemName,order.getNeedPayCash());
				//当该值为FAIL时,表明请求失败
				if(responseSign==null) {
					return JsonResult.fail("支付宝下单请求无响应");
				}
				if("FAIL".equals(responseSign)) {
					return JsonResult.fail("支付宝下单请求失败");
				}
				//AlipayNativeSignLogDTO signLog=responseJSON2SignLog(responseJSON);
				//插入日志
				//payCoreFacade.insertAlipayNativeSignLog(signLog);

				//返回生成的签名信息
				Map<String, Object> map = new HashMap<>();
				map.put("signResult", responseSign);
				map.put("pay", true);
				return JsonResult.success(map);
			case 5:
				//构建支付宝下单请求,并获取响应值
				try {
					payUtil.webAlipaySign(order.getOrderCode(),itemName,order.getNeedPayCash(),req,rse);
				} catch (Exception e) {
					return JsonResult.fail("构建支付宝支付失败，" + e.getMessage());
				}
				return JsonResult.success("pay",true);

			default:
				return JsonResult.fail("支付宝下单请求失败");
			}
		}

	}

//	/**
//	 * 将alipay签名响应结果转为log对象
//	 * @param responseJSON
//	 * @return
//	 */
//	private AlipayNativeSignLogDTO responseJSON2SignLog(JSONObject responseJSON) {
//		AlipayNativeSignLogDTO res=new AlipayNativeSignLogDTO();
//		res.setSign(responseJSON.getString("sign"));
//		JSONObject subObject=responseJSON.getJSONObject("alipay_trade_app_pay_response");
//		res.setCode(subObject.getString("code"));
//		res.setMsg(subObject.getString("msg"));
//		res.setOutTradeNo(subObject.getString("out_trade_no"));
//		res.setSellerId(subObject.getString("seller_id"));
//		res.setSubCode(subObject.getString("sub_code"));
//		res.setSubMsg(subObject.getString("sub_msg"));
//		res.setTotalAmount(subObject.getString("total_amount"));
//		res.setTradeNo(subObject.getString("trade_no"));
//		return res;
//	}

	@Override
	public JsonResult<Map<String, Object>> standardOrderQueryAtPayInvoke(String clientId, String orderCode,Integer payType, HttpServletRequest req) {
		if(EmptyUtil.isBlank(orderCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		if (Objects.equals(so.getOrderStatus(),OrderConstant.ORDER_STATUS_CANCELED.getStatus())){
			return JsonResult.fail("订单已取消");
		}
		//超时判断
		long createTime=so.getCreateTime().getTime();
		long currTime=System.currentTimeMillis();
		//30*60*1000
		if(currTime-createTime>=PayUtil.ORDER_EXIST_TIME_MS) {
			return JsonResult.fail("订单支付超时",BusinessExceptionConstant.ORDER_EXPIRED);
		}
		//清美订单是否支付超时
		if (EmptyUtil.isNotEmpty(so.getExt())){
			QmOrderDTO qmOrderDTO=orderFacade.queryQmOrderBySoId(so.getId());
			if(Objects.nonNull(qmOrderDTO) && Objects.nonNull(qmOrderDTO.getExpireTime())) {
				if (qmOrderDTO.getExpireTime().before(new Date())){
					return JsonResult.fail("订单支付超时",BusinessExceptionConstant.ORDER_EXPIRED);
				}
			}
		}

		//查询订单状态
		Integer payStatus=so.getOrderPayStatus();
		if(payStatus==0) {
			//未付款
			//查询订单是否存在于等待队列
			AwaitQueueDTO queue=payCoreFacade.queryQueueBySoId(so.getId());
			if(queue==null) {
				//不存在队列
				//解冻订单积分
				unfreeseSoFubiBy(so);
				//将订单总额,应支付积分金额,应支付现金金额添加至返回值
				Map<String,Object> res=new HashMap<>();
				BigDecimal allDiscount = so.getOrderPromotionDiscount();
				logger.info("alldiscount" +allDiscount);
				BigDecimal orderAmount= so.getOrderAmount().subtract(allDiscount);
				logger.info("总金额(运费+商品金额-总优惠)orderAmount:"+orderAmount);
				UserDTO userDTO = userReadService.findUserByID(so.getUserId());
				//查询用户可用余额:积分账户余额-冻结积分金额
				UserAccountDTO uaFubi=accountFacade.queryUserAccountByUserIdAndType(so.getUserId(),0);

				BigDecimal jidian = BigDecimal.ZERO;
				boolean isDlfUser = false;
				if(EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && Objects.equals(userDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
					isDlfUser = true;
					UserAccountDTO jiDianAcc= accountFacade.queryUserAccountByUserIdAndType(so.getUserId(),5);
					if(jiDianAcc ==null){
						return JsonResult.fail("用户积点账户未就绪");
					}
					logger.info("德律风支付时查询到积点:{}", JSON.toJSONString(jiDianAcc));
					jidian = jiDianAcc.getBalance();
				}

				if(uaFubi==null) {
					return JsonResult.fail("用户积分账户未就绪");
				}
				UserAccountDTO uaFreeze=accountFacade.queryUserAccountByUserIdAndType(so.getUserId(), 2);
				if(uaFreeze==null) {
					return JsonResult.fail("用户冻结账户未就绪");
				}

				UserAccountDTO userFdAccountDTO = accountFacade.queryUserAccountByUserIdAndType(so.getUserId(), 4);
				/*if(userFdAccountDTO==null) {
					return JsonResult.fail("用户退款账户未就绪");
				}*/
				//用户可用积分
				BigDecimal useableFubi=uaFubi.getBalance();
				//应付积分计算逻辑:积分够用则为订单总额,积分不足则为剩余积分金额
				BigDecimal needPayFubi=BigDecimal.valueOf(0);
				BigDecimal limitCashPayAmount=so.getLimitCashPayAmount();
				needPayFubi = so.getOrderAmountPay().subtract(limitCashPayAmount);
				logger.info("唤起支付接口时,user是否德律风用户{},可用余额：{},退款账户:{}",isDlfUser,useableFubi,userFdAccountDTO !=null?userFdAccountDTO.getBalance():null);
				if(!isDlfUser){
					if(userFdAccountDTO !=null){
						useableFubi = useableFubi.add(userFdAccountDTO.getBalance());
					}
					useableFubi=useableFubi.subtract(uaFreeze.getBalance());
					if(needPayFubi.compareTo(useableFubi)<0){
						useableFubi = needPayFubi;
					}
				}

				//若是德律风，余额是无需减扣冻结金额的
				useableFubi = isDlfUser?uaFubi.getBalance():useableFubi;
				logger.info("唤起支付接口时,user是否德律风用户{},可用余额：{}",isDlfUser,useableFubi);
				if(isDlfUser){
					if(payType ==null){
						return JsonResult.fail("支付类型不能为空");
					}
					 if(payType.intValue() ==0 && useableFubi.compareTo(needPayFubi)<0){
						 logger.error("餐卡余额不足,{},{}",needPayFubi,useableFubi);
							return JsonResult.fail("餐卡余额不足");
					}else if(payType.intValue() ==5 && jidian.compareTo(needPayFubi)<0){
					 	logger.error("积点余额不足,{},{}",needPayFubi,jidian);
							return JsonResult.fail("积点余额不足");
					}
				}
				//商品总数量
				//查询订单项列表
				List<SoItemDTO> items=orderFacade.querySoItemBySoId(so.getId());
				int goodsAccount=0;
				for(SoItemDTO item:items) {
					goodsAccount+=item.getPuCount();
				}
				//订单总额
				res.put("orderAmount", orderAmount);
				logger.info("orderAmount:"+orderAmount);
				//商品总数
				res.put("goodsAccount", goodsAccount);
				logger.info("goodsAmount:"+goodsAccount);
				//积分余额
				res.put("fubiBalance", useableFubi);
				logger.info("useableFubi:"+useableFubi);
				//应付现金计算方法:订单总额-应付积分,若是德律风不支持组合支付，因此需要支付订单全额的现金
				BigDecimal needPayCash = !isDlfUser?so.getOrderAmountPay().subtract(useableFubi):so.getOrderAmountPay();
				res.put("needPayCash", needPayCash);
				logger.info("needPaycash:"+needPayCash);
				/*res.put("needPayCash", so.getOrderAmountPay().subtract(useableFubi));
				logger.info("needPaycash:"+so.getOrderAmountPay().subtract(useableFubi));*/
				//积点余额
				res.put("jiDianBalance", jidian);
				logger.info("useableJiDian:"+jidian);
				if(clientId.equals("4")&&orderAmount.compareTo(useableFubi)>0){
					return JsonResult.fail("您当前积分账户余额不足，请联系管理员充值");
				}
				return JsonResult.success(res);
			}else {
				//存在队列
				return JsonResult.fail("订单在支付等待队列中",BusinessExceptionConstant.ORDER_IN_QUEUE);
			}
		}else {
			//非未付款
			//判断订单确认状态
			Integer confirmStatus=so.getOrderConfirmStatus();
			if(confirmStatus==2) {
				//确认状态是已取消
				return JsonResult.fail("订单已取消",BusinessExceptionConstant.ORDER_CANCELED);
			}else {
				//确认状态非已取消
				if(payStatus==1) {
					//订单支付状态为已支付时
					return JsonResult.fail("订单已经成功支付",BusinessExceptionConstant.ORDER_PAID);
				}else {
					//订单支付状态为已退款时
					return JsonResult.fail("订单已退款",BusinessExceptionConstant.ORDER_REFUND);
				}
			}
		}
	}

	@Override
	public JsonResult<Map<String, Object>> standardPayCancle(String orderCode, Long userId) {
		//只能取消自己的订单
		if(EmptyUtil.isBlank(orderCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		if(so.getUserId().longValue()!=userId.longValue()){
			return JsonResult.fail("只能取消自己的订单");
		}
		JsonResult<Map<String, Object>> res=null;
		//订单现金支付方法
		Integer cashPayType=so.getCashPayType();
		if(cashPayType!=null) {
			switch (cashPayType) {
			case 1:
				//支付宝
				res= cancleAlipay(so);
				break;
			case 2:
				//微信
				res= cancleWxPay(so);
				break;
			default:
				//其他
				return JsonResult.fail("支付方式未定义");
			}
			if(res.getCode()!=0) {
				//存在异常返回值
				return res;
			}
		}


		//取消队列
		payCoreFacade.deleteQueueBySoId(so.getId());

		//解除订单的积分冻结
		unfreeseSoFubiBy(so);
		//取消支付成功,解锁订单
		Long aLong = jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+so.getOrderCode());
		logger.info(so.getOrderCode()+"解锁成功,结果:"+aLong+";orderCode="+so.getOrderCode());
		return JsonResult.success();
	}


	@Override
	public JsonResult<Map<String, Object>> cashPayCancel(String orderCode, Long userId) {
		//只能取消自己的订单
		if(EmptyUtil.isBlank(orderCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		if(so.getUserId().longValue()!=userId.longValue()){
			return JsonResult.fail("只能取消自己的订单");
		}
		//订单现金支付方法
		Integer cashPayType=so.getCashPayType();
		if(cashPayType==null) {
			return JsonResult.fail("未使用现金支付");
		}
		JsonResult<Map<String, Object>> res=null;
		switch (cashPayType) {
		case 1:
			//支付宝
			res= cancleAlipay(so);
			break;
		case 2:
			//微信
			res= cancleWxPay(so);
			break;
		default:
			//其他
			return JsonResult.fail("支付方式未定义");
		}
		if(res.getCode()!=0) {
			//存在异常返回值
			return res;
		}
		//取消队列
		payCoreFacade.deleteQueueBySoId(so.getId());
		return JsonResult.success();
	}

	/**
	 * 解冻订单积分
	 * @param so
	 */
	private void unfreeseSoFubiBy(SoDTO so) {
		orderFacade.cancelSoFreezeFubi(so.getId(),so.getUserId());
	}

	/**
	 * 微信支付取消支付方法
	 * @param so
	 * @return
	 */
	private JsonResult<Map<String, Object>> cancleWxPay(SoDTO so) {
		//查询队列,获取微信支付传递的订单号和随机尾缀
		AwaitQueueDTO queue=payCoreFacade.queryQueueBySoId(so.getId());
		if(queue==null) {
			// return JsonResult.fail("订单队列已取消");
			return JsonResult.success();
		}
		String random=queue.getRandomNumber();
		String code=so.getOrderCode()+random;
		//交易查询
		String err=payUtil.wxQuerySub(code, so.getPlatformId());
		if(err!=null) {
			return JsonResult.fail(err);
		}
		//取消支付
		err=payUtil.wxCancelSub(code, so.getPlatformId());
		if(err!=null) {
			return JsonResult.fail(err);
		}

		//删除队列
		payCoreFacade.deleteQueueBySoId(so.getId());
		return JsonResult.success();
	}

	/**
	 * 支付宝取消支付方法
	 * @param so
	 * @return
	 */
	private JsonResult<Map<String, Object>> cancleAlipay(SoDTO so) {
		//提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】
		/*
		 * 查询订单api
		 * 参考https://docs.open.alipay.com/api_1/alipay.trade.query/
		 */
		String err=payUtil.alipayQuerySub(so.getOrderCode());
		if(err!=null) {
			return JsonResult.fail(err);
		}
		err=payUtil.alipayCloseSub(so.getOrderCode());
		if(err!=null) {
			return JsonResult.fail(err);
		}
		//删除队列
		payCoreFacade.deleteQueueBySoId(so.getId());
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> standardOrderQueryAtPayMethod(String orderCode) {
		if(EmptyUtil.isBlank(orderCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoDTO so=orderFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		//超时判断
		long createTime=so.getCreateTime().getTime();
		long currTime=System.currentTimeMillis();
		//30*60*1000
		if(currTime-createTime>=PayUtil.ORDER_EXIST_TIME_MS) {
			return JsonResult.fail("订单支付超时",BusinessExceptionConstant.ORDER_EXPIRED);
		}
		//订单支付状态
		Integer payStatus=so.getOrderPayStatus();
		if(payStatus==1) {
			//已支付
			return JsonResult.fail("订单已支付",BusinessExceptionConstant.ORDER_PAID);
		}else {
			//查询队列
			AwaitQueueDTO queue=payCoreFacade.queryQueueBySoId(so.getId());
			if(queue!=null) {
				return JsonResult.fail("订单在支付等待队列中",BusinessExceptionConstant.ORDER_IN_QUEUE);
			}else {
				//返回剩余支付时间
				Long timeLeft = createTime + PayUtil.ORDER_EXIST_TIME_MS-currTime;
				if (timeLeft < 0) {
					timeLeft=0l;
				}
				Map<String, Object> data = new HashMap<>();
				data.put("timeLeft", timeLeft/1000);
				//对h5端返回订单总额,商品总数量,已冻结的积分额

				List<SoItemDTO> items=orderFacade.querySoItemBySoId(so.getId());
				int goodsAccount=0;
				for(SoItemDTO item:items) {
					goodsAccount+=item.getPuCount();
				}
				data.put("goodsAccount", goodsAccount);
				BigDecimal ffb=accountFacade.querySoFreezeFubi(so.getId());
				if(ffb==null) {
					ffb=new BigDecimal("0.00");
				}
				if(so.getLimitFuBiPayAmount().compareTo(ffb)>0){
					return JsonResult.fail("当前存在仅积分购买的商品");
				}
				data.put("frozenFubi", ffb);
				//修改原有逻辑知己获取so中的orderamount,改成orderamount-优惠总金额-积分支付金额
				int i = so.getOrderAmount().subtract(so.getOrderPromotionDiscount()).subtract(ffb).compareTo(BigDecimal.ZERO);
				//付款金额不能小于0
				if(i<0){
					return JsonResult.fail("实付金额不能小于0");
				}
				data.put("orderAmount", so.getOrderAmount().subtract(so.getOrderPromotionDiscount()).subtract(ffb));
				return JsonResult.success(data);
			}
		}
	}

	@Override
	public SoDTO queryOrderByOrderCode(String orderCode) {
		return orderFacade.querySoByOrderCode(orderCode);
	}

	@Override
	public UserDTO queryUserById(Long userId) {
		return accountFacade.queryUserById(userId);
	}
/*
	public static void main(String[] args) {
		Map<String,String> secSignMap=new HashMap<>();
		secSignMap.put("prepayid", "wx05111751726482f0d92c19f83113129437");
		secSignMap.put("appid", "wx6968f76d891d8736");
		secSignMap.put("partnerid", "1302535401");
		String nonstr=UUID.uuid();
		String timestamp=String.valueOf(System.currentTimeMillis()/1000l);
		secSignMap.put("noncestr", nonstr);
		secSignMap.put("timestamp", timestamp);
		secSignMap.put("package", "Sign=WXPay");
		String sign = payUtil.wxSign(secSignMap, "d0c07fe05e5748108f001ca3f3f4f241");
		System.out.println(sign);
	}*/

	@Override
	public JsonResult<Map<String, Object>> scanCodeAlipaySign(StandardOrderQueryResultVO order) {
		//变更订单现金支付方式,插入等待队列
		payCoreFacade.orderPaySigned(order,1,null,PayUtil.SIGN_PLATFORM_NATIVE);
		String itemName = genPaySubject(order.getItems());
		//构建支付宝下单请求,并获取响应值
		String responseSign=payUtil.scanCodeAlipaySign(order.getOrderCode(),itemName,order.getNeedPayCash());
		//当该值为FAIL时,表明请求失败
		if(responseSign==null) {
			return JsonResult.fail("支付宝下单请求无响应");
		}
		if("FAIL".equals(responseSign)) {
			return JsonResult.fail("支付宝下单请求失败");
		}
		//AlipayNativeSignLogDTO signLog=responseJSON2SignLog(responseJSON);
		//插入日志
		//payCoreFacade.insertAlipayNativeSignLog(signLog);

		//返回生成的签名信息
		return JsonResult.success("signResult",responseSign);
	}

	/**
	 * 校验微信APPId是否正确
	 * @param userId
	 * @param appId
	 * @return
	 */
	private boolean checkWxAppId(Long userId,String appId,String openId){
		WxOpenidDTO wxOpenid = new WxOpenidDTO();
		wxOpenid.setUserId(userId);
		wxOpenid.setWxOpenid(openId);
		List<WxOpenidDTO> wxOpenidDTOs = userReadService.queryWxOpenid(wxOpenid);
		if(wxOpenidDTOs!=null && wxOpenidDTOs.size()>0) {
			for (WxOpenidDTO openidDTO:wxOpenidDTOs){
				if (Objects.equals(appId,openidDTO.getWxAppid())){
					return true;
				}
			}
		}
		return false;
	}
}
