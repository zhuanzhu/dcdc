package com.egeo.components.pay.facade;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.client.CardClient;
import com.egeo.components.order.client.MerchantProdSalesRecordCoreClient;
import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.client.SoRefundClient;
import com.egeo.components.order.dto.CancelAndRefundOrderWithTxDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.pay.client.AwaitQueueClient;
import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.dto.PayAliLogDTO;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.pay.service.write.AlipayNativeSignLogWriteService;
import com.egeo.components.pay.service.write.AwaitQueueWriteService;
import com.egeo.components.pay.service.write.PayAliLogWriteService;
import com.egeo.components.pay.service.write.PayWeixinLogWriteService;
import com.egeo.components.pay.vo.StandardOrderQueryResultVO;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.client.MerchantProdSalesRecordClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.client.ExchangeOrderRecordClient;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.pay.PayUtil;

@Component
public class PayCoreFacade {

	XLogger logger = XLogger.getLogger(this.getClass().getName());

	@Resource
	private PayWeixinLogWriteService payWeixinLogWriteService;

	@Resource
	private PayAliLogWriteService payAliLogWriteService;

	@Autowired
	private SoClient soClient;

	@Autowired
	private SoClient soWriteService;

	@Autowired
	private SoItemClient soItemClient;

	@Autowired
	private ECardClient eCardWriteService;

	@Autowired
	private ECardClient eCardClient;

	@Autowired
	private SoFreezeFubiClient soFreezeFubiClient;

	@Autowired
	private SaltClient saltClient;

	@Autowired
	private UserAccountClient userAccountClient;

	@Autowired
	private CompanyAccountClient caClient;

	@Autowired
	private AccountBatchClient accountBatchWriteService;

	@Autowired
	private UserAccountClient userAccountWriteService;

	@Autowired
	private SoFreezeFubiClient soFreezeFubiWriteService;

	@Resource
	private AwaitQueueWriteService awaitQueueWriteService;

	@Resource
	private AwaitQueueClient awaitQueueClient;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitClient;

	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordWriteService;

	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordClient;

	@Autowired
	private SoChildClient soChildWriteService;

	@Resource
	private AlipayNativeSignLogWriteService alipayNativeSignLogWriteService;

	@Autowired
	private CouponUnitClient couponUnitWriteService;

	@Autowired
	private SoRefundClient soRefundClient;

	@Autowired
	private SoClient soService;

	@Autowired
	private CardClient cardWriteService;

	@Autowired
	private MerchantProdSalesRecordCoreClient merchantProdSalesRecordCoreWriteService;

	@Autowired
	private CompanyCoreClient companyCoreClient;

	@Autowired
	private SendInfoClient sendInfoWriteService;

	@Autowired
	private UserClient userClient;

	@Autowired
	private MembershipUserClient membershipUserWriteService;

	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordWriteService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordClient;
	@Autowired
	private SoChildClient soChildClient;



	@Autowired
	private PayUtil payUtil;


	/**
	 * 插入微信返回信息日志
	 *
	 */
	public Long insertWxPayLog(PayWeixinLogDTO log) {
		return payWeixinLogWriteService.insertPayWeixinLogWithTx(log);
	}

