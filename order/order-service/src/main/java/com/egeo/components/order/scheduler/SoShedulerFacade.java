package com.egeo.components.order.scheduler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.utils.JdUtils2;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.client.CardClient;
import com.egeo.components.order.client.MerchantProdSalesRecordCoreClient;
import com.egeo.exception.BusinessException;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.service.SoService;
import com.egeo.components.order.service.read.ReceiverAddressReadService;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.ReceiverAddressWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoPackageWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.pay.client.AwaitQueueClient;
import com.egeo.components.pay.client.JdOrderAwaitQueueClient;
import com.egeo.components.pay.client.ThirdpartyAwaitQueueClient;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.JdProductInnerIdClient;
import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.client.MerchantProdSalesRecordClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.client.ECardTempClient;
import com.egeo.components.promotion.client.ExchangeOrderRecordClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.fo.FindECardOfPageFO;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;
import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.user.client.PlatformClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.qc.QCInfos2;
import com.egeo.components.utils.qc.QCResult;
import com.egeo.components.utils.qc.QCUtil;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SendMail;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.encrypt.QEncodeUtil;
import com.egeo.utils.excel.ExcelExportSXXSSF;
import com.egeo.utils.log.LogUtil;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhone;
import com.egeo.utils.thirdparty.RechargePhoneResult;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Component
public class SoShedulerFacade {
	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SoReadService soReadService;

	@Autowired
	private SoWriteService soWriteService;

	@Autowired
	private SoItemReadService soItemReadService;

	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockWriteService;

	@Autowired
	private SoFreezeFubiClient soFreezeFubiReadService;

	@Autowired
	private SoFreezeFubiClient soFreezeFubiWriteService;

	@Autowired
	private UserAccountClient userAccountReadService;
	@Autowired
	private SkuClient skuReadService;

	@Autowired
	private UserAccountClient userAccountWriteService;

	@Autowired
	private SaltClient saltReadService;

	@Autowired
	private AwaitQueueClient awaitQueueReadService;

	@Autowired
	private AccountBatchClient accountBatchWriteService;

	@Autowired
	private CompanyAccountClient companyAccountReadService;

	@Autowired
	private SoChildReadService soChildReadService;

	@Autowired
	private SoChildWriteService soChildWriteService;

	@Autowired
	private AwaitQueueClient awaitQueueWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordReadService;

	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordWriteService;

	@Autowired
	private ECardClient eCardWriteService;

	@Autowired
	private ECardClient eCardReadService;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	@Autowired
	private SoThirdpartyReadService soThirdpartyReadService;

	@Autowired
	private SoThirdpartyWriteService soThirdpartyWriteService;

	@Autowired
	private CouponUnitClient couponUnitWriteService;

	@Autowired
	private SoRefundReadService soRefundReadService;

	@Autowired
	private SoService soService;

	@Autowired
	private CardClient cardWriteService;

	@Autowired
	private SendInfoClient sendInfoWriteService;
	@Autowired
	private ECardTempClient eCardTempWriteService;

	@Autowired
	private MerchantProdSalesRecordCoreClient merchantProdSalesRecordCoreWriteService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockService;
	@Autowired
	private ThirdpartyAwaitQueueClient thirdpartyAwaitQueueReadService;
	@Autowired
	private CardSaltClient cardSaltWriteService;
	@Autowired
	private ThirdpartyAwaitQueueClient thirdpartyAwaitQueueWriteService;
	@Autowired
	private MerchantClient merchantReadService;
	@Autowired
	private PlatformClient platformReadService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordWriteService;
	@Autowired
	private UserClient userReadService;

	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordReadService;
	@Autowired
	private SoPackageReadService soPackageReadService;
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
	@Autowired
	private JdOrderAwaitQueueClient jdOrderAwaitQueueReadService;
	@Autowired
	private ReceiverAddressReadService receiverAddressReadService;
	@Autowired
	private ReceiverAddressWriteService receiverAddressWriteService;
	@Autowired
	private SoPackageWriteService soPackageWriteService;
	@Autowired
	private JdProductInnerIdClient jdProductInnerIdReadService;

	@Autowired
	private PayUtil payUtil;
	@Autowired
	private RechargePhoneUtil rechargePhoneUtil;
	@Autowired
	private JdUtils jdUtils;
	@Autowired
	private QCUtil qCUtil;
	@Resource
	private PushOrderManage pushOrderManage;

	@Value("${jd.account.limit:10000}")
	private Integer jdAccountLimit;
	@Autowired
	private JdUtils2 jdUtils2;

	Logger log = Logger.getLogger(SoShedulerFacade.class);

	private MerchantProductWarehouseStockDTO converSoItemToMps(SoItemDTO soItemDTO) {

		MerchantProductWarehouseStockDTO mpsDto = new MerchantProductWarehouseStockDTO();
		// 商家
		// mpsDto.setMerchantId(soItemDTO.getMerchantId());
		// 商品
		// mpsDto.setMerchantProductId(soItemDTO.getMpId());

		// mpsDto.setSkuId(soItemDTO.getSkuId());
		// 商品购买的数量
		// mpsDto.setRealFrozenStockNum(soItemDTO.getProductItemNum().longValue());

		return mpsDto;
	}

	public Boolean checkPayCashValidation(SoDTO sodto) {
		return null;
	}

	public boolean changeOrderStatus(String orderCode, Long soId, Integer status, Long userId) {
		boolean b = false;
		// 根据订单编号查询是否处于支付等待列表
		AwaitQueueDTO awaitQueue = awaitQueueReadService.queryAwaitQueueByOrderId(soId);

		if (EmptyUtil.isNotEmpty(awaitQueue)) {
			// 查询订单支付状态根据状态完成相应操作
			cancelSo(awaitQueue);
			awaitQueueWriteService.delByOrderIdWithTx(awaitQueue.getSoId());
		} else {
			// 这里处理尚未调起现金支付签名的订单
			b = updateOrderStatus(orderCode,soId, userId, status);

		}

		return b;

	}

	private boolean updateOrderStatus(String orderCode, Long soId, Long userId, Integer status) {
		boolean b = false;
		b = soWriteService.changeOrderStatusByOrderId(orderCode, soId, status, 2, null, null);
		// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分冻结账户
		cancelSoFreezeFubi(soId, userId);
		return b;
	}

	public boolean unlockItemsStock(List<SoItemDTO> soItemDTOList) {
		// 改变所有购买项的库存，解锁锁定的库存
		List<MerchantProductWarehouseStockDTO> mpsDtoList = new ArrayList<MerchantProductWarehouseStockDTO>();
		for (SoItemDTO soItemDto : soItemDTOList) {
			mpsDtoList.add(converSoItemToMps(soItemDto));
		}
		return merchantProductWarehouseStockWriteService.batchUnlockItemsStockWithTx(mpsDtoList);

	}