	/**
	 * 微信和演示竞品公司 插入日志并变更订单支付状态
	 *
	 * @param so
	 */
	public void insertWxCallBackLogAndOrderPayConfirm(PayWeixinLogDTO log, SoDTO so,Integer cashPayType,Integer companyType, HttpServletRequest req) {
		logger.info("微信回调test1");
		Long orderId = so.getId();
		Long platformId = so.getPlatformId();
		if(EmptyUtil.isNotEmpty(log)){
			log.setPlatformId(platformId);
			payWeixinLogWriteService.insertPayWeixinLogWithTx(log);
		}
		SoDTO orderUpdateCondition = new SoDTO();
		orderUpdateCondition.setId(orderId);
		orderUpdateCondition.setOrderCode(so.getOrderCode());
		orderUpdateCondition.setOrderPaymentConfirmDate(new Date());
		orderUpdateCondition.setOrderStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		// 支付确认类型：0:用户支付确认 1:配送回款确认
		orderUpdateCondition.setOrderPaymentConfirmType(0);
		orderUpdateCondition.setCashPayType(cashPayType);
		// 更改确认状态
		List<SoChildDTO> soChildDTOList = soChildClient.querySoChildListBySoId(orderId);
		Boolean flag = false;
		for(SoChildDTO childDTO:soChildDTOList){
			if(childDTO.getThirdpartyType()==3){
				flag = true;
			}
		}
		if(flag){
			orderUpdateCondition.setOrderConfirmStatus(0);
		}else{
			orderUpdateCondition.setOrderConfirmStatus(1);
		}
		//为用户分配会籍权限
		if(so.getSaleWay()==5){
			giveMembership(so);
		}

		// 更改订单支付状态
		orderUpdateCondition.setOrderPayStatus(1);
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiClient.findSoFreezeBalanceBySoId(orderId);
		// 订单积分冻结标志:订单冻结积分存在且大于0
		boolean soFreezeFubiFlag = soFreezeBalance != null && soFreezeBalance.compareTo(new BigDecimal("0.00")) > 0;

		// 设置现金支付金额
		BigDecimal soPaidByCash = null;
		String totalFee = payUtil.wxOrderQueryRequest(log.getOutTradeNo(), so.getPlatformId());
		if (!totalFee.equals("FAIL")) {
			soPaidByCash = new BigDecimal(totalFee).divide(new BigDecimal("100"));
		}
		orderUpdateCondition.setOrderPaidByCash(soPaidByCash);
		//设置支付方式(微信)
		orderUpdateCondition.setCashPayType(Integer.valueOf(2));

		soWriteService.updateOrderPaymentInfo(orderUpdateCondition);

		// 根据订单id删除订单等待队列
		awaitQueueWriteService.delByOrderIdWithTx(orderId);

		InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
		vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
		vo1.setOrderCode(so.getOrderCode());
		vo1.setOrderPayStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		vo1.setUserId(so.getUserId());
		// 发送订单支付状态变更消息
		sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);

		// 用户积分余额扣除支付部分,积分冻结金额取消,订单冻结积分取消
		paymentFubi(orderId, so.getOrderCode(), so.getUserId(), so.getPlatformId(), soFreezeFubiFlag, soFreezeBalance);
		// 发送积分变更消息
		if(soFreezeFubiFlag) {
			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
			Map<String,String> infoMap = new HashMap<String,String>();
			infoDto.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
			infoDto.setUserId(so.getUserId());
			infoMap.put("changeFuBi", soFreezeBalance==null?"0":soFreezeBalance.toPlainString());
			infoMap.put("changeCause", AccountConstant.FUBI_CHANGE_BUY_SU);
			infoDto.setParams(infoMap);
			sendInfoWriteService.insertUserFuBiInfoAndSend(infoDto);
		}
		paymentCash(orderId, so.getOrderCode(), so.getUserId(), platformId, soPaidByCash);
		// 分配unit
		//根据userid查询username
		UserDTO userByID = userClient.findUserByID(so.getUserId());
		if(EmptyUtil.isEmpty(userByID)){
			throw new BusinessException(so.getUserId() + "不存在");
		}
		//为卡圈分配卡号卡密
		cardWriteService.allocationCardAndTakeStock(orderId, so.getOrderCode(), so.getUserId(), userByID.getName(), "", "", companyType);

		// 增加订单对应pu相应的销量
		merchantProdSalesRecordCoreWriteService.recordSalesVolume(so.getId());

		//进行以旧换新操作
		if(so.getSaleWay().equals(8)){
			exchangeAction(so,companyType,userByID.getName(),userByID.getId(),req);
		}


		/*// 根据订单id查询订单项
		SoItemDTO soItemDTO = new SoItemDTO();
		soItemDTO.setSoId(orderId);
		List<SoItemDTO> unitItemList = soItemClient.findAll(soItemDTO);
		for (SoItemDTO soItemDTO3 : unitItemList) {
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(soItemDTO3.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitClient
					.findCommodityProductUnitById(commodityProductUnitDTO);
			// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
			// 根据puid扣除冻结库存扣除库存
			commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(soItemDTO3.getPuId(),
					soItemDTO3.getPuCount(), StockConstant.STOCK_STATUS_PAYED.getStatus(),
					commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),so.getOrderCode(),null,null,null,null);
		}*/

		// 订单支付宝支付成功,判断订单的第三方订单的类型,并进行相应操作
		boolean isSuccessDealThirdpartyOrder = true;
		if(companyType == 0)
			isSuccessDealThirdpartyOrder = soService.dealThirdpartyOrderWithTx(userByID.getName(),userByID.getId(),orderId, so.getOrderCode());

		if (isSuccessDealThirdpartyOrder) {
			// 支付成功,且第三方订单处理成功,若使用了优惠卷,优惠卷变为已使用
			couponUnitWriteService.updateCouponByPaySuccessWithTx(orderId);
		} else {
			// 生成退款单编号,以防第三方话费充值失败需取消订单自动退款
			List<String> soRefundNOList = soRefundClient.genSoRefundNO();
			// 支付成功,但第三方订单处理失败,取消订单自动退款
			List<SoItemDTO> items = soItemClient.querySoItemListBySoId(so.getId());
			SoDTO soById = soClient.findSoById(so.getId());
			soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userByID.getName(),soById, items, userByID.getId(), soRefundNOList.get(0),
					soRefundNOList.get(1), new HttpServletRequestDTO(req)));
		}

	}

	//进行以旧换新
	private void exchangeAction(SoDTO soDTO,Integer companyType,String userName,Long userId,HttpServletRequest req){
		//进行以旧换新操作
		if(soDTO.getSaleWay().equals(8)){
			boolean isSuccessExchange=true;
			if(companyType==0){
				isSuccessExchange=soService.exchangeOrderWithTx(soDTO.getId(),soDTO.getOrderCode(),userName,userId);
				logger.info("以旧换新结果:"+isSuccessExchange);
			}
			if(!isSuccessExchange){
				//取消订单成功,如果是以旧换新更新相关记录
					ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
					recordDTO.setConversionStatus(Integer.valueOf(2));
					recordDTO.setOrderCode(soDTO.getOrderCode());
					int rt=exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);

				//如果是以旧换新订单需要释放旧unit
				List<ExchangeOrderRecordDTO> exchangeOrderRecordByOrderCode = exchangeOrderRecordClient.findExchangeOrderRecordAllByOrderCode(soDTO.getOrderCode());
				if(EmptyUtil.isEmpty(exchangeOrderRecordByOrderCode)||exchangeOrderRecordByOrderCode.size()>1){
					logger.info("当前订单对应的以旧换新记录有误,orderCode="+soDTO.getOrderCode());
					throw new BusinessException("当前订单对应的以旧换新记录有误,请联系管理员");
				}
				JedisUtil jedisUtil = new JedisUtil();
				jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
				//int i = couponUnitWriteService.updateCouponUnitRemoveLock(exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
				logger.info("订单取消,释放unit锁");



				//以旧换新失败

				// 生成退款单编号
				List<String> soRefundNOList = soRefundClient.genSoRefundNO();
				// 支付成功,但以旧换新失败,取消订单自动退款
				List<SoItemDTO> items = soItemClient.querySoItemListBySoId(soDTO.getId());
				SoDTO soById = soClient.findSoById(soDTO.getId());
				soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName,soById, items, userId, soRefundNOList.get(0),
						soRefundNOList.get(1), new HttpServletRequestDTO(req)));
			}
		}
	}