	/**
	 * 把锁定的库存解锁
	 *
	 * @param soItemDTOList
	 * @return
	 */
	public boolean unlockItemsProductUnitStock(List<SoItemDTO> soItemDTOList) {
		boolean result = false;
		// 改变所有购买项的库存，解锁锁定的库存
		for (SoItemDTO soItemDto : soItemDTOList) {
			SoDTO soDTO = soReadService.querySoById(soItemDto.getId());
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(soItemDto.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
					.findCommodityProductUnitById(commodityProductUnitDTO);
			//根据订单ID查询 puIds
			//根据puIds
			List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
			commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(Arrays.asList(soDTO.getOrderCode()));
			List<Long> puIdList = new ArrayList<>();
			List<String> puIdStrList = new ArrayList<>();

			Set<Long> puIdSet = new HashSet<>();
			Set<String> puIdStrSet = new HashSet<>();
			for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
				puIdSet.add(waterDTO.getCommodityProductUnitId());
				puIdStrSet.add(waterDTO.getCommodityProductUnitId().longValue()+"");

			}
			puIdList.addAll(puIdSet);
			puIdStrList.addAll(puIdStrSet);

			List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(puIdStrList,commodityProductUnitDTO2.getSkuId());

			if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
				puIdList.add(soItemDto.getPuId());
				commodityProductUnitDTOs.add(commodityProductUnitDTO2);
				commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(soItemDto.getPuId(), soItemDto.getPuCount(),
						StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), soDTO.getOrderCode(), null, null,
						null, null, puIdList, commodityProductUnitDTOs));
			}else {
				// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
				commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(soItemDto.getPuId(),
						soItemDto.getPuCount(), StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),
						commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),
						soDTO.getOrderCode(), null, null, null, null);
			}

		}
		result = true;
		return result;

	}

	public List<SoDTO> findAllunpayOrders() {
		return soReadService.findAllunpayOrders();
	}

	public List<SoItemDTO> findSoItemListByOrderCode(Long soId) {
		return soReadService.findSoItemListByOrderCode(soId);
	}

	/**
	 * 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
	 */
	private void cancelSoFreezeFubi(Long orderId, Long userId) {
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(orderId);
		if (EmptyUtil.isNotEmpty(soFreezeBalance)) {
			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户

			// 根据订单id删除订单冻结积分
			soFreezeFubiWriteService.delBySoId(orderId);

			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO userAccount = userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
			// 根据uuid查询
			SaltDTO salt = saltReadService.querySaltByUUID(userAccount.getUuid());
			String ciphertextSalt = MD5Util.MD5Salt(userAccount.getBalance().toString(), salt.getSaltValue());
			if (!ciphertextSalt.equals(userAccount.getCiphertext())) {
				log.debug("积分冻结账户异常,id:" + userAccount.getId());
			}
			BigDecimal balance = userAccount.getBalance().subtract(soFreezeBalance);
			String ciphert = MD5Util.MD5Salt(balance.toString(), salt.getSaltValue());
			// 根据用户积分冻结id修改冻结积分余额
			UserAccountDTO userAccountDTO2 = new UserAccountDTO();
			userAccountDTO2.setId(userAccount.getId());
			userAccountDTO2.setCiphertext(ciphert);
			userAccountDTO2.setBalance(balance);
			userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
		}
	}

	/**
	 * 刷新支付订单状态
	 */
	public void changeSoPayStatusJob() {
		// 获取所有支付等待订单
		List<AwaitQueueDTO> awaitQueueList = awaitQueueReadService.findAwaitQueueAll(new AwaitQueueDTO());
		Long currentTime = System.currentTimeMillis();
		for (AwaitQueueDTO queue : awaitQueueList) {
			// 根据订单
			Long createTime = queue.getCreateTime().getTime();
			Long residueTime = currentTime - createTime;
			// 判断是否大于15分钟 15*60*1000=900000
			if (residueTime.longValue() > PayUtil.QUEUE_EXIST_TIME_MS) {
				// 查询订单支付状态根据状态完成相应操作
				cancelSo(queue);
			} else {
				// 根据订单编号查询订单支付完成
				Long soId = queue.getSoId();
				SoDTO so = soReadService.querySoById(soId);
				if (so == null) {
					continue;
				}
				Integer cashPayType = so.getCashPayType();
				if (cashPayType == null) {
					continue;
				}
				Integer payStatus = so.getOrderPayStatus();
				if (payStatus != 0) {
					// 不是待支付状态,删除队列，继续下一循环
					awaitQueueWriteService.delByOrderIdWithTx(soId);
					continue;
				}
				String queryRes = null;
				switch (cashPayType) {
				case 1:
					queryRes = payUtil.alipayQuerySub(queue.getOrderCode());
					break;
				case 2:
					queryRes = payUtil.wxQuerySub(queue.getOrderCode() + queue.getRandomNumber(),null);
					break;
				default:
					// 支付方式未定义,不进行接下来的操作
					continue;
				}

				if (queryRes == null) {
					// 订单处于支付中状态
					// 继续等待
					continue;
				} else if ("ORDER_PAYED".equals(queryRes)) {
					// 走一下订单成功支付的逻辑
					// 这段逻辑从payCoreFacade模块拷过来的
					orderPaySuccess(so);

					// // 订单已支付
					// // 删除队列
					// // 到这一步说明回调未收到,但查询表明订单已经完成支付
					// // 需要删除队列,解冻积分,完成资金流动等逻辑
					// // 修改订单状态
					// soWriteService.changeOrderStatusByOrderId(soId,
					// OrderConstant.ORDER_STATUS_PAYED.getStatus(), 1, 1,
					// null);
					// //修改订单实付金额
					//
					// // 前面已经查询过,订单是未支付状态
					// // 如果可以走到这里,需要完成资金流动,解冻积分逻辑
					// boolean cashFlow = cashFlow(so);
					// if (cashFlow) {
					// // 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
					// cancelSoFreezeFubi(soId, so.getUserId());
					// }
					// // 根据订单等待id删除订单等待
					// awaitQueueWriteService.delByOrderIdWithTx(soId);
				}
			}
		}
	}

	/**
	 * 订单支付成功子逻辑 负责的任务: 更改订单状态 更改订单付款信息 计算支付金额 积分流动 现金流动 分配unit 增加订单对应pu相应的销量
	 * 删除订单等待队列
	 */
	private void orderPaySuccess(SoDTO order) {
		Long orderId = order.getId();
		String orderCode = order.getOrderCode();
		SoDTO orderUpdateCondition = new SoDTO();
		orderUpdateCondition.setId(orderId);
		orderUpdateCondition.setOrderCode(orderCode);
		orderUpdateCondition.setOrderPaymentConfirmDate(new Date());
		orderUpdateCondition.setOrderStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		// 支付确认类型：0:用户支付确认 1:配送回款确认
		orderUpdateCondition.setOrderPaymentConfirmType(0);
		orderUpdateCondition.setCashPayType(order.getCashPayType());
		// 更改订单确认状态
		orderUpdateCondition.setOrderConfirmStatus(1);
		// 更改订单支付状态
		orderUpdateCondition.setOrderPayStatus(1);
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(orderId);
		// 订单积分冻结标志:订单冻结积分存在且大于0
		boolean soFreezeFubiFlag = soFreezeBalance != null && soFreezeBalance.compareTo(new BigDecimal("0.00")) > 0;

		// 计算订单现金支付金额
		/*
		 * 这里隐含一个问题: 到底支付了多少现金, 应该从支付宝的回调参数中获取, 而不是用订单总金额-积分支付金额计算得出
		 */
		BigDecimal soPaidByCash = null;
		if (soFreezeFubiFlag) {
			orderUpdateCondition.setOrderPaidByFubi(soFreezeBalance);
			soPaidByCash = order.getOrderAmount().subtract(soFreezeBalance);
			orderUpdateCondition.setOrderPaidByCash(soPaidByCash);
		} else {
			orderUpdateCondition.setOrderPaidByFubi(new BigDecimal(0.00));
			soPaidByCash = order.getOrderAmount();
			orderUpdateCondition.setOrderPaidByCash(soPaidByCash);
		}
		soWriteService.updateOrderPaymentInfo(orderUpdateCondition);
		// 发送订单支付状态变更消息

		InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
		vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
		vo1.setOrderCode(orderCode);
		vo1.setOrderPayStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
		vo1.setUserId(order.getUserId());
		sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);

		// 用户积分余额扣除支付部分,积分冻结金额取消,订单冻结积分取消
		paymentFubi(orderId, orderCode, order.getUserId(), order.getPlatformId(), soFreezeFubiFlag, soFreezeBalance);
		// 发送积分变更消息
		InsertAndSendMessageDTO vo2 = new InsertAndSendMessageDTO();
		vo2.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
		Map<String,String> infoMap = new HashMap<String,String>();
		infoMap.put("changeFuBi", soFreezeBalance.toPlainString());
		infoMap.put("changeCause", AccountConstant.FUBI_CHANGE_BUY_SU);
		vo2.setParams(infoMap);
		vo2.setUserId(order.getUserId());
		sendInfoWriteService.insertUserFuBiInfoAndSend(vo2);

		// 现金资产流动
		paymentCash(orderId, orderCode, order.getUserId(), order.getPlatformId(), soPaidByCash);
		UserDTO userDTO = userReadService.findUserByID(order.getUserId());
		String userName="";
		if(EmptyUtil.isNotEmpty(userDTO)){
			userName = userDTO.getName();
		}
		// 分配unit(支付回调公司类型肯定为0正式)
		cardWriteService.allocationCardAndTakeStock(orderId, orderCode, order.getUserId(), userName, null, null, 0);

		// 增加订单对应pu相应的销量
		merchantProdSalesRecordCoreWriteService.recordSalesVolume(order.getId());
		//进行以旧换新操作
		if(order.getSaleWay().equals(8)){
			//exchangeAction(order,0,userName,order.getId());
		}
		// 根据订单id删除订单等待队列
		awaitQueueWriteService.delByOrderIdWithTx(orderId);

	}


	//进行以旧换新


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
		if (soFreezeFubiFlag) {
			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO uaFubi = userAccountReadService.queryUserAccountByUserIdAndType(userId, 0);
			// 根据uuid查询
			SaltDTO usSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
			// 流出账户列表
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
			outFlowAcc.setAccountId(uaFubi.getId());
			outFlowAcc.setSalt(usSalt.getSaltValue());
			outFlowAcc.setSum(soFreezeBalance);
			outFlowAccs.add(outFlowAcc);
			// 流入账户列表
			CompanyAccountDTO caFubi = companyAccountReadService.querySpecialCompanyAccountByType(platformId,1);
			SaltDTO caSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
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
			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO uaFreese = userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
			// 根据uuid查询
			SaltDTO uaFreeseSalt = saltReadService.querySaltByUUID(uaFreese.getUuid());

			// 根据订单id删除订单冻结积分
			soFreezeFubiWriteService.delBySoId(orderId);
			// 更新积分账户余额
			BigDecimal balance = uaFreese.getBalance().subtract(soFreezeBalance);
			String cipher = MD5Util.MD5Salt(balance.toString(), uaFreeseSalt.getSaltValue());
			// 根据用户积分冻结id修改冻结积分余额
			UserAccountDTO userAccountDTO2 = new UserAccountDTO();
			userAccountDTO2.setId(uaFreese.getId());
			userAccountDTO2.setCiphertext(cipher);
			userAccountDTO2.setBalance(balance);
			userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
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
		UserAccountDTO uaCash = userAccountReadService.queryUserAccountByUserIdAndType(userId, 3);
		SaltDTO uaSalt = saltReadService.querySaltByUUID(uaCash.getUuid());
		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
		CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
		outFlowAcc.setAccountId(uaCash.getId());
		outFlowAcc.setSalt(uaSalt.getSaltValue());
		outFlowAcc.setSum(soPaidByCash);
		outFlowAccs.add(outFlowAcc);
		// 查询迩格现金收入账户和盐
		CompanyAccountDTO caCash = companyAccountReadService.querySpecialCompanyAccountByType(platformId,2);
		SaltDTO caSalt = saltReadService.querySaltByUUID(caCash.getUuid());
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
	 * 查询订单支付状态根据状态完成相应操作<br>
	 * 分情况调用PayUtil中的查询子方法和取消子方法 <br>
	 * 查询子方法返回null,表示可以取消<br>
	 * 查询子方法返回ORDER_PAYED,表示订单已经支付,不可取消,但要删除订单队列和解冻积分,完成资金流动等逻辑,状态码1<br>
	 * 查询子方法返回其他字符串,表示出现异常,直接return<br>
	 * <br>
	 * 删除子方法返回null,表示取消成功,删除队列,解冻积分 状态码2 <br>
	 * 删除子方法返回其他字符串,表示出现异常,直接return<br>
	 * <br>
	 *
	 * @param queue
	 */
	private void cancelSo(AwaitQueueDTO queue) {
		// boolean revocationOrderPay = false;
		// 根据订单编号查询订单支付完成
		String orderCode = queue.getOrderCode();
		String codeWithRandom = orderCode + queue.getRandomNumber();
		Integer cashPayType = queue.getCashPayType();
		Long soId = queue.getSoId();
		SoDTO order = soReadService.querySoById(soId);
		if (order == null) {
			return;
		}
		Integer payStatus = order.getOrderPayStatus();
		if (payStatus != 0) {
			// 订单不是待支付状态,表明已经接受了回调,直接删除队列
			awaitQueueWriteService.delByOrderIdWithTx(soId);
			// 不继续执行下面的操作
			return;
		}
		if (cashPayType == null) {
			log.error("订单标号为" + orderCode + "的订单现金支付方式未定义,无法调用定时任务取消队列");
			return;
		}
		int status = 0;
		switch (cashPayType) {
		case 1:
			// 支付宝
			// 查询交易
			String errAli = payUtil.alipayQuerySub(orderCode);
			if (errAli != null) {
				if ("ORDER_PAYED".equals(errAli)) {
					status = 1;
					break;
				} else {
					log.error("订单标号为" + orderCode + "的订单调用阿里交易查询接口出现异常:" + errAli);
					return;
				}
			}
			// 取消交易
			errAli = payUtil.alipayCloseSub(orderCode);
			if (errAli != null) {
				log.error("订单标号为" + orderCode + "的订单调用阿里交易查询接口出现异常:" + errAli);
				return;
			}
			status = 2;
			break;
		case 2:
			// 微信
			// 查询交易
			String errWx = payUtil.wxQuerySub(codeWithRandom,order.getPlatformId());
			if (errWx != null) {
				if ("ORDER_PAYED".equals(errWx)) {
					status = 1;
					break;
				} else {
					log.error("订单标号为" + orderCode + "的订单调用阿里交易查询接口出现异常:" + errWx);
					return;
				}
			}
			// 取消交易
			errWx = payUtil.wxCancelSub(codeWithRandom,order.getPlatformId());
			if (errWx != null) {
				log.error("订单标号为" + orderCode + "的订单调用阿里交易查询接口出现异常:" + errWx);
				return;
			}
			status = 2;
			break;
		default:
			log.error("订单标号为" + queue.getOrderCode() + "的订单现金支付方式定义无法识别,无法调用定时任务取消队列");
			return;
		}
		// 状态码 1:订单成功支付,2交易成功取消
		switch (status) {
		case 1:
			// 订单支付成功子逻辑 负责的任务: 更改订单状态 更改订单付款信息 计算支付金额 积分流动 现金流动 分配unit 增加订单对应pu相应的销量
			orderPaySuccess(order);
			/*SoDTO orderUpdateCondition = new SoDTO();
			orderUpdateCondition.setId(soId);
			orderUpdateCondition.setOrderPaymentConfirmDate(new Date());
			orderUpdateCondition.setOrderStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
			// 支付确认类型：0:用户支付确认 1:配送回款确认
			orderUpdateCondition.setOrderPaymentConfirmType(0);
			orderUpdateCondition.setCashPayType(2);
			// 更改确认状态
			orderUpdateCondition.setOrderConfirmStatus(1);
			// 更改订单支付状态
			orderUpdateCondition.setOrderPayStatus(1);
			// 根据订单id查询订单冻结积分
			BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(soId);
			// 订单积分冻结标志:订单冻结积分存在且大于0
			boolean soFreezeFubiFlag = soFreezeBalance != null && soFreezeBalance.compareTo(new BigDecimal("0.00")) > 0;

			BigDecimal soPaidByCash = null;
			if (soFreezeFubiFlag) {
				orderUpdateCondition.setOrderPaidByFubi(soFreezeBalance);
				soPaidByCash = order.getOrderAmount().subtract(soFreezeBalance);
				orderUpdateCondition.setOrderPaidByCash(soPaidByCash);
			} else {
				orderUpdateCondition.setOrderPaidByFubi(new BigDecimal(0.00));
				soPaidByCash = order.getOrderAmount();
				orderUpdateCondition.setOrderPaidByCash(soPaidByCash);
			}
			soWriteService.updateOrderPaymentInfo(orderUpdateCondition);

			// 修改订单状态
			soWriteService.changeOrderStatusByOrderId(soId, OrderConstant.ORDER_STATUS_PAYED.getStatus(), null, null,
					null);
			// 前面已经查询过,订单是未支付状态
			// 如果可以走到这里,需要完成资金流动,解冻积分逻辑
			boolean cashFlow = cashFlow(order);
			if (cashFlow) {
				// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
				cancelSoFreezeFubi(soId, order.getUserId());
			}
			// 根据订单等待id删除订单等待
			awaitQueueWriteService.delByOrderIdWithTx(soId);*/
			break;
		case 2:
			// 解冻积分
			cancelSoFreezeFubi(soId, order.getUserId());
			// 根据订单等待id删除订单等待、可以继续去继续现金支付
			awaitQueueWriteService.delByOrderIdWithTx(soId);
			break;
		// case 3:
		// // 根据订单编号撤销订单支付
		// revocationOrderPay = awaitQueueReadService.revocationOrderPay(queue);
		// if (revocationOrderPay) {
		// // 取消订单
		// updateOrderStatus(queue.getOrderCode(),
		// OrderConstant.ORDER_STATUS_CANCELED.getStatus());
		// // 根据订单等待id删除订单等待
		// awaitQueueWriteService.deleteAwaitQueueWithTx(queue);
		// }
		// break;
		}

	}

	private boolean cashFlow(SoDTO soDTO) {
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(soDTO.getId());
		// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
		UserAccountDTO userAccountDTO = userAccountReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 0);
		// 根据uuid查询
		SaltDTO saltDTO = saltReadService.querySaltByUUID(userAccountDTO.getUuid());
		// 流出账户列表
		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
		CashFlowAccountDTO cashFlowAccountDTO = new CashFlowAccountDTO();
		cashFlowAccountDTO.setAccountId(userAccountDTO.getId());
		cashFlowAccountDTO.setSalt(saltDTO.getSaltValue());
		cashFlowAccountDTO.setSum(soFreezeBalance);
		outFlowAccs.add(cashFlowAccountDTO);

		// 流入账户列表
		CompanyAccountDTO fubiShouru = companyAccountReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
		String caUUID = fubiShouru.getUuid();
		SaltDTO comSalt = saltReadService.querySaltByUUID(caUUID);
		List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
		CashFlowAccountDTO cashFlowAccount = new CashFlowAccountDTO();
		cashFlowAccount.setAccountId(fubiShouru.getId());
		cashFlowAccount.setSalt(comSalt.getSaltValue());
		inFlowAccs.add(cashFlowAccount);

		CashFlowResultDTO cashFlowResultDTO = accountBatchWriteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 1, inFlowAccs, 0,
				false, soDTO.getPlatformId(), FlowTypeConstant.OP_FUBI.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
				soDTO.getUserId(), FinBatchConstant.ORDER_PAY_FUBI, null, null, false, 0));
		return cashFlowResultDTO.getResult();
	}

	/**
	 * 订单超过14天定时确认收货
	 */
	public void confirmReceiptSoJob() {
		// 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
		long autoCompleteTimeInterval = 1000 * 60 * 60 * 24 * 14;// 14天
		//long autoCompleteTimeInterval=1000 * 60*2;//2分钟
		List<SoDTO> soList = soReadService.findByOrderStatusNoOrderAutoCompleteDate();
		for (SoDTO soDTO : soList) {
			boolean isNotSaveOrderAutoCompleteDate = true;
			// 根据母订单id查询子订单信息
			SoChildDTO soChildDTO = new SoChildDTO();
			soChildDTO.setSoId(soDTO.getId());
			List<SoChildDTO> soChildList = soChildReadService.findSoChildAll(soChildDTO);
			for (SoChildDTO soChildDTO2 : soChildList) {
				if (soChildDTO2.getDeliveryStatus() != 2 && soChildDTO2.getDeliveryStatus() != 3) {
					isNotSaveOrderAutoCompleteDate = false;
				}
			}

			// 保存订单自动完成时间
			if (isNotSaveOrderAutoCompleteDate) {
				SoDTO oldSoDTO = findSoById(soDTO.getId());

				Date date = new Date();
				date.setTime(System.currentTimeMillis() + autoCompleteTimeInterval);
				// 根据订单id保存订单自动完成时间
				SoDTO soDTO2 = new SoDTO();
				soDTO2.setId(soDTO.getId());
				soDTO2.setOrderAutoCompleteDate(date);
				soWriteService.saveOrderAutoCompleteDate(soDTO2);

				// 记录订单完成日志
				recordConfirmOrderLog(oldSoDTO, "SoFacade_confirmReceiptSoJob");
			}
		}

		// 查询订单自动完成时间不为空并且订单不为已完成的订单
		List<SoDTO> soDTOList = soReadService.findByOrderAutoCompleteDateNoNull();
		for (SoDTO soDTO : soDTOList) {
			Date date = new Date();
			// 订单自动完成时间小于当前时间修改订单状态为以完成
			if (soDTO.getOrderAutoCompleteDate().before(date)) {
				SoDTO oldSoDTO = findSoById(soDTO.getId());

				// 前面小于后面
				SoDTO so = new SoDTO();
				so.setId(soDTO.getId());
				so.setOrderStatus(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus());
				so.setOrderConfirmStatus(3);
				soWriteService.updateOrderPaymentInfo(so);

				// 记录订单完成日志
				recordConfirmOrderLog(oldSoDTO, "SoFacade_confirmReceiptSoJob");
				pushOrderManage.pushOrderInfo(soDTO.getId(),null,null);
			}

		}

	}

	/**
	 * 记录订单完成日志
	 * @param oldSoDTO
	 */
	private void recordConfirmOrderLog(SoDTO oldSoDTO, String operObject) {
		SoDTO newSoDTO = findSoById(oldSoDTO.getId());
		EgeoLog log = new EgeoLog();
		log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
		log.setOperObject(operObject);
		log.setMsgId(LogConstant.ORDER_CONFIRM.getStatus());
		log.setType(LogTypeConstant.SO.getStatus());
		log.setOperatorObjId(newSoDTO.getId());
		log.setOperatorObjCode(newSoDTO.getOrderCode());
		log.setOldObj(oldSoDTO);
		log.setNewObj(newSoDTO);
		log.setMsgContent(JSON.toJSONString(LogUtil.getObjDifference(log.getOldObj(), log.getNewObj())));
		log.setTime(new Date());
		log.setPlatformId(oldSoDTO.getPlatformId());
		try {
			ActiveMQUtils.recordBusinessLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解冻订单库存
	 *
	 * @param list
	 */
	public void unFreeseOrderStock(List<SoItemDTO> list, String orderCode) {
		SoDTO soDTO = soReadService.querySoByOrderCode(orderCode);

		for (SoItemDTO it : list) {
			Long puId = it.getPuId();
			Integer puCount = it.getPuCount();
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(it.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
					.findCommodityProductUnitById(commodityProductUnitDTO);

			//根据订单ID查询 puIds
			//根据puIds
			List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
			//commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(Arrays.asList(soDTO.getOrderCode()),StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
					commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(Arrays.asList(soDTO.getOrderCode()));
			List<Long> puIdList = new ArrayList<>();
			List<String> puIdStrList = new ArrayList<>();

			Set<Long> puIdSet = new HashSet<>();
			Set<String> puIdStrSet = new HashSet<>();

			for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
				puIdSet.add(waterDTO.getCommodityProductUnitId());
				puIdStrSet.add(waterDTO.getCommodityProductUnitId().longValue()+"");

			}
			puIdList.addAll(puIdSet);
			puIdStrList.addAll(puIdStrSet);

			List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(puIdStrList,commodityProductUnitDTO2.getSkuId());

			if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
				puIdList.add(it.getPuId());
				commodityProductUnitDTOs.add(commodityProductUnitDTO2);
				commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(it.getPuId(), it.getPuCount(),
						StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), soDTO.getOrderCode(), null, null,
						null, null, puIdList, commodityProductUnitDTOs));
			}else {
				commodityProductUnitWarehouseStockWriteService.unFreeseOrderStock(puId, puCount, orderCode,
						StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),
						commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), null,
						null, null, null);
			}


			// 判断pu是否是unit商品
			boolean isUnit = commodityProductUnitReadService.queryIsUnit(puId);
			if (isUnit) {
				// 查询sku信息
				CommodityProductUnitDTO condition = new CommodityProductUnitDTO();
				condition.setId(puId);
				CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(condition);
				// 回滚unit冻结库存
				merchantProductVirtualStockWriteService.unfreezeStockWithTx(pu.getSkuId(), puCount);
			}
		}
		storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),soDTO,list));
	}

	public SoDTO findSoById(Long id) {
		return soReadService.querySoById(id);
	}

	/**
	 * 请求话费充值结果
	 */
	public void confirmRechargeResult() {
		// 1.查出处于等待充值结果的第三方话费充值订单,查询处理中的第三方订单
		SoThirdpartyDTO dto = new SoThirdpartyDTO();
		dto.setThirdpartyStatus(Integer.valueOf(0));
		List<SoThirdpartyDTO> soThirdpartyList = soThirdpartyReadService.findSoThirdpartyAll(dto);
		for (SoThirdpartyDTO soThirdparty : soThirdpartyList) {
			SoChildDTO soChild = soChildReadService.findSoChildById(soThirdparty.getSoChildId());
			RechargePhone rechargePhone = null;
			try {
				rechargePhone = rechargePhoneUtil.orderSta(soChild.getChildCode());
			} catch (Exception e) {
				log.error("请求话费充值结果异常, 子订单编号: " + soChild.getChildCode());
				e.printStackTrace();
			}

			if (rechargePhone != null && RechargePhoneErrorCode.REQUEST_SUCCESS.getCode().equals(rechargePhone.getErrorCode())) {
				// 查询订单充值状态成功
				RechargePhoneResult rechargePhoneResult = rechargePhone.getResult();
				SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
				soThirdpartyDTO.setSoChildId(soChild.getId());
				List<SoThirdpartyDTO> soThirdpartyDTOList =  soThirdpartyReadService.findSoThirdpartyAll(soThirdpartyDTO);
				if (EmptyUtil.isNotEmpty(soThirdpartyDTOList)) {
					soThirdpartyDTO.setId(soThirdpartyDTOList.get(0).getId());
				}

				// 充值状态:0充值中 1成功 9撤销，刚提交都返回0
				switch (Integer.parseInt(rechargePhoneResult.getGameState())) {
				case 0:
					// 充值中,不做任何处理,等待下次定时查询
					break;
				case 1:
					// 充值成功
					// 1)更新第三方子订单
					SoDTO oldSoDTO = findSoById(soChild.getSoId());
					soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(1));
					soThirdpartyDTO.setThirdpartyId(soThirdpartyDTO.getThirdpartyId() == null ? rechargePhoneResult.getSporderId() : null);
					soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);
					// 2)订单状态变更为已完成,确认状态为已完成
					soWriteService.changeOrderStatusByOrderId(oldSoDTO.getOrderCode(), soChild.getSoId(), OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(), 3, null, null);

					// 记录订单完成日志
					recordConfirmOrderLog(oldSoDTO, "SoFacade_confirmRechargeResult");

					break;
				case 9:
					// 充值失败
					SoDTO oldSoDTO_ = findSoById(soChild.getSoId());
					// 1)更新第三方子订单
					soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(2));
					soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);
					// 2)订单状态为 待付款,自动退款
					// 生成退款单编号,以防第三方话费充值失败需取消订单自动退款
					List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
					List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soChild.getSoId());

					// 订单状态/确认状态/支付状态切换为 已取消/已取消/已退款,取消订单自动退款(完善)
					soService.cancelAndRefundOrderWithTx(null,oldSoDTO_, items, null, soRefundNOList.get(0),
							soRefundNOList.get(1), null);

					// 5.取消订单成功的优惠卷相关操作
					couponUnitWriteService.updateCouponByCancelOrderWithTx(oldSoDTO_.getId());

					// 3)充值失败原因处理
					log.error("话费充值失败,返回状态: 撤回, 子订单编号: " + soChild.getChildCode());
					break;
				default:
					break;
				}

			} else if (rechargePhone != null && RechargePhoneErrorCode.ERROR_ORDERCODE.getCode().equals(rechargePhone.getErrorCode())) {
				// 错误的订单号
				log.error("请求话费充值结果失败,失败原因: 错误的订单号, 子订单编号: " + soChild.getChildCode());
			}
		}

	}

	public List<SoChildDTO> findSoChildAll(SoChildDTO soChildDTO) {
		return soChildReadService.findSoChildAll(soChildDTO);
	}

	/**
	 * 取消订单成功,变更优惠卷
	 * @param orderId
	 */
	public int updateCouponByCancelOrderWithTx(Long orderId) {

		return couponUnitWriteService.updateCouponByCancelOrderWithTx(orderId);
	}

    public void confirmQCResult() {
		//查询所有第三方订单类型为2(券仓)的等待队列
		ThirdpartyAwaitQueueDTO awaitQueueDTO = new ThirdpartyAwaitQueueDTO();
		awaitQueueDTO.setThirdpartyType(Integer.valueOf(2));
		List<ThirdpartyAwaitQueueDTO> awaitQueueDTOList=thirdpartyAwaitQueueReadService.findThirdpartyAwaitQueueAll(awaitQueueDTO);
		for(ThirdpartyAwaitQueueDTO dto:awaitQueueDTOList){
			SoChildDTO soChildDTO = soChildReadService.findSoChildById(dto.getSoId());
			//查询订单信息
			SoDTO soById = soReadService.findSoById(soChildDTO.getSoId());
			Long puId = soItemReadService.findPuIdBySoChildId(soChildDTO.getId());
			SkuDTO skuDTO=skuReadService.findSkuByPuId(puId);
			//2.更新第三方订单信息
			SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
			soThirdpartyDTO.setSoChildId(dto.getSoId());
			List<SoThirdpartyDTO> soThirdpartyDTOList = soThirdpartyReadService
					.findSoThirdpartyAll(soThirdpartyDTO);
			soThirdpartyDTO.setId(soThirdpartyDTOList.get(0).getId());
			String result="";
			try {
				log.info("券仓订单查询第三方接口入口");
				log.info("[券仓订单查询]参数:子订单id:"+dto.getOrderCode());
				result = qCUtil.queryOrder(dto.getOrderCode());
				log.info("券仓订单查询第三方接口出口,返回值:"+result);
			} catch (Exception e) {
				log.error("[券仓卡券定时查询任务异常]message:"+e.getMessage());
			}
			//解析结果
			QCResult qcResult = JsonUtils.jsonToPojo(result, QCResult.class);
			//3.交易成功,删除等待队列
			ThirdpartyAwaitQueueDTO thirdpartyAwaitQueueDTO = new ThirdpartyAwaitQueueDTO();
			thirdpartyAwaitQueueDTO.setOrderCode(soChildDTO.getChildCode());
			thirdpartyAwaitQueueWriteService.deleteThirdpartyAwaitQueueByCodeWithTx(thirdpartyAwaitQueueDTO);



			if(qcResult.getHeader().getRespcode().equals("RMP0000")){
				//交易成功
				//2.1保存卡号卡密
				//todo
				List<QCInfos2> infos2 = qcResult.getBody().getInfos().get(0).getInfos2();
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				List<ErCardRecordDTO> cardList = new ArrayList<>();
				try {
					for(QCInfos2 qc:infos2){
						//卡密(先保存临时表)
						ECardDTO eCardDTO= new ECardDTO();
						ECardTempDTO eCardTempDTO = new ECardTempDTO();
						eCardTempDTO.setCardNumber(qc.getBarcode());
						eCardTempDTO.setCardThick(qc.getBarpwd());
						if(EmptyUtil.isEmpty(qc.getDuedt())){
							eCardTempDTO.setEndTime(null);
							eCardDTO.setEndTime(null);

						}else{
							eCardTempDTO.setEndTime(format.parse(qc.getDuedt()));
							eCardDTO.setEndTime(format.parse(qc.getDuedt()));
						}
						eCardTempDTO.setShortUrl(qc.getShorturl());
						eCardTempDTO.setSkuId(skuDTO.getId());
						eCardTempDTO.setSkuName(skuDTO.getSkuName());
						eCardTempDTO.setSkuSerialNumber(skuDTO.getSkuSerialNumber());
						eCardTempDTO.setType(Integer.valueOf(5));
						eCardTempDTO.setUserId(soById.getUserId());
						eCardTempDTO.setOrderCode(soById.getOrderCode());
						Long aLong = eCardTempWriteService.insertECardTempWithTx(eCardTempDTO);

						eCardDTO.setCardNumber(qc.getBarcode());
						eCardDTO.setCreateUserid(soById.getUserId());
						eCardDTO.setEndTime(format.parse(qc.getDuedt()));
						eCardDTO.setSkuId(skuDTO.getId());
						eCardDTO.setSkuName(skuDTO.getSkuName());
						eCardDTO.setSkuSerialNumber(skuDTO.getSkuSerialNumber());
						eCardDTO.setType(Integer.valueOf(5));//类型5为券仓卡券,1为京东e卡
						eCardDTO.setIsAllocation(Integer.valueOf(1));//1:已分配,0:未分配
						eCardDTO.setIsValid(Integer.valueOf(1));//是否有效,1 有效
						eCardDTO.setAllocationTime(new Date());
						eCardDTO.setUserId(soById.getUserId());
						eCardDTO.setOrderCode(soById.getOrderCode());
						eCardDTO.setStartTime(new Date());
						eCardDTO.setShortUrl(qc.getShorturl());
						if(EmptyUtil.isNotEmpty(qc.getBarpwd())){
							String cardThick = qc.getBarpwd();
							String salt = SaltUtils.getRandomSalt();
							String encrypt = QEncodeUtil.aesEncrypt(cardThick, salt);
							qc.setBarpwd(encrypt);
							CardSaltDTO cardSaltDTO = new CardSaltDTO();
							String uuid = UUIDBuild.getUUID();
							cardSaltDTO.setUuid(uuid);
							cardSaltDTO.setSaltValue(salt);
							qc.setUuid(uuid);
							eCardDTO.setUuid(uuid);
							cardSaltWriteService.insertCardSaltWithTx(cardSaltDTO);
						}
						//插入卡盐
						eCardDTO.setCardThick(qc.getBarpwd());
						eCardDTO.setPlatformId(soById.getPlatformId());
						eCardWriteService.insertECardWithTx(eCardDTO);
						//操作成功,删除临时表
						ECardTempDTO tempDTO = new ECardTempDTO();
						tempDTO.setId(aLong);
						eCardTempWriteService.deleteECardTempWithTx(tempDTO);
					}


				} catch (Exception e) {
					qCUtil.sendSysEmail(soById.getOrderCode(),result);
					log.error("盐加密,保存失败,联系管理员,message:" );
					log.error(e.getMessage(),e );
				}


				//2.2更新第三方订单(将第三方订单状态修改成1,成功,记录第三方订单id),并更新订单状态为已完成
				soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(1));
				soThirdpartyDTO.setThirdpartyId(qcResult.getBody().getTranid());
				SoDTO soDTO = new SoDTO();
				soDTO.setId(soById.getId());
				soDTO.setOrderStatus(Integer.valueOf(4));
				soDTO.setOrderConfirmStatus(Integer.valueOf(3));
				soThirdpartyWriteService.updateSoThirdpartyAndSoWithTx(soThirdpartyDTO);
				soWriteService.changeOrderStatusByOrderId(soById.getOrderCode(), soById.getId(), OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(), 3, null, null);



				//记录订单完成日志
				recordConfirmOrderLog(soById, "SoFacade_confirmRechargeResult");

			}else{
				//交易失败
				if(qcResult.getHeader().getRespcode().equals("RMP0021")||qcResult.getHeader().getRespcode().equals("RMP0044")){
					//需要预警处理(发送邮件)
					Long sum=soItemReadService.findSoChildPUNum(soChildDTO.getId());//查询子订单的购物数量
					qCUtil.sendSysExEmail(qcResult,soById.getOrderCode(),sum.intValue());
				}
				//1)跟新第三方订单状态为失败
				soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(2));

				// 2)订单状态为 待付款,自动退款
				// 生成退款单编号,以防第三方话费充值失败需取消订单自动退款
				List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
				List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soById.getId());

				// 订单状态/确认状态/支付状态切换为 已取消/已取消/已退款,取消订单自动退款(完善)
				soService.cancelAndRefundOrderWithTx(null,soById, items, null, soRefundNOList.get(0),
						soRefundNOList.get(1), null);

				// 3.取消订单成功的优惠卷相关操作
				couponUnitWriteService.updateCouponByCancelOrderWithTx(soById.getId());

				//4)记录失败原因
				log.error("[券仓卡券购买]购买失败,原因:code:"+qcResult.getHeader().getRespcode()+";message:"+qcResult.getHeader().getRespmsg());

			}


		}
    }

    /**
     * 查询需要预警的子订单列表，并发送预警邮件
     */
    public void sendOrderWarningMail() throws Exception {

    	int duringHours = Integer.parseInt(SpringContextTool.getProperty("warning.order.duringhours"));
    	String recerverJsonStr = SpringContextTool.getProperty("warning.order.receiver");
    	List<String> receiverList = JSONArray.parseArray(recerverJsonStr, String.class);
    	List<SoChildDTO> soChildList = soChildReadService.findWarningChildOrder(DateUtils.addHours(new Date(), -duringHours));
    	if (EmptyUtil.isNotEmpty(soChildList)) {
    		List<MerchantDTO> merchantList = merchantReadService.findMerchantAll(new MerchantDTO());
    		Map<Long, String> merchantMap = new HashMap<>();
    		for (MerchantDTO merchant : merchantList) {
    			merchantMap.put(merchant.getId(), merchant.getName());
    		}
    		List<PlatformDTO> platformList = platformReadService.findAllPlatform();
    		Map<Long, String> platformMap = new HashMap<>();
    		for (PlatformDTO platform : platformList) {
    			platformMap.put(platform.getId(), platform.getName());
    		}
    		Map<String, List<SoChildDTO>> platformSoChildMap = new HashMap<>();
    		for (SoChildDTO soChild : soChildList) {
    			String mobile = soChild.getGoodReceiverMobile();
    			if (EmptyUtil.isEmpty(mobile)) {
    				soChild.setGoodReceiverMobile(soChild.getGoodReceiverPhone());
    			}
    			String platformName = platformMap.get(soChild.getPlatformId());
    			soChild.setMerchantName(merchantMap.get(soChild.getPerformingParty()));
    			soChild.setPlatformName(platformName);
    			if (!platformSoChildMap.containsKey(platformName)) {
    				platformSoChildMap.put(platformName, new ArrayList<SoChildDTO>());
    			}
    			platformSoChildMap.get(platformName).add(soChild);
    		}
    		exportToFileAndSendMail(platformSoChildMap, receiverList, duringHours);
    	}
    }

    private void exportToFileAndSendMail(Map<String, List<SoChildDTO>> platformSoChildMap, List<String> receiverList, int duringHours) throws Exception {
    	List<String> fieldNames=new ArrayList<String>();
		fieldNames.add("运营方");
		fieldNames.add("订单所属平台");
		fieldNames.add("订单编号");
		fieldNames.add("子订单编号");
		fieldNames.add("付款时间");
		fieldNames.add("子订单金额");
		fieldNames.add("用户姓名");
		fieldNames.add("联系方式");

		List<String> fieldCodes=new ArrayList<String>();
		fieldCodes.add("merchantName");
		fieldCodes.add("platformName");
		fieldCodes.add("orderCode");
		fieldCodes.add("childCode");
		fieldCodes.add("orderPaymentConfirmDate");
		fieldCodes.add("amount");
		fieldCodes.add("goodReceiverName");
		fieldCodes.add("goodReceiverMobile");

		for (Entry<String, List<SoChildDTO>> entry : platformSoChildMap.entrySet()) {
			ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start("./异常履约订单/", "./异常履约订单/", entry.getKey() + "异常履约订单", fieldNames, fieldCodes, 100);
			excelExportSXXSSF.writeDatasByObject(entry.getValue());
			String filePath = excelExportSXXSSF.exportFile();
			List<File> attachments = new ArrayList<>();
			attachments.add(new File(filePath));
			String content = entry.getKey() + "有" + entry.getValue().size() + "笔订单超过" + duringHours + "小时未发货，请及时检查订单情况并跟进，具体订单信息见附件。";
			SendMail.sendMailAsync(receiverList, "订单履约异常的提醒", content, attachments);
		}
    }

	/**
	 * 查询有效、未分配的Unit对应的spu预警期并发送预警邮件
	 * @throws Exception
	 */
	//预警级别
	private Long FIRST_PRECAUTIOUS_ATT_NAME_ID = 6L;
	private Long SECOND_PRECAUTIOUS_ATT_NAME_ID = 7L;
	private Long THIRD_PRECAUTIOUS_ATT_NAME_ID = 8L;

	public void sendPrecautiousWarningMail(Long platformId) throws Exception {

		//查询不同级别的预警集合(一级，二级，三级)
		Map<Long,String> preFirst = skuReadService.findSkuIdAndPreDaysByPreAttNameId(FIRST_PRECAUTIOUS_ATT_NAME_ID);
		log.info("一级："+ preFirst);
		Map<Long,String> preSecond = skuReadService.findSkuIdAndPreDaysByPreAttNameId(SECOND_PRECAUTIOUS_ATT_NAME_ID);
		log.info("二级："+ preSecond);
		Map<Long,String> preThird = skuReadService.findSkuIdAndPreDaysByPreAttNameId(THIRD_PRECAUTIOUS_ATT_NAME_ID);
		log.info("三级："+ preThird);
		//查询需要判断预警期的集合
		ECardDTO eCardDTO = new ECardDTO();
		eCardDTO.setIsValid(1);
		eCardDTO.setIsAllocation(0);
		eCardDTO.setPlatformId(platformId);
		Pagination page = new Pagination();
		page.setPageNo(1);
		page.setPageSize(1000);
		page.setLimitStart((page.getPageNo()-1)*page.getPageSize() );
		boolean flag = true;
		List<ECardDTO> firstSheet = new ArrayList<>();
		List<ECardDTO> secondSheet = new ArrayList<>();
		List<ECardDTO> thirdSheet = new ArrayList<>();

		while (flag) {
			PageResult<ECardDTO> pageResult = eCardReadService.findECardOfPage(new FindECardOfPageFO(eCardDTO, page));
			log.info("eCard集合大小："+ pageResult.getList().size());
			if (EmptyUtil.isEmpty(pageResult.getList())) {
				flag = false;
				break;
			}
			for ( ECardDTO card : pageResult.getList() ) {

				if (preThird.containsKey(card.getSkuId())) {
					cardToList(preThird, card, thirdSheet );
				}
				if (preSecond.containsKey(card.getSkuId())) {
					cardToList(preSecond, card, secondSheet );
				}
				if (preFirst.containsKey(card.getSkuId())) {
					cardToList(preFirst, card, firstSheet );
				}
			}
			page.setPageNo(page.getPageNo()+1);
			page.setLimitStart((page.getPageNo()-1)*page.getPageSize() );
		}

		//导出，发邮件
		if (EmptyUtil.isNotEmpty(firstSheet) || EmptyUtil.isNotEmpty(secondSheet) || EmptyUtil.isNotEmpty(thirdSheet) ) {
			exportToFileAndSendPrecautiousMail(firstSheet, secondSheet, thirdSheet, platformId);
		}


	}
	/**
	 * 需要预警的card添加到导出的sheetList中
	 */
	private void cardToList(Map<Long, String> preMap, ECardDTO card, List<ECardDTO> sheetList ) {
		if (EmptyUtil.isNotEmpty(preMap.get(card.getSkuId()))) {
			long preDays = Long.parseLong(preMap.get(card.getSkuId())) + 1;
			int daydiff = daydiff(new Date(), card.getEndTime());
			if ((daydiff - preDays) == 0) {
				sheetList.add(card);
			}
		}
	}

	/**
	 * 两个日期相差天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	private int daydiff(Date fDate, Date oDate) {
		/*Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;*/

		int days = (int) ((oDate.getTime() - fDate.getTime()) / (1000*3600*24));
		return days + 1;

	}

	private void exportToFileAndSendPrecautiousMail(List<ECardDTO> firstSheet, List<ECardDTO> secondSheet, List<ECardDTO> thirdSheet, Long platformId) throws Exception {
		log.info("sheet1:"+firstSheet);
		log.info("sheet2:"+secondSheet);
		log.info("sheet3:"+thirdSheet);

		//读取配置文件
		//int duringHours = Integer.parseInt(properties.getProperty("warning.order.duringhours"));
		String recerverJsonStr = SpringContextTool.getProperty("warning.order.receiver");
		List<String> receiverList = JSONArray.parseArray(recerverJsonStr, String.class);

		//测试人邮箱
		/*receiverList.clear();
		receiverList.add("fei.fang@fugj.com");
		receiverList.add("qing.ye@fugj.com");*/

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//区分平台
		String platformName = "";
		if (platformId == 7L) {
			platformName += "大厨管家";
		} else if (platformId == 2L) {
			platformName += "富宏云采";
		}
		//excel
		Workbook wb = new XSSFWorkbook();
		Sheet s1 = wb.createSheet("一级预警-"+sdf.format(new Date())+"-"+platformName);
		Sheet s2 = wb.createSheet("二级预警-"+sdf.format(new Date())+"-"+platformName);
		Sheet s3 = wb.createSheet("三级预警-"+sdf.format(new Date())+"-"+platformName);
		String[] header = {"sku编号", "sku名称", "unit编号", "开始时间", "结束时间"};
		// 创建表头
		Row head1 = s1.createRow(0);
		Row head2 = s2.createRow(0);
		Row head3 = s3.createRow(0);
		for (int i = 0; i < header.length; i++) {
			head1.createCell(i).setCellValue(header[i]);
		}
		for(int i=0;i<header.length;i++){
			head2.createCell(i).setCellValue(header[i]);
		}
		for(int i=0;i<header.length;i++){
			head3.createCell(i).setCellValue(header[i]);
		}
		// completeOrderListSheet(s, voList);
		completeSheet(s1,firstSheet);
		completeSheet(s2,secondSheet);
		completeSheet(s3,thirdSheet);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			wb.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//String upload = Upload.fastDFSUpload(bos.toByteArray(), "xlsx");

		String fileLocation = "/";
		String fileName = "Unit预警-"+sdf.format(new Date())+"-"+platformName+".xlsx";
		fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
		File file = byte2File(bos.toByteArray(), fileLocation, fileName);


		//附件
		List<File> attachments = new ArrayList<>();
		attachments.add(file);
		String subject = "失效预警-"+sdf.format(new Date())+"-"+platformName;
		String content = "您设置的Unit失效预警已触发，其中一级预警的Unit共有"+firstSheet.size()+"张，二级预警的Unit共"+secondSheet.size()+"张，" +
				"三级预警的Unit共"+thirdSheet.size()+"张，请及时进行处理。请打开相应附件查看详情内容。";
		SendMail.sendMailAsync(receiverList,subject, content,attachments);
	}

	/**
	 * 数据  ----》  Excel
	 * @param s
	 * @param dtoList
	 */
	private void completeSheet(Sheet s, List<ECardDTO> dtoList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String[] header = {"sku编号", "sku名称", "unit编号", "开始时间", "结束时间"};
		for (int i = 0; i < dtoList.size(); i++) {
			Row r = s.createRow(i + 1);
			ECardDTO dto = dtoList.get(i);
			r.createCell(0).setCellValue(dto.getSkuSerialNumber());
			r.createCell(1).setCellValue(dto.getSkuName());
			r.createCell(2).setCellValue(StringUtils.protectProductSerialNumber(dto.getId()));
			r.createCell(3).setCellValue( sdf.format(dto.getStartTime()));
			r.createCell(4).setCellValue( sdf.format(dto.getEndTime()));
		}
	}

	/**
	 * 根据byte数组，生成文件
	 * @param bfile 文件数组
	 * @param filePath 文件存放路径
	 * @param fileName 文件名称
	 */
	public  File byte2File(byte[] bfile,String filePath,String fileName) {
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		try {
			File dir=new File(filePath);
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdirs();
			}
			file=new File(filePath+fileName);
			if (file.exists()) {
				FileWriter fileWriter =new FileWriter(file);
				fileWriter.write("");
				fileWriter.flush();
				fileWriter.close();
			}
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(bfile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(bos != null){
					bos.close();
				}
				if(fos != null){
					fos.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return file;
	}


	public int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordDTO recordDTO) {
		return exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);
	}

	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordByOrderCode(String orderCode) {
		return exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(orderCode);
	}

	public void updateCouponUnitRemoveLock(String oldUnitCode) {
		couponUnitWriteService.updateCouponUnitRemoveLock(oldUnitCode);
	}

	public void jdOrderConfirm() {
		List<JdOrderAwaitQueueDTO> jdOrderAwaitQueueAll = jdOrderAwaitQueueReadService.findJdOrderAwaitQueueAll(new JdOrderAwaitQueueDTO());
		for(JdOrderAwaitQueueDTO dto:jdOrderAwaitQueueAll){
			SoDTO soDTO = soReadService.findSoById(dto.getSoId());
			UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
			//处理每一个订单的预占库存
			int result = soService.jdOrderConfirm(userDTO.getName(),userDTO.getId(),dto.getId(), dto.getJdOrderId(), dto.getSoChildId(), dto.getSoId(), dto.getOrderCode());

			if(result==1){
				//预占成功
				// 支付成功,且第三方订单处理成功,若使用了优惠卷,优惠卷变为已使用
				couponUnitWriteService.updateCouponByPaySuccessWithTx(dto.getSoId());
			}else if(result==2){
				//预占失败
				// 生成退款单编号,以防第三方话费充值失败需取消订单自动退款
				List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
				// 支付成功,但第三方订单处理失败,取消订单自动退款
				List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(dto.getSoId());
				SoDTO soById = soReadService.findSoById(dto.getSoId());
				soService.cancelAndRefundOrderWithTx(userDTO.getName(),soById, items, userDTO.getId(), soRefundNOList.get(0),
						soRefundNOList.get(1), new HttpServletRequestDTO());

			}
		}
	}

	public void checkJdAccount(JedisUtil jedisUtil) {
		//String accessToken = jdUtils.getAccessToken(jedisUtil);
		String accessToken = jdUtils2.getAccessToken(jedisUtil);
		JSONObject jdAccount = jdUtils.getJdAccount(accessToken);
		if(EmptyUtil.isNotEmpty(jdAccount)){
			JSONObject balance = jdAccount.getJSONObject("balance");
			BigDecimal limit = balance.getBigDecimal("remainLimit");
			logger.info(balance.toJSONString());
			if(limit.compareTo(BigDecimal.valueOf(jdAccountLimit))<0){
				jdUtils.sendJdAcoountEmail(limit.toString(),jdAccountLimit.intValue()+"");
			}

		}

	}

    public void jdOrderPayStatus(JedisUtil jedisUtil) {
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		JSONArray jdProductChange = jdUtils.getJdProductChange(token, "14");
		if(EmptyUtil.isNotEmpty(jdProductChange)){
			for(Object obj:jdProductChange) {
				JSONObject ob = (JSONObject) obj;
				String messId = ob.getString("id");
				JSONObject result = ob.getJSONObject("result");
				String jdOrderId = result.getString("orderId");
				SoDTO soDTO=soReadService.findSoByThirdpartyId(jdOrderId);
				if(EmptyUtil.isNotEmpty(soDTO)){
					if(soDTO.getOrderStatus()!=10){
						UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
						List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
						// 支付成功,但第三方订单处理失败,取消订单自动退款
						List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soDTO.getId());
						SoDTO soById = soReadService.findSoById(soDTO.getId());
						soService.cancelAndRefundOrderWithTx(userDTO.getName(),soById, items, userDTO.getId(), soRefundNOList.get(0),
								soRefundNOList.get(1), new HttpServletRequestDTO());
					}
					jdUtils.deleteJdMessage(token,messId);
				}

			}
		}

	}

	@Transactional
    public void jdOrderStatus(JedisUtil jedisUtil) {
		//查询出所有状态为已付款,已发货的京东订单
		List<Long> thirdpartyIdList=soThirdpartyReadService.getThirdpartyIdListByStatus();
		logger.info("定时任务需要查询状态的京东订单:"+thirdpartyIdList.toString());
		List<Long> jdSoChildIdList = new ArrayList<>();
		//根据京东母订单id查询订单详情处理拆单业务
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		for(Long jdOrderId:thirdpartyIdList){
			Long soChildId=soChildReadService.findSoChildIdByThirdpartyId(jdOrderId);
			SoChildDTO childDTO = soChildReadService.findSoChildById(soChildId);

			JSONObject jdOrderDetail = jdUtils.getJdOrderDetail(token, String.valueOf(jdOrderId));



			if(EmptyUtil.isNotEmpty(jdOrderDetail)){
				logger.info("定时任务查询京东订单("+String.valueOf(jdOrderId)+")明细:"+jdOrderDetail.toJSONString());
                Integer jdOrderState = jdOrderDetail.getInteger("jdOrderState");
                Integer type = jdOrderDetail.getInteger("type");
				if(type.equals(Integer.valueOf(1))){
					//存在子订单(已发生拆单)
					JSONArray cOrder = jdOrderDetail.getJSONArray("cOrder");
					if(EmptyUtil.isNotEmpty(cOrder)){
						boolean isExistError = false;
						for(int i=0;i<cOrder.size();i++){

							SoItemDTO itemDTO = new SoItemDTO();
							itemDTO.setSoChildId(soChildId);
							List<SoItemDTO> soItemDTOList = soItemReadService.findAll(itemDTO);
							JSONObject order = cOrder.getJSONObject(i);
							Long childId = order.getLong("jdOrderId");
							BigDecimal orderPayPrice = order.getBigDecimal("orderPrice");

							//进行拆单
							JSONArray skuList = order.getJSONArray("sku");
							List<Map<Object, Object>> puList = new ArrayList<>();
							for (int j = 0; j < skuList.size(); j++) {
								HashMap<Object, Object> map = new HashMap<>();
								JSONObject sku = skuList.getJSONObject(j);
								Long skuId = sku.getLong("skuId");
								//查询原订单商品项
								//Long puId = commodityProductUnitReadService.findPuIdByExtendSkuId(skuId);
								//Long puId =jdProductInnerIdReadService.findPuIdByJdSkuId(skuId);
/*								Long puId =commodityProductUnitReadService.findPuIdByJdSkuId(skuId);
*/
								for (SoItemDTO soItemDTO : soItemDTOList) {
                                    if (skuId.equals(soItemDTO.getPuId())){
                                        Integer num = sku.getInteger("num");
                                        map.put("itemId", soItemDTO.getId());
                                        map.put("num", num);
                                        puList.add(map);
                                    }
								}
							}
                            try {
                                Long newSoChildId;
                                if (i < cOrder.size() - 1) {

                                    newSoChildId = orderOpen(orderPayPrice, childId, soChildId, puList, Integer.valueOf(1));

                                } else {
                                    newSoChildId = orderOpen(orderPayPrice, childId, soChildId, puList, Integer.valueOf(0));
                                }

                                //更改物流状态
                                soStatusChange(newSoChildId, jdOrderState);
                                logger.info("添加一个子订单:" + childId);
                                jdSoChildIdList.add(childId);
                            } catch (com.egeo.exception.BusinessException e) {
                                e.printStackTrace();
                                logger.error("拆弹失败:childId-{},msg-{}", childId, e.getMessage());
                                logger.error("拆弹失败:childId" + childId + "soChildId" + soChildId);
                                //throw e;
								isExistError = true;
								continue;
                            } catch (Exception e) {
                                e.printStackTrace();
                                logger.error("拆弹失败:childId-{},msg-{}", childId, e.getMessage());
                                logger.error("拆弹失败:childId" + childId + "soChildId" + soChildId);
                                //throw e;
								isExistError = true;
								continue;
                            }
						}
						if(isExistError){
							continue;
						}
					}
				}else{
					logger.info("添加一个子订单:"+jdOrderId);
					jdSoChildIdList.add(jdOrderId);
                    //更改物流状态
                    soStatusChange(soChildId, jdOrderState);
				}

				//如果是拒收订单则发送邮件预警
                List<Integer> unReceived = Arrays.asList(13,17,22);
                if(unReceived.contains(jdOrderState)){
                    //发送拒收预警
                    jdUtils.sendDeliveryErrEmail(jdOrderState,childDTO.getSoId());
                }

			}else {
				logger.info("定时任务查询京东订单("+String.valueOf(jdOrderId)+")明细为空:");
			}

		}
		//拆弹完成后,查询物流业务
		for(Long jdOrderChildId:jdSoChildIdList){
			logger.info("定时任务京东子订单id:"+jdOrderChildId);
			SoChildDTO dto = new SoChildDTO();
			dto.setThirdpartySoChildId(jdOrderChildId);
			List<SoChildDTO> soChildAll = soChildReadService.findSoChildAll(dto);
			if(EmptyUtil.isNotEmpty(soChildAll)){
				if(soChildAll.size()>1){
					logger.error("当前第三方订单号对应多个子订单:"+jdOrderChildId);
					continue;
				}
				SoPackageDTO packageDTO = new SoPackageDTO();
				packageDTO.setSoChildId(soChildAll.get(0).getId());
				List<SoPackageDTO> soPackageAll = soPackageReadService.findSoPackageAll(packageDTO);
				if(EmptyUtil.isNotEmpty(soPackageAll)){
					continue;
				}
				JSONObject jdOrderDelivery = jdUtils.getJdOrderDelivery(token, jdOrderChildId + "");
				logger.info("定时任务物流信息查询:"+jdOrderDelivery.toString());
				if(EmptyUtil.isNotEmpty(jdOrderDelivery)){
					JSONArray waybillCodeList = jdOrderDelivery.containsKey("waybillCode")?jdOrderDelivery.getJSONArray("waybillCode"):null;
					JSONArray orderTrack = getJdOrderTrack(jdOrderDelivery,jdOrderChildId);
					if(EmptyUtil.isNotEmpty(waybillCodeList)){
						if(waybillCodeList.size()>1){
							continue;
						}
						JSONObject waybillCode = (JSONObject) waybillCodeList.get(0);
						String deliveryOrderId = waybillCode.getString("deliveryOrderId");
						String carrier = waybillCode.getString("carrier");
						if (EmptyUtil.isNotEmpty(deliveryOrderId) || EmptyUtil.isNotEmpty(carrier)){
							SoPackageDTO soPackageDTO = new SoPackageDTO();
							soPackageDTO.setDeliveryCode(deliveryOrderId);
							soPackageDTO.setDeliveryCompanyName(carrier);
							soPackageDTO.setSoChildId(soChildAll.get(0).getId());
							soPackageDTO.setSoId(soChildAll.get(0).getSoId());
							soPackageDTO.setMerchantId(6L);
							Date deliveryDate = getDeliveryDate(orderTrack, deliveryOrderId);
							if (Objects.nonNull(deliveryDate)){
								soPackageDTO.setDeliveryDate(deliveryDate);
							}
							soPackageWriteService.insertSoPackageWithTx(soPackageDTO);
						}
					}
				}
			}


		}




    }

	private Date getDeliveryDate(JSONArray orderTrack, String deliveryOrderId) {
		Date deliveryDate=new Date();
		if(EmptyUtil.isNotEmpty(orderTrack) && orderTrack.size() >0){
			try {
				JSONObject track= orderTrack.getJSONObject(0);
				String deliverTime=track.containsKey("msgTime")?track.getString("msgTime"):null;
				if (EmptyUtil.isNotEmpty(deliverTime)){
					deliveryDate=DateUtils.parseDate(deliverTime,"yyyy-MM-dd HH:mm:ss");
				}
				logger.info("定时任务查询订单状态时获取到物流发货时间deliveryCode-{},结果:{}",deliveryOrderId,deliverTime);
			}catch (Exception e){
				logger.error("定时任务更新物流发货时间失败，发生异常:deliveryCode-{},{}", deliveryOrderId,e);
				deliveryDate=new Date();
			}
		}
		return deliveryDate;
	}

	private JSONArray getJdOrderTrack(JSONObject jdOrderDelivery,Long jdOrderChildId) {
		try {
			String key="orderTrack";
			if(!jdOrderDelivery.containsKey(key)){
				return null;
			}
			return jdOrderDelivery.getJSONArray(key);
		}catch (Exception e){
			logger.error("定时任务查询子订单:{}状态时获取orderTrack发生异常:{}",jdOrderChildId,e);
		}
		return null;
	}

	private void soStatusChange(Long soChildId,Integer jdStatus){
		logger.info("定时任务更新订单物流状态:"+soChildId+";状态:"+jdStatus);
		SoChildDTO soChildDTO = soChildReadService.findSoChildById(soChildId);
		if(EmptyUtil.isEmpty(soChildDTO)){
			logger.error("子订单未查询到:"+soChildId);
			return;
		}
		SoDTO soDTO = soReadService.findSoById(soChildDTO.getSoId());
		List<Integer> unSend = Arrays.asList(2,3,4,5,6);
        List<Integer> sendReady= Arrays.asList(7,8,9,10,29,30);
        List<Integer> sending= Arrays.asList(11,12,16,31);
        List<Integer> received= Arrays.asList(14,19,21);
        List<Integer> unReceived= Arrays.asList(13,17);

		boolean pushOrder=false;
        if(unSend.contains(jdStatus)){
            logger.info("定时任务子订单:{}待发货,母订单已付款(无需更改)",soChildId);
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(0));
            dto.setId(soChildDTO.getId());
            dto.setThirdpartySoChildStatus(jdStatus);
            int rt = soChildWriteService.updateSoChildWithTx(dto);
			logger.info("定时任务子订单:{}待发货,母订单已付款(无需更改),更新结果:{}",soChildId,rt);
			if(rt >0){
				pushOrder=true;
			}
		}else if(sendReady.contains(jdStatus)){
            logger.info("定时任务子订单:{}分拣中,母订单已付款(无需改动)",soChildId);
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(1));
            dto.setId(soChildDTO.getId());
            dto.setThirdpartySoChildStatus(jdStatus);
            int rt = soChildWriteService.updateSoChildWithTx(dto);
			logger.info("定时任务子订单:{}分拣中,母订单已付款(无需改动),更新结果:{}",soChildId,rt);
			if(rt >0){
				pushOrder=true;
			}
        }else if(sending.contains(jdStatus)){
		    logger.info("定时任务子订单:{}已发货,母订单已发货",soChildId);
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(2));
            dto.setId(soChildDTO.getId());
            dto.setThirdpartySoChildStatus(jdStatus);
            int rt = soChildWriteService.updateSoChildWithTx(dto);
			logger.info("定时任务子订单:{}已发货,母订单已发货,更新结果:{}",soChildId,rt);
            SoDTO newSo = new SoDTO();
            newSo.setId(soDTO.getId());
            newSo.setOrderStatus(Integer.valueOf(2));
            soWriteService.updateOrderWithTX(newSo);
			if(rt >0){
				pushOrder=true;
			}
		}else if(received.contains(jdStatus)){
            logger.info("定时任务子订单:{}已签收,母订单已完成",soChildId);
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(3));
            dto.setId(soChildDTO.getId());
            dto.setThirdpartySoChildStatus(jdStatus);
            int rt = soChildWriteService.updateSoChildWithTx(dto);
			logger.info("定时任务子订单:{}已签收,母订单已完成更新结果:{}",soChildId,rt);
			if (Objects.equals(soDTO.getOrderStatus(),1)){
				SoDTO newSo = new SoDTO();
				newSo.setId(soDTO.getId());
				newSo.setOrderStatus(Integer.valueOf(2));
				soWriteService.updateOrderWithTX(newSo);
			}
			if(rt >0){
				pushOrder=true;
			}
		}else if(unReceived.contains(jdStatus)){
            logger.info("定时任务子订单:{}据收,母订单已发货",soChildId);
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(4));
            dto.setId(soChildDTO.getId());
            dto.setThirdpartySoChildStatus(jdStatus);
            int rt = soChildWriteService.updateSoChildWithTx(dto);
			logger.info("定时任务子订单:{}据收,母订单已发货,更新结果:{}",soChildId,rt);
            SoDTO newSo = new SoDTO();
            newSo.setId(soDTO.getId());
            newSo.setOrderStatus(Integer.valueOf(3));
            newSo.setOrderConfirmStatus(Integer.valueOf(1));
            soWriteService.updateOrderWithTX(newSo);
			if(rt >0){
				pushOrder=true;
			}
		}
		if (pushOrder){
			pushOrderManage.pushOrderInfo(soDTO.getId(),null,null);
		}

	}
    private Long orderOpen(BigDecimal orderPayPrice, Long childId, Long soChildId, List<Map<Object, Object>> puList, Integer type){
		/*
		 * 检查子订单物流状态是否是待发货或者分拣中 检查母订单确认状态是否是已确认
		 * 查询母订单下的所有子订单,取得子订单编号尾缀的最大值,+1获取新子订单的编号 检验拆单数量
		 * 原子执行:创建子订单,拷贝订单项,减少原订单项的数量,插入操作流水,更新原子订单数据
		 */
		if(type.equals(Integer.valueOf(1))){
			SoChildDTO sc = soChildReadService.findSoChildById(soChildId);
			if(EmptyUtil.isEmpty(sc)){
				throw new BusinessException("子订单不存在无法拆单");
			}
			Integer deliveryStatus = sc.getDeliveryStatus();
			if (deliveryStatus != 0 && deliveryStatus != 1) {
				throw new BusinessException("子订单物流状态不是待发货或分拣中,无法拆单");
			}
			SoDTO so = soReadService.querySoById(sc.getSoId());
			if (so == null)
				throw new BusinessException("母订单不存在");
			Integer soConfirmStatus = so.getOrderConfirmStatus();
			if (soConfirmStatus != 1) {
				logger.error("母订单:{}状态不是已确认,无法拆单",so.getOrderCode());
				throw new BusinessException("母订单状态不是已确认,无法拆单");
			}
			// 订单项拆出数字校验,同时将待插入/更新的订单项挑出来
			List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(sc.getSoId());
			List<SoItemDTO> insertItems = new ArrayList<>();
			List<SoItemDTO> updateItems = new ArrayList<>();// 包含直接变更子订单id的订单项,需要减少商品数量的订单项
			// 统计新子订单项数据
			BigDecimal amountNew = new BigDecimal(0);
			BigDecimal deliveryFeeNew = new BigDecimal(0);
			BigDecimal productAmountNew = new BigDecimal(0);
			BigDecimal couponDiscountNew = new BigDecimal(0);
			BigDecimal storeDiscountNew = new BigDecimal(0);
			// 统计拆单之后原订单剩余的商品数量
			int ordinaryPuCount = 0;
			for (SoItemDTO item : items) {
				Long itemId = item.getId();
				ordinaryPuCount += item.getPuCount();
				for (int i = 0; i < puList.size(); i++) {
					Map<Object,Object> puMap = puList.get(i);
					Integer numJ = (Integer) puMap.get("num");// 拆出数量
					if (numJ == null) {
						numJ = 0;
					}
					if (numJ > 0) {
						Long itemIdJ = (Long)puMap.get("itemId");
						if (itemId.longValue() == itemIdJ.longValue()) {
							Integer num = item.getPuCount();
							if (numJ.intValue() > num.intValue()) {
								throw new BusinessException("拆出数量不能超过原先数量");
							} else {
								if (numJ.intValue() == num.intValue()) {
									// 该商品被完全拆出
									ordinaryPuCount -= num;
									// 更改订单项的子订单id
									SoItemDTO updateDTO = new SoItemDTO();
									updateDTO.setId(itemId);
									// updateDTO.setSoChildId(soChildId);
									updateItems.add(updateDTO);
									// 计算订单项价值
									BigDecimal cnt = BigDecimal.valueOf(item.getPuCount());
									amountNew = amountNew.add(item.getAfterDiscountPriceAver().multiply(cnt));
									deliveryFeeNew = deliveryFeeNew.add(item.getDeliveryFeeAver().multiply(cnt));
									productAmountNew = productAmountNew.add(item.getPrice().multiply(cnt));
									couponDiscountNew = couponDiscountNew.add(item.getPromotionAver().multiply(cnt));
									storeDiscountNew = storeDiscountNew.add(item.getStoreDiscountAver().multiply(cnt));
								} else {
									// 该商品被部分拆出
									// 更改原订单项的数量
									SoItemDTO updateDTO = new SoItemDTO();
									updateDTO.setId(itemId);
									int puLeft = num - numJ;
									updateDTO.setPuCount(puLeft);
									// 变更原子订单Pu数量
									ordinaryPuCount -= numJ;
									updateItems.add(updateDTO);
									// 新增一条订单项(基本拷贝原订单项的信息)
									SoItemDTO insertDTO = item.copyThis();
									insertDTO.setSource(item.getSource());
									insertDTO.setExternalSkuId(item.getExternalSkuId());
									insertDTO.setAfterDiscountPriceAver(item.getAfterDiscountPriceAver());
									// InsertDTO.setSoChildId(soChildId);
									insertDTO.setPuCount(numJ);
									insertItems.add(insertDTO);
									BigDecimal numJBD = BigDecimal.valueOf(numJ);
									amountNew = amountNew.add(item.getAfterDiscountPriceAver().multiply(numJBD));
									deliveryFeeNew = deliveryFeeNew.add(item.getDeliveryFeeAver().multiply(numJBD));
									productAmountNew = productAmountNew.add(item.getPrice().multiply(numJBD));
									couponDiscountNew = couponDiscountNew.add(item.getPromotionAver().multiply(numJBD));
									storeDiscountNew = storeDiscountNew.add(item.getStoreDiscountAver().multiply(numJBD));
								}
							}
						}
					}
				}
			}
			if (ordinaryPuCount == 0) {
				// 原子订单不能被拆空
				throw new BusinessException("原子订单应至少包含1件商品");
			}
			if (insertItems.size() == 0 && updateItems.size() == 0) {
				throw new BusinessException("没有可拆的项");
			}
			// 查询当前最大子订单编号+1
			String maxChildCode = soChildReadService.queryMaxChildCodePlus1BySoId(sc.getSoId());

			// 组织新增子订单数据
			SoChildDTO insertSoChild = new SoChildDTO();
			insertSoChild.setOrdinaryDeliveryFee(new BigDecimal(0));
			insertSoChild.setNeedCountDeliveryFee(0);
			insertSoChild.setAmount(amountNew);
			insertSoChild.setDeliveryFee(deliveryFeeNew);
			insertSoChild.setProductAmount(productAmountNew);
			insertSoChild.setCouponDiscount(couponDiscountNew);
			insertSoChild.setStoreDiscount(storeDiscountNew);
			insertSoChild.setThirdpartySoChildId(childId);
			insertSoChild.setThirdpartyType(Integer.valueOf(3));

			insertSoChild.setChildCode(maxChildCode);
			insertSoChild.setDeliveryStatus(deliveryStatus);
			insertSoChild.setReceiverAddressId(sc.getReceiverAddressId());
			insertSoChild.setOrdinaryId(soChildId);
			insertSoChild.setPerformingParty(sc.getPerformingParty());
			insertSoChild.setPlatformId(sc.getPlatformId());
			insertSoChild.setSoId(sc.getSoId());
			insertSoChild.setPreSell(sc.getPreSell());
			insertSoChild.setDeliverEndTime(sc.getDeliverEndTime());
			insertSoChild.setRemark(sc.getRemark());
			insertSoChild.setInvoiceId(sc.getInvoiceId());
			insertSoChild.setThirdpartySoChildPayAmount(orderPayPrice);
			SoChildDTO updateSoChild = new SoChildDTO();
			updateSoChild.setAmount(sc.getAmount().subtract(amountNew));
			updateSoChild.setDeliveryFee(sc.getDeliveryFee().subtract(deliveryFeeNew));
			updateSoChild.setProductAmount(sc.getProductAmount().subtract(productAmountNew));
			updateSoChild.setCouponDiscount(sc.getCouponDiscount().subtract(couponDiscountNew));
			updateSoChild.setStoreDiscount(sc.getStoreDiscount().subtract(storeDiscountNew));
			updateSoChild.setId(soChildId);
			updateSoChild.setThirdpartySoChildPayAmount(sc.getThirdpartySoChildPayAmount().subtract(orderPayPrice));
			updateSoChild.setLastOperateTime(new Date());
			Long newSoChildId=soChildWriteService.openOrderWithTx(insertSoChild, insertItems, updateItems, updateSoChild);
			//拆单成功,为新的子订单生成快照,原始快照
			//电子卡券不生成快照和原始快照
			if (sc.getReceiverAddressId() != null) {
				ReceiverAddressDTO addressDTO = receiverAddressReadService.findReceiverAddressById(sc.getReceiverAddressId());
				this.createSnapsAddress(addressDTO, maxChildCode);
			}
			return newSoChildId;
		}else{
			SoChildDTO updateSoChild = new SoChildDTO();
			updateSoChild.setId(soChildId);
			updateSoChild.setThirdpartySoChildId(childId);
			updateSoChild.setThirdpartySoChildPayAmount(orderPayPrice);
			soChildWriteService.updateSoChildWithTx(updateSoChild);
			return soChildId;
		}



	}


	//创建快照和原始快照,addr参数仅有地址信息,type和childcode不需要
	private Map<String, Long> createSnapsAddress(ReceiverAddressDTO addr, String childCode) {
		//如果不是电子劵,创建快照
		addr.setSoChildCode(childCode);
		addr.setId(null);
		//拼装原始快照
		ReceiverAddressDTO originalSnapAddressDTO = new ReceiverAddressDTO();
		BeanUtils.copyProperties(addr, originalSnapAddressDTO);
		originalSnapAddressDTO.setType(4);
		//拼装快照
		ReceiverAddressDTO snapAddressDTO = new ReceiverAddressDTO();
		BeanUtils.copyProperties(addr, snapAddressDTO);
		snapAddressDTO.setType(3);
		//插入原始快照
		Long originalSnapAddressId = receiverAddressWriteService.insertReceiverAddressWithTx(originalSnapAddressDTO);
		//插入快照
		Long snapAddressId = receiverAddressWriteService.insertReceiverAddressWithTx(snapAddressDTO);
		Map<String, Long> map = new HashMap<>();
		map.put("originalSnapAddressId",originalSnapAddressId);
		map.put("snapAddressId",snapAddressId);
		return map;

	}


}