//为用户分配会籍
	private void giveMembership(SoDTO so) {
		List<Long> puIdList=com.egeo.utils.StringUtils.stringsToLongs(soItemClient.findPuIdBySoId(so.getId()));
		if(puIdList.size()!=1){
			logger.info("[会籍购买,订单对应的puId有误]puIdList:"+puIdList);
			throw new BusinessException("该订单对应的商品有误请联系管理员");
		}
		//根据puId查询skuId
		Long skuId=commodityProductUnitClient.findSkuIdByPuId(puIdList.get(0));
		if(EmptyUtil.isEmpty(skuId)){
			logger.info("[会籍购买,订单对应的skuId有误]skuId:"+skuId);
			throw new BusinessException("该订单对应的sku有误请联系管理员");
		}
		membershipUserWriteService.giveUserMembershipByOrder(so.getUserId(),so.getPlatformId(),skuId);
	}

	/**
	 * map转Log对象
	 *
	 * @param responseParams
	 * @return
	 */
	@Deprecated
	private PayWeixinLogDTO paramMap2WxPayLog(Map<String, String> responseParams) {
		PayWeixinLogDTO log = new PayWeixinLogDTO();
		log.setResultCode(responseParams.get("return_code"));
		log.setReturnMsg(responseParams.get("return_msg"));
		log.setAppid(responseParams.get("appid"));
		log.setMchId(responseParams.get("mch_id"));
		log.setDeviceInfo(responseParams.get("device_info"));
		log.setNonceStr(responseParams.get("nonce_str"));
		log.setSign(responseParams.get("sign"));
		log.setSignType(responseParams.get("sign_type"));
		log.setResultCode(responseParams.get("result_code"));
		log.setErrCode(responseParams.get("err_code"));
		log.setErrCodeDes(responseParams.get("err_code_des"));
		log.setOpenid(responseParams.get("openid"));
		log.setIsSubscribe(responseParams.get("is_subscribe"));
		log.setTradeType(responseParams.get("trade_type"));
		log.setBankType(responseParams.get("bank_type"));
		String total_fee = responseParams.get("total_fee");
		if (total_fee != null)
			log.setTotalFee(Long.parseLong(total_fee));
		String cash_fee = responseParams.get("cash_fee");
		if (cash_fee != null)
			log.setCashFee(Long.parseLong(cash_fee));
		log.setTransactionId(responseParams.get("transaction_id"));
		log.setOutTradeNo(responseParams.get("out_trade_no"));
		log.setTimeEnd(responseParams.get("time_end"));
		log.setPrepayId(responseParams.get("prepay_id"));
		log.setMwebUrl(responseParams.get("mweb_url"));
		return log;
	}

	/**
	 * 插入支付宝回调记录,修改订单状态
	 *
	 * @param log
	 */
	public void insertAilCallBackLogAndOrderPayConfirm(PayAliLogDTO log, Long orderId, HttpServletRequest req) {
		payAliLogWriteService.insertPayWeixinLogWithTx(log);
		String orderCode = log.getOutTradeNo();
		SoDTO order = soClient.querySoById(orderId);
		SoDTO orderUpdateCondition = new SoDTO();
		orderUpdateCondition.setId(orderId);
		orderUpdateCondition.setOrderCode(order.getOrderCode());
		orderUpdateCondition.setOrderPaymentConfirmDate(new Date());
		orderUpdateCondition.setOrderStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		// 支付确认类型：0:用户支付确认 1:配送回款确认
		orderUpdateCondition.setOrderPaymentConfirmType(0);
		orderUpdateCondition.setCashPayType(Integer.valueOf(1));//现金支付类型为1.支付宝
		// 更改订单确认状态
		List<SoChildDTO> soChildDTOList = soChildClient.querySoChildListBySoId(orderId);
		Boolean flag = false;
		for(SoChildDTO childDTO:soChildDTOList){
			if(childDTO.getThirdpartyType()==3){
				flag = true;
			}
		}
		if(flag){
			orderUpdateCondition.setOrderConfirmStatus(0);
		}else{
			orderUpdateCondition.setOrderConfirmStatus(1);
		}

		//为用户分配会籍
		if(order.getSaleWay()==5){
			giveMembership(order);
		}
		// 更改订单支付状态
		orderUpdateCondition.setOrderPayStatus(1);
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiClient.findSoFreezeBalanceBySoId(orderId);
		// 订单积分冻结标志:订单冻结积分存在且大于0
		boolean soFreezeFubiFlag = soFreezeBalance != null && soFreezeBalance.compareTo(new BigDecimal("0.00")) > 0;

		// 计算订单现金支付金额
		BigDecimal soPaidByCash = log.getTotalAmount();
		orderUpdateCondition.setOrderPaidByCash(soPaidByCash);

		// 修改订单信息
		soWriteService.updateOrderPaymentInfo(orderUpdateCondition);

		// 根据订单id删除订单等待队列
		awaitQueueWriteService.delByOrderIdWithTx(orderId);

		InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
		vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
		vo1.setOrderCode(orderCode);
		vo1.setOrderPayStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		vo1.setUserId(order.getUserId());
		// 发送订单支付状态变更消息
		sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);

		// 用户积分余额扣除支付部分,积分冻结金额取消,订单冻结积分取消
		paymentFubi(orderId, orderCode, order.getUserId(), order.getPlatformId(), soFreezeFubiFlag, soFreezeBalance);
		// 发送积分变更消息
		if(soFreezeFubiFlag) {
			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
			Map<String,String> infoMap = new HashMap<String,String>();
			infoDto.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
			infoDto.setUserId(order.getUserId());
			infoMap.put("changeFuBi", soFreezeBalance==null?"0":soFreezeBalance.toPlainString());
			infoMap.put("changeCause", AccountConstant.FUBI_CHANGE_BUY_SU);
			infoDto.setParams(infoMap);
			sendInfoWriteService.insertUserFuBiInfoAndSend(infoDto);
		}

		// 现金资产流动
		paymentCash(orderId, orderCode, order.getUserId(), order.getPlatformId(), soPaidByCash);
		UserDTO userByID = new UserDTO();
		if(EmptyUtil.isNotEmpty(order.getUserId())){
			userByID = userClient.findUserByID(order.getUserId());
			if(EmptyUtil.isEmpty(userByID)){
				throw new BusinessException(order.getUserId() + "用户不存在");
			}
		}
		// 分配unit(支付回调公司类型肯定为0正式)
		cardWriteService.allocationCardAndTakeStock(orderId, order.getOrderCode(), order.getUserId(), userByID.getName(), null, null, 0);
		// 增加订单对应pu相应的销量
		merchantProdSalesRecordCoreWriteService.recordSalesVolume(orderId);

		//以旧换新操作
		exchangeAction(order,0,userByID.getName(),userByID.getId(),req);


		// 订单支付宝支付成功,判断订单的第三方订单的类型,并进行相应操作
		Integer companyType = companyCoreClient.findCompanyTypeByUserId(order.getUserId());
		boolean isSuccessDealThirdpartyOrder = true;
		if(companyType == 0)
			isSuccessDealThirdpartyOrder = soService.dealThirdpartyOrderWithTx(userByID.getName(),userByID.getId(),orderId, order.getOrderCode());

		if (isSuccessDealThirdpartyOrder) {
			// 支付成功,若使用了优惠卷,优惠卷变为已使用
			couponUnitWriteService.updateCouponByPaySuccessWithTx(orderId);
		} else {
			// 生成退款单编号,以防第三方话费充值失败需取消订单自动退款
			List<String> soRefundNOList = soRefundClient.genSoRefundNO();

			// 第三方订单处理失败,取消订单自动退款
			List<SoItemDTO> items = soItemClient.querySoItemListBySoId(order.getId());
			SoDTO soById = soClient.findSoById(order.getId());
			soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userByID.getName(),soById, items, userByID.getId(), soRefundNOList.get(0),
					soRefundNOList.get(1), new HttpServletRequestDTO(req)));
		}
	}

	/**
	 * 根据订单id查询订单冻结积分
	 *
	 * @param soId
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long soId) {
		return soFreezeFubiClient.findSoFreezeBalanceBySoId(soId);
	}

	/**
	 * 用户积分余额扣除支付部分,积分冻结金额取消,订单冻结积分取消
	 *
	 * @param orderId
	 * @param orderCode
	 * @param userId
	 * @param platformId
	 * @param soFreezeFubiFlag
	 *            订单冻结积分标志
	 */

	private void paymentFubi(Long orderId, String orderCode, Long userId, Long platformId, boolean soFreezeFubiFlag,
			BigDecimal soFreezeBalance) {
		logger.debug("com.egeo.back.pay.facade.PayCoreFacade.paymentFubi()" + "orderCode=" + orderId);
		if (soFreezeFubiFlag) {
			//fix-start 2024-11-05解决未减扣退账账户问题
			// 若不是德律风用户这个冻结金额中还有可能包含了退款账户可使用的金额
			UserDTO userDTO = userClient.findUserByID(userId);
			boolean isDlfUser = false;
			if(userDTO !=null && EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && Objects.equals(userDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
				isDlfUser = true;
			}

			// 流入账户列表
			CompanyAccountDTO caFubi = caClient.querySpecialCompanyAccountByType(platformId,1);
			SaltDTO caSalt = saltClient.querySaltByUUID(caFubi.getUuid());

			//若不是德律风用户，才会需要退款账户参与支付
			BigDecimal remaining = soFreezeBalance;
			UserAccountDTO uaFd = userAccountClient.queryUserAccountByUserIdAndType(userId, 4);
			BigDecimal fdBalance = uaFd !=null?uaFd.getBalance():BigDecimal.ZERO;
			// 根据uuid查询
			SaltDTO uaFdSalt = saltClient.querySaltByUUID(uaFd.getUuid());
			if(!isDlfUser && uaFd !=null && fdBalance.compareTo(BigDecimal.ZERO) >0){
				BigDecimal subtractAmt = BigDecimal.ZERO;
				if(fdBalance.compareTo(remaining) >=0){
					//可用余额大于冻结金额就全部减冻结金额
					subtractAmt = remaining;
				}else{
					//可用余额小于冻结金额就只减余额
					subtractAmt = fdBalance;
				}
				remaining = remaining.subtract(subtractAmt);
				// 流出账户列表
				List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
				CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
				outFlowAcc.setAccountId(uaFd.getId());
				outFlowAcc.setSalt(uaFdSalt.getSaltValue());
				outFlowAcc.setSum(subtractAmt);
				outFlowAccs.add(outFlowAcc);

				// 流入账户列表
				List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
				CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
				inFlowAcc.setAccountId(caFubi.getId());
				inFlowAcc.setSalt(caSalt.getSaltValue());
				inFlowAccs.add(inFlowAcc);
				// 用户积分账户支付(降低校验门槛,支付账户可以为负值)
				accountBatchWriteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 1, inFlowAccs, 0, false, platformId,
						FlowTypeConstant.OP_FUBI.getStatus(), orderId, orderCode, userId, FinBatchConstant.ORDER_PAY_FUBI,
						null, "订单付款", false, 0));
				logger.info("com.egeo.back.pay.facade.PayCoreFacade.paymentFubi()" + "资金流动，id=" + orderId+"操作冻结金额="+subtractAmt+",账户id="+uaFd.getId()+",账户类型为4");
			}
			//fix-start 2024-11-05解决未减扣退账账户问题


			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO uaFubi = userAccountClient.queryUserAccountByUserIdAndType(userId, 0);
			// 根据uuid查询
			SaltDTO usSalt = saltClient.querySaltByUUID(uaFubi.getUuid());
			if(remaining.compareTo(BigDecimal.ZERO) >0){
				// 流出账户列表
				List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
				CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
				outFlowAcc.setAccountId(uaFubi.getId());
				outFlowAcc.setSalt(usSalt.getSaltValue());
				outFlowAcc.setSum(remaining);
				outFlowAccs.add(outFlowAcc);

				// 流入账户列表
				List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
				CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
				inFlowAcc.setAccountId(caFubi.getId());
				inFlowAcc.setSalt(caSalt.getSaltValue());
				inFlowAccs.add(inFlowAcc);
				// 用户积分账户支付(降低校验门槛,支付账户可以为负值)
				accountBatchWriteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 1, inFlowAccs, 0, false, platformId,
						FlowTypeConstant.OP_FUBI.getStatus(), orderId, orderCode, userId, FinBatchConstant.ORDER_PAY_FUBI,
						null, "订单付款", false, 0));
				logger.info("com.egeo.back.pay.facade.PayCoreFacade.paymentFubi()" + "资金流动，id=" + orderId+"操作冻结金额="+remaining+",账户id="+uaFd.getId()+"账户类型为0");
			}

			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO uaFreese = userAccountClient.queryUserAccountByUserIdAndType(userId, 2);
			// 根据uuid查询
			SaltDTO uaFreeseSalt = saltClient.querySaltByUUID(uaFreese.getUuid());

			// 根据订单id删除订单冻结积分
			soFreezeFubiWriteService.delBySoId(orderId);
			//根据订单id修改订单的积分支付金额
			soWriteService.updateSoPayByFuBi(orderId,soFreezeBalance.toPlainString());
			// 更新积分账户余额
			BigDecimal balance = uaFreese.getBalance().subtract(soFreezeBalance);
			String cipher = MD5Util.MD5Salt(balance.toString(), uaFreeseSalt.getSaltValue());
			// 根据用户积分冻结id修改冻结积分余额
			UserAccountDTO userAccountDTO2 = new UserAccountDTO();
			userAccountDTO2.setId(uaFreese.getId());
			userAccountDTO2.setCiphertext(cipher);
			userAccountDTO2.setBalance(balance);
			userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
			logger.debug("com.egeo.back.pay.facade.PayCoreFacade.paymentFubi()" + "解冻积分，id=" + orderId);
		}
	}

	/**
	 * 订单支付现金资产流动
	 *
	 * @param orderId
	 * @param orderCode
	 * @param userId
	 * @param platformId
	 * @param soPaidByCash
	 */
	private void paymentCash(Long orderId, String orderCode, Long userId, Long platformId, BigDecimal soPaidByCash) {
		// 查询用户现金支付账户和盐
		UserAccountDTO uaCash = userAccountClient.queryUserAccountByUserIdAndType(userId, 3);
		SaltDTO uaSalt = saltClient.querySaltByUUID(uaCash.getUuid());
		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
		CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
		outFlowAcc.setAccountId(uaCash.getId());
		outFlowAcc.setSalt(uaSalt.getSaltValue());
		outFlowAcc.setSum(soPaidByCash);
		outFlowAccs.add(outFlowAcc);
		// 查询迩格现金收入账户和盐
		CompanyAccountDTO caCash = caClient.querySpecialCompanyAccountByType(platformId,2);
		SaltDTO caSalt = saltClient.querySaltByUUID(caCash.getUuid());
		List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
		CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
		inFlowAcc.setAccountId(caCash.getId());
		inFlowAcc.setSalt(caSalt.getSaltValue());
		inFlowAccs.add(inFlowAcc);
		accountBatchWriteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 1, inFlowAccs, 0, false, platformId,
				FlowTypeConstant.OP_CASH.getStatus(), orderId, orderCode, userId, FinBatchConstant.ORDER_PAY_CASH, null,
				"订单付款", false, 0));
	}

	/**
	 * 添加支付等待队列
	 *
	 * @param dto
	 * @return
	 */
	public Long insertAwaitQueueWithTx(AwaitQueueDTO dto,Integer orderPayStatus) {
		return awaitQueueWriteService.insertAwaitQueueWithTx(dto,orderPayStatus);
	}

	/**
	 * 根据puid集合查询pu信息
	 *
	 * @param puIds
	 * @return
	 */
	public List<CommodityProductUnitDTO> findByPUIds(List<Long> puIds) {
		return commodityProductUnitClient.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(puIds));
	}

	/**
	 * 插入支付宝原生支付签名日志
	 *
	 * @param signLog
	 */
	public Long insertAlipayNativeSignLog(AlipayNativeSignLogDTO signLog) {
		return alipayNativeSignLogWriteService.insertAlipayNativeSignLogWithTx(signLog);
	}

	/**
	 * 根据订单id查询等待队列信息
	 *
	 * @param id
	 * @return
	 */
	public AwaitQueueDTO queryQueueBySoId(Long id) {
		return awaitQueueClient.queryAwaitQueueByOrderId(id);
	}

	/**
	 * 根据订单id删除队列
	 *
	 * @param id
	 * @return
	 */
	public int deleteQueueBySoId(Long id) {
		return awaitQueueWriteService.delByOrderIdWithTx(id);
	}

	/**
	 * 订单支付签名事务,更改订单支付方式与签名平台,加入等待队列
	 *
	 * @param cashPayType
	 *            订单现金支付方式 1 支付宝 2 微信 3 银联 4 建行
	 * @param randomNumber
	 *            微信支付使用的4为随机位数
	 * @param order
	 */
	public void orderPaySigned(StandardOrderQueryResultVO order, Integer cashPayType, String randomNumber,Integer signPlatform) {
		// 订单支付方式更改
		Long orderId = order.getSoId();
		SoDTO so = new SoDTO();
		so.setId(orderId);
		//so.setCashPayType(cashPayType);
		so.setSignPlatform(signPlatform);
		soWriteService.updateOrderByOrderId(so);
		// 订单加入等待队列(已在队列中则不添加)
		AwaitQueueDTO queue = awaitQueueClient.queryAwaitQueueByOrderId(orderId);
		if (queue == null) {
			queue = new AwaitQueueDTO();
			queue.setCashPayType(cashPayType);
			queue.setOrderCode(order.getOrderCode());
			queue.setSoId(orderId);
			queue.setRandomNumber(randomNumber);
			awaitQueueWriteService.insertAwaitQueueWithTx(queue,order.getOrderPayStatus());

		}
	}

}
