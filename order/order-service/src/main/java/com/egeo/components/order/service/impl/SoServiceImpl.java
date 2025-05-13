package com.egeo.components.order.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.WorldBuyOrderManage;
import com.egeo.components.order.vo.*;
import com.egeo.components.order.vo.world.WorldPayReqVO;
import com.egeo.components.promotion.client.*;
import com.egeo.components.promotion.dto.*;
import com.egeo.components.promotion.vo.BuyCardRefundReqVO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.utils.*;
import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.service.read.*;
import com.egeo.components.order.service.write.QmOrderWriteService;
import com.egeo.utils.*;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.GoodsDetail;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.common.RefundErrorCodeConstant;
import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.business.impl.SoThirdpartyManageImpl;
import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.service.SoService;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.SoRefundWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.pay.client.JdOrderAwaitQueueClient;
import com.egeo.components.pay.client.PayWeixinLogClient;
import com.egeo.components.pay.client.ThirdpartyAwaitQueueClient;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.RecoverOrderStockBatchDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.InfoClient;
import com.egeo.components.user.client.InfoTemplateClient;
import com.egeo.components.user.client.InfoTemplateSendWayClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;
import com.egeo.components.utils.qc.QCInfos2;
import com.egeo.components.utils.qc.QCResult;
import com.egeo.components.utils.qc.QCUtil;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.encrypt.QEncodeUtil;
import com.egeo.utils.log.LogUtil;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhone;
import com.egeo.utils.thirdparty.RechargePhoneResult;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Service("soService")
public class SoServiceImpl implements SoService {

	XLogger logger = XLogger.getLogger(this.getClass().getName());

	@Autowired
	private AccountBatchClient abWrteService;

	@Autowired
	private UserAccountClient uaReadService;

	@Autowired
	private CompanyAccountClient caReadService;

	@Autowired
	private SaltClient saltReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private PayWeixinLogClient payWeixinLogReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient stockWriteService;

	@Autowired
	private ThirdpartyAwaitQueueClient thirdpartyAwaitQueueWriteService;

	@Autowired
	private SoWriteService soWriteService;

	@Autowired
	private SoReadService soReadService;

	@Autowired
	private SoRefundWriteService soRefundWriteService;

	@Autowired
	private SoRefundReadService soRefundReadService;

	@Autowired
	private SoChildReadService soChildReadService;

	@Autowired
	private SoThirdpartyWriteService soThirdpartyWriteService;

	@Autowired
	private SoThirdpartyReadService soThirdpartyReadService;

	@Autowired
	private SoItemReadService soItemReadService;

	@Autowired
	private UserClient userReadService;

	/*@Autowired(name = "sendInfoProvider")
	private Provider provider;*/

	@Autowired
	private InfoClient infoWriteService;
	@Autowired
	private InfoTemplateClient infoTemplateReadService;
	@Autowired
	private InfoTemplateSendWayClient infoTemplateSendWayReadService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockService;
	@Autowired
	private CompanyClient companyReadService;
	@Autowired
	private SkuClient skuReadService;
	@Autowired
	private CardSaltClient cardSaltWriteService;
	@Autowired
	private ECardClient eCardWriteService;
	@Autowired
	private ECardTempClient eCardTempWriteService;
	@Autowired
	private CouponUnitClient couponUnitReadService;
	@Autowired
	private ExchangeBatchClient exchangeBatchReadService;
	@Autowired
	private QmOrderReadService qmOrderReadService;
	@Autowired
	private QmOrderWriteService qmOrderWriteService;
	@Autowired
	private CouponBatchClient couponBatchReadService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordReadService;
	@Autowired
	private CouponUnitClient couponUnitWriteService;
	@Autowired
	private SendInfoClient sendInfoWriteService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordWriteService;
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
	@Autowired
	private JdOrderAwaitQueueClient jdOrderAwaitQueueWriteService;
	@Autowired
	AccountFlowClient accountFlowClient;
	@Autowired
	private JedisUtil jedisUtil;
	@Autowired
	private PayUtil payUtil;
	@Autowired
	private PayExtUtil payExtUtil;
	@Autowired
	private RechargePhoneUtil rechargePhoneUtil;
	@Autowired
	private QCUtil qCUtil;
	@Autowired
	private JdUtils jdUtils;

	@Autowired
	private JdUtils2 jdUtils2;
	@Autowired
	private QingMeiUtil qingMeiUtil;
	@Autowired
	private CompanyConfigClient companyConfig;
	@Autowired
	private CakeUtil cakeUtil;
	private static final String soRefundReason = "用户取消订单"; // 自动取消订单原因

	@Autowired
	private WorldBuyOrderManage worldBuyOrderManage;

	@Autowired
	private PushOrderManage pushOrderManage;

	@Resource
	private BuyCardClient buyCardClient;

	@Override
	public List<String> soRefundWithTx(SoDTO soDTO, String reason, Long operatorId, String soRefundCodeByFubi,
			String soRefundCodeByCash,String soRefundCodeByJiDian,String soRefundCodeByBuyCard, Boolean isCancel, String thirdRefundCode,HttpServletRequestDTO req) {
		logger.info("订单:{}进入实际退款流程",soDTO.getOrderCode());
		UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
		boolean isDlfUser = false;
		if(userDTO !=null && EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && userDTO.getChannelSource().equals(UserChannelSourceEnum.DLF.getChannelSource())){
			isDlfUser = true;
		}
		List<String> refundNos=new ArrayList<>();
		Long fubiAccountBatchId = null;
		Long cashAccountBatchId = null;
		Long jidianAccountBatchId = null;
		Long buyCardAccountBatchId = null;
		if (soDTO.getRefundFubi().doubleValue() > 0) {
			List<CompanyConfigDTO> configs = userReadService.findUserCompanyConfigs(soDTO.getUserId());
			Integer refundType = 0;
			for(CompanyConfigDTO config : configs) {
				if(config.getKey().equalsIgnoreCase("account.refund.type")) {
					refundType = Integer.valueOf(config.getValue());
				}
			}
			// 查询用户积分账户和盐
			UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
			if (uaFubi == null)
				throw new BusinessException("用户积分账户数据不存在");
			SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
			if (uaFubiSalt == null)
				throw new BusinessException("用户积分账户加密盐数据不存在");
			// 查询迩格积分收入账户和盐
			CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
			if (caFubi == null)
				throw new BusinessException("平台积分收入账户数据不存在");
			SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
			if (caFubiSalt == null)
				throw new BusinessException("平台积分收入账户加密盐数据不存在");
			// 资金流动  平台--> 个人积分
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
			outFlowAcc.setAccountId(caFubi.getId());
			outFlowAcc.setSalt(caFubiSalt.getSaltValue());
			outFlowAcc.setSum(soDTO.getRefundFubi());
			outFlowAccs.add(outFlowAcc);
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
			inFlowAcc.setAccountId(uaFubi.getId());
			inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
			inFlowAcc.setSum(soDTO.getRefundFubi());
			inFlowAccs.add(inFlowAcc);
			CashFlowResultDTO fubiFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
					soDTO.getPlatformId(), FlowTypeConstant.OR_FUBI.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
					operatorId, FinBatchConstant.ORDER_REFUND_FUBI, null, reason, false, 0));

			fubiAccountBatchId = fubiFlowResultDTO.getBatchId();
			//SendInfoWriteService sendInfoWriteService = new SendInfoWriteServiceImpl(userReadService,provider,infoWriteService, infoTemplateReadService, infoTemplateSendWayReadService);
			// 发送积分变更消息

			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
			Map<String,String> infoMap = new HashMap<String,String>();
			infoDto.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
			infoDto.setUserId(soDTO.getUserId());
			infoMap.put("changeFuBi", soDTO.getRefundFubi().toPlainString());
			infoMap.put("changeCause", reason);
			infoDto.setParams(infoMap);
			sendInfoWriteService.insertUserFuBiInfoAndSend(infoDto);

		}
		if (soDTO.getRefundCash().doubleValue() > 0) {
			// 查询用户现金账户和盐
			UserAccountDTO uaCash = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 3);
			if (uaCash == null)
				throw new BusinessException("用户现金账户数据不存在");
			SaltDTO uaCashSalt = saltReadService.querySaltByUUID(uaCash.getUuid());
			if (uaCashSalt == null)
				throw new BusinessException("用户现金账户加密盐数据不存在");
			// 查询迩格现金收入账户和盐
			CompanyAccountDTO caCash = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),2);
			if (caCash == null)
				throw new BusinessException("平台现金收入账户数据不存在");
			SaltDTO caCashSalt = saltReadService.querySaltByUUID(caCash.getUuid());
			if (caCashSalt == null)
				throw new BusinessException("平台现金入账户加密盐数据不存在");
			// 资金流动 平台现金-- >个人现金
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
			outFlowAcc.setAccountId(caCash.getId());
			outFlowAcc.setSalt(caCashSalt.getSaltValue());
			outFlowAcc.setSum(soDTO.getRefundCash());
			outFlowAccs.add(outFlowAcc);
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
			inFlowAcc.setAccountId(uaCash.getId());
			inFlowAcc.setSalt(uaCashSalt.getSaltValue());
			inFlowAccs.add(inFlowAcc);
			CashFlowResultDTO cashFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
					soDTO.getPlatformId(), FlowTypeConstant.OR_CASH.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
					operatorId, FinBatchConstant.ORDER_REFUND_CASH, null, reason, false, 0));

			cashAccountBatchId = cashFlowResultDTO.getBatchId();
		}
		if (soDTO.getRefundJidian() !=null && soDTO.getRefundJidian().doubleValue() > 0) {
			List<CompanyConfigDTO> configs = userReadService.findUserCompanyConfigs(soDTO.getUserId());
			Integer refundType = 5;
			// 查询用户积分账户和盐
			UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
			if (uaFubi == null){
				throw new com.egeo.exception.BusinessException("用户积分账户数据不存在");
			}

			SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
			if (uaFubiSalt == null){
				throw new com.egeo.exception.BusinessException("用户积分账户加密盐数据不存在");
			}

			// 查询迩格积分收入账户和盐
			CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
			if (caFubi == null){
				throw new com.egeo.exception.BusinessException("平台积分收入账户数据不存在");
			}

			SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
			if (caFubiSalt == null){
				throw new com.egeo.exception.BusinessException("平台积分收入账户加密盐数据不存在");
			}

			// 资金流动  平台--> 个人积分
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
			outFlowAcc.setAccountId(caFubi.getId());
			outFlowAcc.setSalt(caFubiSalt.getSaltValue());
			outFlowAcc.setSum(soDTO.getRefundJidian());
			outFlowAccs.add(outFlowAcc);
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
			inFlowAcc.setAccountId(uaFubi.getId());
			inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
			inFlowAcc.setSum(soDTO.getRefundJidian());
			inFlowAccs.add(inFlowAcc);
			CashFlowResultDTO jiDianFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
					soDTO.getPlatformId(), FlowTypeConstant.OR_CASH.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
					operatorId, FinBatchConstant.ORDER_REFUND_CASH, null, reason, false, 0));

			jidianAccountBatchId = jiDianFlowResultDTO.getBatchId();
		}
		if(soDTO.getRefundCard() !=null && soDTO.getRefundCard().doubleValue() >0){
			logger.info("订单:{}进入卡劵退款流水,操作金额:{}...",soDTO.getOrderCode(),soDTO.getRefundCard());
			Integer refundType = 6;
			// 查询用户积分账户和盐
			UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
			if (uaFubi == null){
				throw new com.egeo.exception.BusinessException("用户积点账户数据不存在");
			}

			SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
			if (uaFubiSalt == null){
				throw new com.egeo.exception.BusinessException("用户积点账户加密盐数据不存在");
			}

			// 查询迩格积分收入账户和盐
			CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
			if (caFubi == null){
				throw new com.egeo.exception.BusinessException("平台积点收入账户数据不存在");
			}

			SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
			if (caFubiSalt == null){
				throw new com.egeo.exception.BusinessException("平台积分收入账户加密盐数据不存在");
			}

			// 资金流动  平台--> 个人积分
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
			outFlowAcc.setAccountId(caFubi.getId());
			outFlowAcc.setSalt(caFubiSalt.getSaltValue());
			outFlowAcc.setSum(soDTO.getRefundCard());
			outFlowAccs.add(outFlowAcc);
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
			inFlowAcc.setAccountId(uaFubi.getId());
			inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
			inFlowAcc.setSum(soDTO.getRefundCard());
			inFlowAccs.add(inFlowAcc);
			CashFlowResultDTO fubiFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
					soDTO.getPlatformId(), FlowTypeConstant.OR_CGK.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
					operatorId, FinBatchConstant.ORDER_REFUND_CGK, null, reason, false, 0));

			buyCardAccountBatchId = fubiFlowResultDTO.getBatchId();
		}
		if (isCancel) {
			// 取消订单
			// 生成积分退款单(有积分支付)
			if (fubiAccountBatchId != null) {
				SoRefundDTO soRefundDTOByFubi = new SoRefundDTO();
				soRefundDTOByFubi.setSoRefundCode(soRefundCodeByFubi);
				soRefundDTOByFubi.setSoId(soDTO.getId());
				soRefundDTOByFubi.setCreatorId(operatorId);
				soRefundDTOByFubi.setUserId(soDTO.getUserId());
				soRefundDTOByFubi.setSoRefundReason(reason);
				soRefundDTOByFubi.setFubiAccountBatchId(fubiAccountBatchId);
				soRefundDTOByFubi.setSoRefundByFubi(soDTO.getRefundFubi());
				soRefundDTOByFubi.setPlatformId(soDTO.getPlatformId());
				soRefundDTOByFubi.setThirdRefundCode(thirdRefundCode);
				soRefundDTOByFubi.transferByVo(soDTO.getRefundVo());
				if(isDlfUser){
					soRefundDTOByFubi.setRefundState(0);
				}
				insertSoRefundAndLogWithTx(soRefundDTOByFubi,soDTO.getOrderCode(), req);
				refundNos.add(soRefundCodeByFubi);
			}
			// 生成现金退款单(有现金支付且现金退款成功)
			if (cashAccountBatchId != null) {
				SoRefundDTO soRefundDTOByCash = new SoRefundDTO();
				soRefundDTOByCash.setSoRefundCode(soRefundCodeByCash);
				soRefundDTOByCash.setSoId(soDTO.getId());
				soRefundDTOByCash.setCreatorId(operatorId);
				soRefundDTOByCash.setUserId(soDTO.getUserId());
				soRefundDTOByCash.setSoRefundReason(reason);
				soRefundDTOByCash.setCashAccountBatchId(cashAccountBatchId);
				soRefundDTOByCash.setSoRefundByCash(soDTO.getRefundCash());
				soRefundDTOByCash.setPlatformId(soDTO.getPlatformId());
				soRefundDTOByCash.setThirdRefundCode(thirdRefundCode);
				soRefundDTOByCash.transferByVo(soDTO.getRefundVo());
				insertSoRefundAndLogWithTx(soRefundDTOByCash,soDTO.getOrderCode(), req);
				refundNos.add(soRefundCodeByCash);
			}

			//生成积点退款单
			if (jidianAccountBatchId != null) {
				SoRefundDTO soRefundDTOByCash = new SoRefundDTO();
				soRefundDTOByCash.setSoRefundCode(soRefundCodeByJiDian);
				soRefundDTOByCash.setSoId(soDTO.getId());
				soRefundDTOByCash.setCreatorId(operatorId);
				soRefundDTOByCash.setUserId(soDTO.getUserId());
				soRefundDTOByCash.setSoRefundReason(reason);
				soRefundDTOByCash.setJidianAccountBatchId(jidianAccountBatchId);
				soRefundDTOByCash.setSoRefundByJidian(soDTO.getRefundJidian());
				soRefundDTOByCash.setPlatformId(soDTO.getPlatformId());
				soRefundDTOByCash.setThirdRefundCode(thirdRefundCode);
				soRefundDTOByCash.transferByVo(soDTO.getRefundVo());
				insertSoRefundAndLogWithTx(soRefundDTOByCash,soDTO.getOrderCode(), req);
				refundNos.add(soRefundCodeByJiDian);
			}
			//生成卡劵退款单
			if (buyCardAccountBatchId != null) {
				logger.info("订单:{}进入生成卡劵退款单",soDTO.getOrderCode());
				SoRefundDTO soRefundDTOByBuyCard = new SoRefundDTO();
				soRefundDTOByBuyCard.setSoRefundCode(soRefundCodeByBuyCard);
				soRefundDTOByBuyCard.setSoId(soDTO.getId());
				soRefundDTOByBuyCard.setCreatorId(operatorId);
				soRefundDTOByBuyCard.setUserId(soDTO.getUserId());
				soRefundDTOByBuyCard.setSoRefundReason(reason);
				soRefundDTOByBuyCard.setBuyCardAccountBatchId(buyCardAccountBatchId);
				soRefundDTOByBuyCard.setSoRefundByBuyCard(soDTO.getRefundCard());
				soRefundDTOByBuyCard.setPlatformId(soDTO.getPlatformId());
				soRefundDTOByBuyCard.setThirdRefundCode(thirdRefundCode);
				soRefundDTOByBuyCard.transferByVo(soDTO.getRefundVo());
				insertSoRefundAndLogWithTx(soRefundDTOByBuyCard,soDTO.getOrderCode(), req);
				refundNos.add(soRefundCodeByBuyCard);
			}
		} else {
			// 后台退款
			// 生成积分和现金退款单(有积分支付,有现金支付且现金退款成功)
			SoRefundDTO soRefundDTO = new SoRefundDTO();
			soRefundDTO.setSoRefundCode(soRefundCodeByFubi);
			soRefundDTO.setSoId(soDTO.getId());
			soRefundDTO.setCreatorId(operatorId);
			soRefundDTO.setUserId(soDTO.getUserId());
			soRefundDTO.setSoRefundReason(reason);
			soRefundDTO.setFubiAccountBatchId(fubiAccountBatchId != null ? fubiAccountBatchId : null);
			soRefundDTO.setSoRefundByFubi(fubiAccountBatchId != null ? soDTO.getRefundFubi() : null);
			soRefundDTO.setCashAccountBatchId(cashAccountBatchId != null ? cashAccountBatchId : null);
			soRefundDTO.setSoRefundByCash(cashAccountBatchId != null ? soDTO.getRefundCash() : null);
			soRefundDTO.setJidianAccountBatchId(jidianAccountBatchId != null ? jidianAccountBatchId : null);
			soRefundDTO.setSoRefundByJidian(jidianAccountBatchId != null ? soDTO.getRefundJidian() : null);
			soRefundDTO.setBuyCardAccountBatchId(buyCardAccountBatchId !=null?buyCardAccountBatchId:null);
			soRefundDTO.setSoRefundByBuyCard(buyCardAccountBatchId !=null?soDTO.getRefundCard():null);
			soRefundDTO.setThirdRefundCode(thirdRefundCode);
			soRefundDTO.transferByVo(soDTO.getRefundVo());
			insertSoRefundAndLogWithTx(soRefundDTO,soDTO.getOrderCode(), req);
			refundNos.add(soRefundCodeByFubi);
		}
		// 订单已退款金额变动和退款状态变动
		soWriteService.orderRefund(soDTO);
		accountFlowClient.orderRefund(soDTO.getOrderCode());
		//SendInfoWriteService sendInfoWriteService = new SendInfoWriteServiceImpl(userReadService,provider,infoWriteService, infoTemplateReadService, infoTemplateSendWayReadService);
		// 发送订单支付状态变更消息
		if (Objects.equals(2,soDTO.getOrderPayStatus())){
			InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
			vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
			vo1.setOrderCode(soDTO.getOrderCode());
			//已退款
			vo1.setOrderPayStatus(2);
			vo1.setUserId(soDTO.getUserId());
			sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);
		}

		return refundNos;
	}

	/**
	 * 新增退款单以及生成退款单日志
	 *
	 * @param dto
	 * @param req
	 */
	private void insertSoRefundAndLogWithTx(SoRefundDTO dto,String orderCode, HttpServletRequestDTO req) {
		saveBuyCardUseDetail(dto,orderCode);
		// 插入退款单
		Long soRefundId = soRefundWriteService.insertSoRefundWithTx(dto);

		// 记录生成退款单日志
		SoRefundDTO soRefundDTO = new SoRefundDTO();
		soRefundDTO.setId(soRefundId);
		SoRefundDTO soRefundDTO_ = soRefundReadService.findSoRefundById(soRefundDTO);
		EgeoLog log = new EgeoLog();
		log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
		log.setOperObject("SoSerivceImpl_soRefundWithTx");
		log.setMsgId(LogConstant.ORDER_REFUND_NEW.getStatus());
		log.setType(LogTypeConstant.SO_REFUND.getStatus());
		log.setOperatorObjId(soRefundDTO_.getId());
		log.setOperatorObjCode(soRefundDTO_.getSoRefundCode());
		log.setNewObj(soRefundDTO_);

		EgeoBusinessLogCommon.fillLogValueForBasic(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}
	}

	@Override
	public void cancelAndRefundOrderWithTx(String userName,SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
			String soRefundCodeByCash, HttpServletRequestDTO req) {
		cancelAndRefundOrderWithTx(userName,order,items,userId,soRefundCodeByFubi,soRefundCodeByCash,null,null,req);
	}

	@Override
	public boolean refundCash(Long userId,SoDTO order, BigDecimal refundCashAmount, List<SoItemDTO> items, String soRefundCodeByCash) {
		boolean isFullRefund = true;
		if (order.getCashPayType() != null && refundCashAmount.compareTo(BigDecimal.ZERO)>0) {
			isFullRefund = false;
			if (order.getCashPayType() == 1) {
				// 支付宝
				// 设置退款包含的商品列表信息
				List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
				for (SoItemDTO soItemDTO : items) {
					GoodsDetail goodsDetail = new GoodsDetail();
					goodsDetail.setGoodsId(soItemDTO.getPuId().toString());
					goodsDetail.setGoodsName(soItemDTO.getPuName());
					goodsDetail.setQuantity(soItemDTO.getPuCount().longValue());
					goodsDetail.setPrice(soItemDTO.getPrice().toString());
					goodsDetailList.add(goodsDetail);
				}
				// 发送支付宝退款请求
				String errorCode = payUtil.alipayRefundOrder(order.getOrderCode(), soRefundCodeByCash,
						refundCashAmount, userId, goodsDetailList);

				if (errorCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
					// 退款成功
					isFullRefund = true;
				} else {
					// 退款失败
					// 发送预警邮件,走后台退款流程
					payUtil.sendRefundWarnEmail(order.getOrderCode(), errorCode,
							RefundErrorCodeConstant.translate(errorCode), Integer.valueOf(2));
				}

			} else if (order.getCashPayType() == 2) {
				// 微信
				// 查询微信的支付日志,获取微信退款订单编号
				PayWeixinLogDTO payWeixinLogDTO = payWeixinLogReadService
						.queryPayWeixinLogByOrderCode(order.getOrderCode());

				if (payWeixinLogDTO == null)
					throw new BusinessException("该订单没有微信支付日志,无法自动退款");

				Integer signPlatform = order.getSignPlatform();
//				if (payUtil.getProperty("wx.app.id").equals(payWeixinLogDTO.getAppid())) {
//					signPlatform = Integer.valueOf(3);
//				} else if (payUtil.getProperty("wx.app.id.native").equals(payWeixinLogDTO.getAppid())) {
//					signPlatform = Integer.valueOf(1);
//				}
				if (Objects.isNull(signPlatform)){
					signPlatform = 3;
				}

				// 设置请求参数
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("out_trade_no", payWeixinLogDTO.getOutTradeNo());
				paraMap.put("transaction_id", payWeixinLogDTO.getTransactionId());
				paraMap.put("out_refund_no", soRefundCodeByCash);
				// 订单金额,单位为分,int类型
				paraMap.put("total_fee", payUtil.priceDecimal2IntString(order.getOrderPaidByCash()));
				// 退款金额,单位为分,int类型
				paraMap.put("refund_fee", payUtil.priceDecimal2IntString(refundCashAmount));
				String url = payUtil.getProperty("wx.refund.url");

				// 发送微信退款请求
				Map<String, String> result = payUtil.sendWeixinPostRequest(paraMap, url, signPlatform, true,order.getPlatformId());
				String returnCode = result.get("return_code"); // 通信标识:SUCCESS/FAIL
				String returnMsg = result.get("return_msg"); // 返回信息
				String resultCode = result.get("result_code"); // 退款申请接收成功与否:SUCCESS/FAIL
				String errCode = result.get("err_code"); // 错误代码
				String errCodeDes = result.get("err_code_des"); // 错误代码描述

				if (returnCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
					if (resultCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
						// 退款成功
						isFullRefund = true;
					} else {
                        logger.error("微信退款失败,orderCode:{},errCode:{},errCodeDes:{},type:{}"
								,order.getOrderCode(),errCode,errCodeDes,2);
						// 退款失败
						// 发送预警邮件,走后台退款流程
						payUtil.sendRefundWarnEmail(order.getOrderCode(), errCode, errCodeDes, Integer.valueOf(2));
					}
				} else {
					// 退款失败
					// 发送预警邮件,走后台退款流程
					String errDes=EmptyUtil.isNotEmpty(returnMsg) ? returnMsg : RefundErrorCodeConstant.translate(returnCode);
					logger.error("微信退款失败,orderCode:{},returnCode:{},returnMsg:{},type:{}"
							,order.getOrderCode(),returnCode,errDes,2);
					payUtil.sendRefundWarnEmail(order.getOrderCode(), returnCode, errDes, Integer.valueOf(2));
				}

			}
		}
		return isFullRefund;
	}

	/*
校验是否为正式公司
 */
	private Boolean checkCompany(Long companyId){
		CompanyDTO companyById = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyById)){
			throw new BusinessException(companyById+"公司不存在");
		}
		if(companyById.getCompanyType()==0){
			return true;
		}else{
			return false;
		}
	}
	private void checkAccountBeforeRefund(SoDTO soDTO) {
		Integer fubiError = Integer.valueOf(0);
		Integer cashError = Integer.valueOf(1);

		// 查询用户信息
		UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
		if (userDTO == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), null, null, fubiError);
			// throw new BusinessException("用户不存在");
			throw new BusinessException("订单取消失败!");
		}

		// 查询用户积分账户和盐
		UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 0);
		if (uaFubi == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, fubiError);
			// throw new BusinessException("用户积分账户数据不存在");
			throw new BusinessException("订单取消失败!");
		}
		SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
		if (uaFubiSalt == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, fubiError);
			// throw new BusinessException("用户积分账户加密盐数据不存在");
			throw new BusinessException("订单取消失败!");
		}

		// 查询迩格积分收入账户和盐
		CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
		if (caFubi == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), "迩格积分收入账户", null, fubiError);
			// throw new BusinessException("迩格积分收入账户数据不存在");
			throw new BusinessException("订单取消失败!");
		}
		SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
		if (caFubiSalt == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), caFubi.getName(), null, fubiError);
			// throw new BusinessException("迩格积分收入账户加密盐数据不存在");
			throw new BusinessException("订单取消失败!");
		}

		// 查询用户现金账户和盐
		UserAccountDTO uaCash = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 3);
		if (uaCash == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, cashError);
			// throw new BusinessException("用户现金账户数据不存在");
			throw new BusinessException("订单取消失败!");
		}
		SaltDTO uaCashSalt = saltReadService.querySaltByUUID(uaCash.getUuid());
		if (uaCashSalt == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, cashError);
			// throw new BusinessException("用户现金账户加密盐数据不存在");
			throw new BusinessException("订单取消失败!");
		}

		// 查询迩格现金收入账户和盐
		CompanyAccountDTO caCash = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),2);
		if (caCash == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), "迩格现金收入账户", null, cashError);
			// throw new BusinessException("迩格现金收入账户数据不存在");
			throw new BusinessException("订单取消失败!");
		}
		SaltDTO caCashSalt = saltReadService.querySaltByUUID(caCash.getUuid());
		if (caCashSalt == null) {
			payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), caCash.getName(), null, cashError);
			// throw new BusinessException("迩格现金入账户加密盐数据不存在");
			throw new BusinessException("订单取消失败!");
		}
	}

	@Override
	public Boolean dealThirdpartyOrderWithTx(String userName,Long userId,Long orderId, String orderCode) {
		boolean isSuccessDealThirdpartyOrder = true;
		List<SoChildDTO> soChildDTOList = soChildReadService.querySoChildListBySoId(orderId);
		boolean isQmOrder=false;
		for (SoChildDTO soChildDTO : soChildDTOList) {
			if (soChildDTO.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE)) {
				logger.info("[话费充值]开始:orderId="+orderId);
				// 第三方订单类型为,话费充值
				//话费充值一个子订单对应一个soitem
				SoItemDTO soItemDTO = new SoItemDTO();
				soItemDTO.setSoChildId(soChildDTO.getId());
				List<SoItemDTO> all = soItemReadService.findAll(soItemDTO);
				BigDecimal amount=all.get(0).getPrice();
				// 1)请求第三方支付话费
				SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
				soThirdpartyDTO.setSoChildId(soChildDTO.getId());
				List<SoThirdpartyDTO> soThirdpartyDTOList = soThirdpartyReadService
						.findSoThirdpartyAll(soThirdpartyDTO);
				soThirdpartyDTO.setId(soThirdpartyDTOList.get(0).getId());
				RechargePhone rechargePhone = null;
				try {
					logger.info("[话费充值]请求参数:"+amount);
					rechargePhone = rechargePhoneUtil.telRecharge(soThirdpartyDTOList.get(0).getPhone(),
							amount.intValue(), soChildDTO.getChildCode());
					logger.info("[话费充值]返回结果:"+rechargePhone.getErrorCode());
					logger.info("[话费充值]返回结果:"+rechargePhone.getReason());
					logger.info("[话费充值]返回结果:"+rechargePhone.getResult());
				} catch (Exception e) {
					rechargePhoneUtil.logger.error("子订单id为: " + soChildDTO.getId() + ",话费充值失败");
					rechargePhoneUtil.logger.error("子订单id为: " + soChildDTO.getId() + ",话费充值失败,充值号码:"
							+ soThirdpartyDTOList.get(0).getPhone() + ",充值金额:" + amount.intValue());
					e.printStackTrace();
				}
				// 2)设置第三方订单的相关信息
				// -------------错误测试---------------
				// RechargePhone rechargePhone = new RechargePhone();
				// rechargePhone.setErrorCode(RechargePhoneErrorCode.SYSTEM_EXCEPTION.getCode());
				// rechargePhone.setReason(RechargePhoneErrorCode.SYSTEM_EXCEPTION.getMsg());
				// -------------错误测试---------------

				if (rechargePhone.getErrorCode().equals(RechargePhoneErrorCode.REQUEST_SUCCESS.getCode())) {
					// 充值成功
					// 1)更新第三方子订单
					RechargePhoneResult rechargePhoneResult = rechargePhone.getResult();
					// 充值状态:0充值中 1成功 9撤销，刚提交都返回0
					soThirdpartyDTO.setThirdpartyStatus(Integer.parseInt(rechargePhoneResult.getGameState()));
					soThirdpartyDTO.setThirdpartyId(rechargePhoneResult.getSporderId());
					soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);
				} else {
					// 充值失败
					if (rechargePhone.getErrorCode().equals(RechargePhoneErrorCode.INSUFFICIENT_BALANCE.getCode())) {
						// 1.当前账户可用余额不足
						// 1)更新第三方子订单,充值失败
						soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(2));
						soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);

						//2)订单退款
						CancelSo(orderId,orderCode,userName,userId);

						// 3)充值失败原因处理
						rechargePhoneUtil.sendSysExEmail(rechargePhone, orderCode, amount.intValue());

						isSuccessDealThirdpartyOrder = false;

					} else if (rechargePhone.getErrorCode().equals(RechargePhoneErrorCode.SYSTEM_EXCEPTION.getCode())
							|| rechargePhone.getErrorCode()
									.equals(RechargePhoneErrorCode.SYSTEM_EXCEPTION_OLD.getCode())) {
						// 2.第三方系统内部异常
						// 1)更新第三方子订单,充值中
						soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(0));
						soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);

						// 2)充值失败原因处理
						rechargePhoneUtil.sendSysExEmail(rechargePhone, orderCode, amount.intValue());
					} else {
						logger.error("话费充值未知错误,订单号: " + orderCode);
						throw new BusinessException("话费充值未知错误");
					}
				}

			}else if(soChildDTO.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC)){
				//第三方订单类型,券仓
				//1.请求第三方购买券仓卡券
				//根据子订单id查询对应的商品
				Long puId=soItemReadService.findPuIdBySoChildId(soChildDTO.getId());
				if(EmptyUtil.isEmpty(puId)){
					logger.info("第三方订单[券仓]:子订单id未查询到puid");
					throw new BusinessException("子订单有误");

				}
				SkuDTO skuDTO=skuReadService.findSkuByPuId(puId);
				if(EmptyUtil.isEmpty(skuDTO)){
					logger.info("第三方订单[券仓]:puid未查询到sku");
					throw new BusinessException("sku有误");
				}
				String result="";
				Long sum=soItemReadService.findSoChildPUNum(soChildDTO.getId());//查询子订单的购物数量
				//将订单加入到等待队列中
				ThirdpartyAwaitQueueDTO awaitQueueDTO = new ThirdpartyAwaitQueueDTO();
				awaitQueueDTO.setSoId(soChildDTO.getId());//子订单id
				String randomNumber = StringUtils.getRandomByCount(3);
				awaitQueueDTO.setRandomNumber(randomNumber);
				awaitQueueDTO.setOrderCode(soChildDTO.getChildCode());
				awaitQueueDTO.setThirdpartyType(soChildDTO.getThirdpartyType());
				thirdpartyAwaitQueueWriteService.insertThirdpartyAwaitQueueWithTx(awaitQueueDTO);
				//2.准备更新第三方订单信息的公共信息
				SoDTO soById = soReadService.findSoById(orderId);
				SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
				soThirdpartyDTO.setSoChildId(soChildDTO.getId());
				List<SoThirdpartyDTO> soThirdpartyDTOList = soThirdpartyReadService
						.findSoThirdpartyAll(soThirdpartyDTO);
				soThirdpartyDTO.setId(soThirdpartyDTOList.get(0).getId());
				try {
					//调用第三方对接接口
					logger.info("第三方接口的调用入口,参数:外部skuid:"+skuDTO.getExternalSkuId());
					logger.info("第三方接口的调用入口,参数:数量:"+sum.intValue());
					logger.info("第三方接口的调用入口,参数:子订单id:"+soChildDTO.getChildCode());
					result = qCUtil.buyCard(skuDTO.getExternalSkuId(), sum.intValue(), soChildDTO.getChildCode());
					logger.info("第三方接口调用出口,结果:"+result);
				}catch (Exception e){
					logger.error("券仓购买卡券接口异常,message:"+e.getMessage());
					return true;
				}
				//解析结果
				QCResult qcResult = JsonUtils.jsonToPojo(result, QCResult.class);
				//交易完成,删除等待队列
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
						for (QCInfos2 qc : infos2) {
							ECardDTO eCardDTO = new ECardDTO();
							//卡密(先保存到临时表)
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
							eCardTempDTO.setUserId(userId);
							eCardTempDTO.setOrderCode(orderCode);
							Long aLong = eCardTempWriteService.insertECardTempWithTx(eCardTempDTO);
							logger.info("插入草稿成功");

							eCardDTO.setCardNumber(qc.getBarcode());
							eCardDTO.setCreateUserid(userId);

							eCardDTO.setSkuId(skuDTO.getId());
							eCardDTO.setSkuName(skuDTO.getSkuName());
							eCardDTO.setSkuSerialNumber(skuDTO.getSkuSerialNumber());
							eCardDTO.setType(Integer.valueOf(5));//类型5为券仓卡券,1为京东e卡
							eCardDTO.setIsAllocation(Integer.valueOf(1));//1:已分配,0:未分配
							eCardDTO.setIsValid(Integer.valueOf(1));//是否有效,1 有效
							eCardDTO.setUserId(userId);
							eCardDTO.setAllocationTime(new Date());
							eCardDTO.setOrderCode(orderCode);
							eCardDTO.setStartTime(new Date());
							eCardDTO.setShortUrl(qc.getShorturl());
							if(EmptyUtil.isNotEmpty(qc.getBarpwd())){
								String cardThick = qc.getBarpwd();
								String salt = SaltUtils.getRandomSalt();
								String encrypt = null;
								encrypt = QEncodeUtil.aesEncrypt(cardThick, salt);
								qc.setBarpwd(encrypt);
								CardSaltDTO cardSaltDTO = new CardSaltDTO();
								String uuid = UUIDBuild.getUUID();
								cardSaltDTO.setUuid(uuid);
								eCardDTO.setUuid(uuid);
								cardSaltDTO.setSaltValue(salt);
								qc.setUuid(uuid);
								cardSaltWriteService.insertCardSaltWithTx(cardSaltDTO);
								logger.info("插入盐成功");
							}
							eCardDTO.setCardThick(qc.getBarpwd());
							eCardDTO.setPlatformId(soById.getPlatformId());
							logger.info("卡密"+qc.getBarpwd());
							//插入卡盐
							eCardWriteService.insertECardWithTx(eCardDTO);
							logger.info("插入卡券成功");
							//操作成功,删除临时表
							ECardTempDTO tempDTO = new ECardTempDTO();
							tempDTO.setId(aLong);
							eCardTempWriteService.deleteECardTempWithTx(tempDTO);
							logger.info("删除草稿成功");
						}
					}catch (Exception e){
						logger.info("购买结果"+result);
						qCUtil.sendSysEmail(orderCode,result);
						logger.error("盐加密,保存失败,联系管理员,message:" );
						logger.error(e.getMessage(),e );
					}



					//2.2更新第三方订单(将第三方订单状态修改成1,成功,记录第三方订单id),并更新订单状态为已完成
					soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(1));
					soThirdpartyDTO.setThirdpartyId(qcResult.getBody().getTranid());
					SoDTO soDTO = new SoDTO();
					soDTO.setId(orderId);
					soDTO.setOrderStatus(Integer.valueOf(4));
					soThirdpartyWriteService.updateSoThirdpartyAndSoWithTx(soThirdpartyDTO);
					soWriteService.changeOrderStatusByOrderId(orderCode, orderId, OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(), 3, null, null);


					//记录订单完成日志
					recordConfirmOrderLog(soById, "SoFacade_confirmRechargeResult");




				}else{
					//交易失败
					isSuccessDealThirdpartyOrder = false;
					if(qcResult.getHeader().getRespcode().equals("RMP0021")||qcResult.getHeader().getRespcode().equals("RMP0044")){
						//需要预警处理(发送邮件)
						qCUtil.sendSysExEmail(qcResult,orderCode,sum.intValue());
					}
					//1)跟新第三方订单状态为失败
					soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(2));
					soThirdpartyWriteService.updateSoThirdpartyWithTx(soThirdpartyDTO);
					//2)订单退款,变更状态
					CancelSo(orderId,orderCode,userName,userId);
					//3)记录失败原因
					logger.error("[券仓卡券购买]购买失败,原因:code:"+qcResult.getHeader().getRespcode()+";message:"+qcResult.getHeader().getRespmsg());

				}
			}else if(soChildDTO.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD)){
				UserDTO userDTO = userReadService.findUserByID(userId);
				if (userDTO == null) {
					throw new BusinessException("当前用户不存在:"+userId);
				}
				//根据公司配置是否允许京东下单(true-允许,false-禁止);
				boolean jdOrderAllow = jdOrderAllow(userDTO.getCompanyId());
				if((SpringContextTool.isPrd() && jdOrderAllow) || jdUtils2.TEST_TO_PRO.equals("1")) {
					//京东子订单,需要进行预占库存
					Long jdOrderId = soChildDTO.getThirdpartySoChildId();
					//String token = jdUtils.getAccessToken(jedisUtil);
					String token = jdUtils2.getAccessToken(jedisUtil);
					String result = jdUtils.confirmOrder(token, jdOrderId.toString());
					logger.info("京东请求结果:"+result);
					if(EmptyUtil.isNotEmpty(result)){
						JSONObject resultObj = JSONObject.parseObject(result);
						if(resultObj.getString("resultCode").equals("3103")||resultObj.getString("result").equals("true")){
							//成功,下单
							try {
								SoDTO oldSoDtO = soReadService.findSoById(orderId);
								if(oldSoDtO !=null && !Objects.equals(oldSoDtO.getOrderConfirmStatus(),OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus())){
									SoDTO soDTO = new SoDTO();
									soDTO.setId(orderId);
									soDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
									soWriteService.updateOrderWithTX(soDTO);
								}
							}catch (Exception e){
								logger.info("更新京东的订单code:{}确认时失败:{}",soChildDTO.getChildCode(),e);
							}
							// 第三方订单状态 1下单成功 9撤销，刚提交都返回0
							SoThirdpartyDTO thirdpartyDTO = new SoThirdpartyDTO();
							thirdpartyDTO.setThirdpartyStatus(1);
							thirdpartyDTO.setThirdpartyId(jdOrderId+"");
							soThirdpartyWriteService.updateSoThirdpartyByThirdIdWithTx(thirdpartyDTO);

							logger.info("京东预占库存成功");
						}else if(resultObj.getString("result").equals("false")){
							//预占失败
							isSuccessDealThirdpartyOrder = false;
							CancelSo(orderId,orderCode,userName,userId);
							//3)记录失败原因
							logger.error("[京东商品预占库存失败],原因:code:"+resultObj.getString("resultMessage"));
							if(resultObj.getString("resultCode").equals("3017")){
								//发送余额不足预警
								jdUtils.sendErrEmail(resultObj.getString("resultMessage"),orderId,soChildDTO.getThirdpartySoChildId());
							}
						}
					}else{
						//预占库存出错,5分钟重试
						logger.info("预占库存出错,5分钟重试");
						JdOrderAwaitQueueDTO jdOrderAwaitQueueDTO = new JdOrderAwaitQueueDTO();
						jdOrderAwaitQueueDTO.setSoChildId(soChildDTO.getId());
						jdOrderAwaitQueueDTO.setSoId(orderId);
						jdOrderAwaitQueueDTO.setJdOrderId(soChildDTO.getThirdpartySoChildId());
						jdOrderAwaitQueueWriteService.insertJdOrderAwaitQueueWithTx(jdOrderAwaitQueueDTO);

					}
				}else {

					//京东子订单,需要进行预占库存
					Long jdOrderId = soChildDTO.getThirdpartySoChildId();
					//成功,下单
					SoDTO soDTO = new SoDTO();
					soDTO.setId(orderId);
					soDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
					soWriteService.updateOrderWithTX(soDTO);
					// 第三方订单状态 1下单成功 9撤销，刚提交都返回0
					SoThirdpartyDTO thirdpartyDTO = new SoThirdpartyDTO();
					thirdpartyDTO.setThirdpartyStatus(1);
					thirdpartyDTO.setThirdpartyId(jdOrderId+"");
					soThirdpartyWriteService.updateSoThirdpartyByThirdIdWithTx(thirdpartyDTO);

					logger.info("京东预占库存成功");

				}

			}else if(soChildDTO.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_CAKE)){
				SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
				soThirdpartyDTO.setSoChildId(soChildDTO.getId());
				List<SoThirdpartyDTO> soThirdpartyDTOList = soThirdpartyReadService
						.findSoThirdpartyAll(soThirdpartyDTO);
				SoThirdpartyDTO soThirdpartyDTO1 = soThirdpartyDTOList.get(0);
				soThirdpartyDTO.setId(soThirdpartyDTO1.getId());

				String resultStatus = "2";
				String orderSn = EmptyUtil.isNotEmpty(soThirdpartyDTO1.getThirdpartyId())?soThirdpartyDTO1.getThirdpartyId():soChildDTO.getThirdpartySoChildId()+"";
				String outOrderSn =EmptyUtil.isNotEmpty(soThirdpartyDTO1.getSoChildCode())?soThirdpartyDTO1.getSoChildCode():soChildDTO.getChildCode();
				String orderPrice =EmptyUtil.isNotEmpty(soThirdpartyDTO1.getThirdpartyPayAmount())?String.valueOf(soThirdpartyDTO1.getThirdpartyPayAmount()):String.valueOf(soChildDTO.getThirdpartySoChildPayAmount());
				String transactionSn =EmptyUtil.isNotEmpty(soThirdpartyDTO1.getSoChildCode())?soThirdpartyDTO1.getSoChildCode():soChildDTO.getChildCode();

				JSONObject jsonObject = cakeUtil.orderPayResult(resultStatus,orderSn,outOrderSn,orderPrice,transactionSn);
				logger.info("蛋糕叔叔母订单{},子订单:{}支付回调接口请求结果:{}",orderId,soChildDTO.getChildCode(),jsonObject !=null?JSON.toJSONString(jsonObject):"返回结果为空了");
				if(jsonObject.containsKey("code") && "200".equals(jsonObject.getString("code"))){
					//成功,下单
					try {
						SoDTO oldSoDtO = soReadService.findSoById(orderId);
						if(oldSoDtO !=null && oldSoDtO.getOrderConfirmStatus() != OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus()){
							SoDTO soDTO = new SoDTO();
							soDTO.setId(orderId);
							soDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
							soWriteService.updateOrderWithTX(soDTO);
						}
					}catch (Exception e){
						logger.info("更新蛋糕叔叔的订单code:{}确认时失败:{}",soChildDTO.getChildCode(),e);
					}

					try {
						// 第三方订单状态 1下单成功 9撤销，刚提交都返回0
						SoThirdpartyDTO thirdpartyDTO = new SoThirdpartyDTO();
						thirdpartyDTO.setThirdpartyId(orderSn);
						thirdpartyDTO.setThirdpartyStatus(1);
						soThirdpartyWriteService.updateSoThirdpartyByThirdIdWithTx(thirdpartyDTO);
					}catch (Exception e){
						logger.info("更新蛋糕叔叔的子订单code:{}三方状态失败:{}",soChildDTO.getChildCode(),e);
					}
					logger.info("蛋糕叔叔订单id{},子订单{}支付回调接口请求成功",orderId,soChildDTO.getChildCode());
				}else{
					isSuccessDealThirdpartyOrder = false;
					CancelSo(orderId,orderCode,userName,userId);
					//3)记录失败原因
					logger.error("[蛋糕叔叔商品支付失败],原因:code:{}"+jsonObject.getString("msg"));
					/*if(resultObj.getString("resultCode").equals("3017")){
						//发送余额不足预警
						jdUtils.sendErrEmail(resultObj.getString("resultMessage"),orderId,soChildDTO.getThirdpartySoChildId());
					}*/
				}

			}else if(soChildDTO.getThirdpartyType().equals(ThirdConst.ThirdPartyType.WORLD)){
				String worldOrderId = soChildDTO.getThirdpartySoChildId()+"";

				SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
				soThirdpartyDTO.setSoChildId(soChildDTO.getId());
				List<SoThirdpartyDTO> soThirdpartyDTOList = soThirdpartyReadService
						.findSoThirdpartyAll(soThirdpartyDTO);
				SoThirdpartyDTO soThirdpartyDTO1 = soThirdpartyDTOList.get(0);
				soThirdpartyDTO.setId(soThirdpartyDTO1.getId());
				String raBatch = SequenceUtil.genRaBatchNo();
				WorldPayReqVO worldPaySuccessReqVO = new WorldPayReqVO();
				worldPaySuccessReqVO.setOrderSn(soChildDTO.getChildCode());
				worldPaySuccessReqVO.setPayWay("0");
				worldPaySuccessReqVO.setPayTime(String.valueOf(System.currentTimeMillis()));
				worldPaySuccessReqVO.setPaySerial(raBatch);
				worldPaySuccessReqVO.setPayDecarleRequest("否");
				worldPaySuccessReqVO.setPayDecarleResponse("否");
				JSONObject jsonObject = worldBuyOrderManage.orderPay(worldPaySuccessReqVO);
				logger.info("母订单id:{}全球购支付通知结果:{}",orderId,jsonObject !=null ?JSON.toJSONString(jsonObject):"返回结果为空了");
				if(jsonObject !=null && jsonObject.containsKey("code") && "200".equals(jsonObject.getString("code"))){
					try {
						String worldOrderNo = getWorldOrderId(jsonObject);
						logger.info("子订单:{}获取到的worldOrderNo={}",soChildDTO.getChildCode(),worldOrderNo);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("子订单:{}获取到的worldOrderNo失败，发生异常:{}",soChildDTO.getChildCode(),e);
					}

					//成功,下单
					try {
						SoDTO oldSoDtO = soReadService.findSoById(orderId);
						if(oldSoDtO !=null && !Objects.equals(oldSoDtO.getOrderConfirmStatus(),OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus())){
							SoDTO soDTO = new SoDTO();
							soDTO.setId(orderId);
							soDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
							soWriteService.updateOrderWithTX(soDTO);
						}
					}catch (Exception e){
						logger.info("更新全球购的订单code:{}确认时失败:{}",soChildDTO.getChildCode(),e);
					}
					try {
						// 第三方订单状态 1下单成功 9撤销，刚提交都返回0
						SoThirdpartyDTO thirdpartyDTO = new SoThirdpartyDTO();
						thirdpartyDTO.setThirdpartyStatus(1);
						thirdpartyDTO.setThirdpartyId(worldOrderId);
						soThirdpartyWriteService.updateSoThirdpartyByThirdIdWithTx(thirdpartyDTO);
					}catch (Exception e){
						logger.info("更新全球购的子订单code:{}三方状态失败:{}",soChildDTO.getChildCode(),e);
					}

					logger.info("全球购订单:{}支付回调接口请求成功",soChildDTO.getChildCode());
				}else{
					isSuccessDealThirdpartyOrder = false;
					CancelSo(orderId,orderCode,userName,userId);
					//3)记录失败原因
					logger.error("[全球购商品支付失败],原因:code:{}"+jsonObject !=null?jsonObject.getString("msg"):"请求全球购响应为空");
				}
			}else if(soChildDTO.getThirdpartyType().equals(ThirdConst.ThirdPartyType.QM)){
				isQmOrder=true;
			}
		}
		if (isQmOrder){
			SoDTO soDTO=soReadService.findSoById(orderId);
			QmOrderDTO qmOrderDTO=qmOrderReadService.findBySoId(orderId);
			boolean expire=qmOrderDTO.getExpireTime().before(new Date());
			if (expire){
				logger.error("清美订单支付超时,取消;外部交易单号outTradeNo:{};orderCode{}:",qmOrderDTO.getOutTradeNo(),qmOrderDTO.getOrderCode());
				return false;
			}
			String success=qingMeiUtil.paySuccessNotify(qmOrderDTO.getNotifyUrl(),qmOrderDTO.getOutTradeNo(),
					qmOrderDTO.getOrderCode(),String.valueOf(userId),soDTO.getOrderAmountPay(),qmOrderDTO.getRemark(),
					DateUtils.formatDateTime(soDTO.getOrderPaymentConfirmDate()));
			boolean payResult= !Objects.isNull(success) || success.contains("SUCCESS");
			QmOrderDTO updateQmDTO=new QmOrderDTO();
			updateQmDTO.setId(qmOrderDTO.getId());
			updateQmDTO.setOrderPayTime(soDTO.getOrderPaymentConfirmDate());
            updateQmDTO.setSyncTime(new Date());
            if (payResult){
				SoDTO updateSoDTO = new SoDTO();
				updateSoDTO.setId(orderId);
				updateSoDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
				soWriteService.updateOrderWithTX(updateSoDTO);
				updateQmDTO.setSyncPayStatus(QmOrderDTO.SyncPayStatus.SYNCED);
			}else {
				updateQmDTO.setSyncPayStatus(QmOrderDTO.SyncPayStatus.WAIT_SYNC);
				boolean moreThanMaxCount=qmOrderDTO.getSyncPayCount() > qingMeiUtil.syncPayResultMaxCount;
				if (moreThanMaxCount && qingMeiUtil.syncPayMaxCancelOrder){
					CancelSo(orderId,orderCode,userName,userId);
					//3)记录失败原因
					logger.error("[支付结果同步清美超过最大同步次数,取消订单],外部交易单号outTradeNo:{};orderCode:",
							qmOrderDTO.getOutTradeNo(),qmOrderDTO.getOrderCode());
				}
			}
			qmOrderWriteService.updateOrder(updateQmDTO);
		}
		pushOrderManage.pushOrderInfo(orderId,null,null);
		return isSuccessDealThirdpartyOrder;
	}

	private String getWorldOrderId(JSONObject jsonObject) {
		String worldOrderId =null;
		try {
			//老接口的返回
			JSONArray dataJsonArr =  jsonObject.getJSONArray("data");
			logger.info("全球购旧接口返回");
			if(dataJsonArr !=null && dataJsonArr.size() >0){
				JSONObject dataJson = dataJsonArr.getJSONObject(0);
				if(dataJson.containsKey("code") && dataJson.containsKey("data")){
					JSONObject sDataJson = dataJson.getJSONObject("data");
					JSONObject orderItemJson = sDataJson.getJSONObject("orderItem");
					worldOrderId = orderItemJson.getString("orderSn");
				}
			}
		}catch (Exception e){
			//新接口的返回
			logger.info("全球购新接口返回");
			JSONObject dataJson = jsonObject.getJSONObject("data");
			JSONObject orderItemJson = dataJson.getJSONObject("orderItem");
			if(orderItemJson.containsKey("orderNo")){
				worldOrderId = orderItemJson.getString("orderNo");
			}else if(orderItemJson.containsKey("orderSn")){
				worldOrderId = orderItemJson.getString("orderSn");
			}
		}
		return worldOrderId;
	}

	//进行以旧换新
	@Override
	public boolean exchangeOrderWithTx(Long orderId, String orderCode, String name, Long userId) {
		boolean isSuccessExchange=true;
		SoDTO soDTO = soReadService.findSoById(orderId);
		List<ExchangeOrderRecordDTO> recordDTOList=exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(orderCode);
		if(EmptyUtil.isEmpty(recordDTOList)){
			logger.info("[支付回调进行以旧换新]该订单不存在以旧换新记录,orderCode="+orderCode);
			isSuccessExchange = false;
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;

		}else if(recordDTOList.size()>1){
			logger.info("[支付回调进行以旧换新]以旧换新记录数据出错,该订单code对应多条记录,orderCode="+orderCode);
			isSuccessExchange = false;
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;

		}
		List<CouponUnitDTO> couponUnitDTOList=couponUnitReadService.findCouponUnitAllByCouponUnitCode(recordDTOList.get(0).getOldUnitCode());
		if(EmptyUtil.isEmpty(couponUnitDTOList)){
			logger.info("[支付回调进行以旧换新]旧优惠券code不存在,couponUnitCode="+recordDTOList.get(0).getOldUnitCode());
			CancelSo(orderId,orderCode,name,userId);
			isSuccessExchange = false;
		}else if(couponUnitDTOList.size()>1){
			logger.info("[支付回调进行以旧换新]旧优惠券code数据出错,该code对应多条记录,couponUnitCode="+recordDTOList.get(0).getOldUnitCode());
			CancelSo(orderId,orderCode,name,userId);
			isSuccessExchange = false;
			return isSuccessExchange;

		}
		List<CouponBatchDTO> batchDTOList=couponBatchReadService.findCouponBatchAllByBatchCode(recordDTOList.get(0).getNewBatchCode());
		if(EmptyUtil.isEmpty(batchDTOList)){
			logger.info("[支付回调进行以旧换新]新优惠券批次不存在,couponBatchCode="+recordDTOList.get(0).getNewBatchCode());
			isSuccessExchange = false;
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;

		}else if(batchDTOList.size()>1){
			logger.info("[支付回调进行以旧换新]新优惠券批次数据出错,该code对应多条记录,couponBatchCode="+recordDTOList.get(0).getNewBatchCode());
			isSuccessExchange = false;
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;

		}
		//1.校验当前兑换是否依然有效
		isSuccessExchange = checkExchangeValid(couponUnitDTOList.get(0).getId(), batchDTOList.get(0).getId());
		if(!isSuccessExchange){
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;
		}
		//2.设置旧优惠券unit为已兑换状态
		//3.生成新的优惠券批次对应的couponUnit
		//4.更新兑换记录
		isSuccessExchange=exchangeOrderRecordWriteService.updateExchangeAndCouponWithTx(new UpdateExchangeAndCouponWithTxDTO(couponUnitDTOList.get(0).getCouponUnitCode(),couponUnitDTOList.get(0).getCouponUnitStatus(),couponUnitDTOList.get(0).getId(),batchDTOList.get(0),recordDTOList.get(0).getId(),soDTO.getUserId()));
		if(!isSuccessExchange){
			CancelSo(orderId,orderCode,name,userId);
			return isSuccessExchange;
		}else{
			//成功后处理订单状态
			soWriteService.changeOrderStatusByOrderId(orderCode, orderId, OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(), 3, null, null);
			return isSuccessExchange;
		}

	}

	@Override
	public int jdOrderConfirm(String name, Long userDTOId, Long id, Long jdOrderId, Long soChildId, Long soId, String orderCode) {
		int resultType =0;
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		String result = jdUtils.confirmOrder(token, jdOrderId.toString());
		if(EmptyUtil.isNotEmpty(result)){
			JSONObject resultObj = JSONObject.parseObject(result);
			if(resultObj.getString("resultCode").equals("3103")||resultObj.getString("result").equals("true")){
				//成功,下单
				SoDTO soDTO = new SoDTO();
				soDTO.setId(soId);
				soDTO.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
				soWriteService.updateOrderWithTX(soDTO);
				JdOrderAwaitQueueDTO dto = new JdOrderAwaitQueueDTO();
				dto.setId(id);
				jdOrderAwaitQueueWriteService.deleteJdOrderAwaitQueueWithTx(dto);
				resultType=1;
			}else if(resultObj.getString("result").equals("false")){
				//预占失败
				resultType = 2;
				CancelSo(soId,orderCode,name,userDTOId);
				//3)记录失败原因
				logger.error("[京东商品预占库存失败],原因:code:"+resultObj.getString("resultMessage"));
				if(resultObj.getString("resultCode").equals("3017")){
					//发送余额不足预警
					jdUtils.sendErrEmail(resultObj.getString("resultMessage"),soId,jdOrderId);
				}
			}
		}else{
			resultType=3;
		}
		return resultType;








	}

	//校验有效性,当前unit与目标批次,是否有有效的兑换活动,目标批次是否还有可兑换的优惠券
	public Boolean checkExchangeValid(Long couponUnitId, Long batchId) {
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(couponUnitId);
		CouponUnitDTO unitDTO = couponUnitReadService.findCouponUnitById(couponUnitDTO);
		//查询旧unit对应的活动id
		List<Long> list = com.egeo.utils.StringUtils.stringsToLongs(exchangeBatchReadService.findExchangeActivityByOldCouponUnitId(unitDTO.getCouponBatchId(), unitDTO.getCouponUnitStatus()));
		//查询新的batch对应的活动id
		ExchangeBatchDTO dto = new ExchangeBatchDTO();
		dto.setBatchId(batchId);
		dto.setType(1);
		List<Long> activityIds=com.egeo.utils.StringUtils.stringsToLongs(exchangeBatchReadService.findExchangeIdsByBatch(dto));
		Boolean flag=false;
		for(Long id:list){
			for(Long newId:activityIds){
				if(id.equals(newId)){
					flag=true;
				}
			}
		}
		Boolean count = ckeckCouponBatchCount(batchId);
		Boolean aBoolean = ckeckNewBatch(batchId);//校验目标批次的有效性(/是否失效,领取日期是否有效,前端是否展示)

		if(flag&&count&&aBoolean){
			return true;
		}else{
			return false;
		}
		//校验目标批次

	}
	private Boolean ckeckNewBatch(Long batchId){
		CouponBatchDTO batchDTO = new CouponBatchDTO();
		batchDTO.setId(batchId);
		CouponBatchDTO couponBatchById = couponBatchReadService.findCouponBatchById(batchDTO);
		if(EmptyUtil.isEmpty(couponBatchById)){
			logger.info("[兑换流程校验是否有效]错误:未查询到目标批次");
			return false;
		}else if(couponBatchById.getIsDisplay()==0){
			logger.info("[兑换流程校验是否有效]错误:目标批次前端不可显示");
			return false;
		}else if(couponBatchById.getReceiveEndTime().getTime()<new Date().getTime()){
			logger.info("[兑换流程校验是否有效]错误:目标批次可领取时间已过");
			return false;
		}else if(couponBatchById.getReceiveStartTime().getTime()>new Date().getTime()){
			logger.info("[兑换流程校验是否有效]错误:目标批次可领取时间还未到");
			return false;
		}else if(couponBatchById.getIsEffect()==1){
			logger.info("[兑换流程校验是否有效]错误:目标批次已失效");
			return false;
		}
		return true;
	}
	//校验优惠券批次是否还有剩余的可领券
	private Boolean ckeckCouponBatchCount(Long batchId){
		CouponBatchDTO batchDTO = new CouponBatchDTO();
		batchDTO.setId(batchId);
		CouponBatchDTO couponBatchDTO=couponBatchReadService.findCouponBatchById(batchDTO);
		if(EmptyUtil.isEmpty(couponBatchDTO)){
			throw new BusinessException("优惠券批次不存在");
		}
		//查询已领取的优惠券数量
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setCouponBatchId(batchId);
		Long couponUnitAllCount = couponUnitReadService.findCouponUnitAllCount(couponUnitDTO);
		if(couponBatchDTO.getGrantCount()==-1||couponUnitAllCount.compareTo(Long.valueOf(couponBatchDTO.getGrantCount()))<0){
			return true;
		}else{
			return false;
		}


	}

/*	public static void main(String[] args) {
		String str = "{\n" +
				"\t\"body\": {\n" +
				"\t\t\"actid\": \"CShgg0001\",\n" +
				"\t\t\"infos\": [{\n" +
				"\t\t\t\"amount\": \"1\",\n" +
				"\t\t\t\"infos2\": [{\n" +
				"\t\t\t\t\"barcode\": \"18112119130822816026\",\n" +
				"\t\t\t\t\"barpwd\": \"128516365907\",\n" +
				"\t\t\t\t\"duedt\": \"2021[0xc4][0xea]12[0xd4][0xc2]31[0xc8][0xd5]\",\n" +
				"\t\t\t\t\"ticktype\": \"1\"\n" +
				"\t\t\t}],\n" +
				"\t\t\t\"phoneno\": \"15280038813\"\n" +
				"\t\t}],\n" +
				"\t\t\"mercid\": \"888030860645463\",\n" +
				"\t\t\"ordno\": \"1728\",\n" +
				"\t\t\"ticktype\": \"1\",\n" +
				"\t\t\"tranid\": \"201811212138010\"\n" +
				"\t},\n" +
				"\t\"header\": {\n" +
				"\t\t\"apiid\": \"620006\",\n" +
				"\t\t\"busdt\": \"20181121\",\n" +
				"\t\t\"chnno\": \"13389\",\n" +
				"\t\t\"ipaddr\": \"127.0.0.1\",\n" +
				"\t\t\"reqjnl\": \"1811216811563977163417720\",\n" +
				"\t\t\"reqopetm\": \"20181121191305\",\n" +
				"\t\t\"respcode\": \"RMP0000\",\n" +
				"\t\t\"respjnl\": \"2867990119610617252\",\n" +
				"\t\t\"respmsg\": \"[0xbd][0xbb][0xd2][0xd7][0xb3][0xc9][0xb9][0xa6]\",\n" +
				"\t\t\"respopetm\": \"20181121191308\",\n" +
				"\t\t\"version\": \"1.0\"\n" +
				"\t},\n" +
				"\t\"security\": {\n" +
				"\t\t\"desvalue\": \"nu4frE1d9Sau6tYnZE2feP7kU5vm6TECii9xpph/t6k=\",\n" +
				"\t\t\"signvalue\": \"068d14b47018cd0aa726c7b7cdf4eeb2\"\n" +
				"\t}\n" +
				"}";
		Gson gson = new Gson();
		QCResult qcResult = JsonUtils.jsonToPojo(str, QCResult.class);
		qcResult.getHeader().getRespcode().equals("RMP0000");
	}*/

	private void CancelSo(Long orderId,String orderCode,String userName,Long userId){
// 2)订单状态改为 待付款,自动退款
		// 参考: SoManageImpl.cancelOrder
		List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(orderId);
		for (SoItemDTO it : items) {
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(it.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
					.findCommodityProductUnitById(commodityProductUnitDTO);

			//根据订单ID查询 puIds
			//根据puIds
			List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
			commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(Arrays.asList(orderCode),StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			List<Long> puIdList = new ArrayList<>();
			Set<Long> puIdSet = new HashSet<>();
			for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
				puIdSet.add(waterDTO.getCommodityProductUnitId());
			}
			puIdList.addAll(puIdSet);

			List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());

			if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
				puIdList.add(it.getPuId());
				commodityProductUnitDTOs.add(commodityProductUnitDTO2);
				stockWriteService.recoverOrderStockBatch(new RecoverOrderStockBatchDTO(it.getPuId(), it.getPuCount(),orderCode,
						StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), userId,
						userName, null, null, puIdList, commodityProductUnitDTOs));
			}else {
				commodityProductUnitWarehouseStockWriteService.recoverOrderStock(it.getPuId(),
						it.getPuCount(), orderCode,
						StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(),
						commodityProductUnitDTO2.getProductUnitSerialNumber(),
						commodityProductUnitDTO2.getName(), userId, userName, "", "");
			}

		}

		// 充值失败:(已完善)[订单状态切换为 已取消,已付款,已确认,等待后台手动退款(待完善)]
		soWriteService.changeOrderStatusByOrderId(orderCode, orderId,
				OrderConstant.ORDER_STATUS_CANCELED.getStatus(), 1, 1, null);

		// 订单状态切换为 已取消,已退款,已取消,直接自动退款(待公共facada完成)8U6H

		//处理以旧换新回退(同一个订单的处理)
		SoDTO soById = soReadService.findSoById(orderId);
		ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
		recordDTO.setOrderCode(soById.getOrderCode());
		List<ExchangeOrderRecordDTO> exchangeOrderRecordAll = exchangeOrderRecordReadService.findExchangeOrderRecordAll(recordDTO);
		if(EmptyUtil.isNotEmpty(exchangeOrderRecordAll)&&exchangeOrderRecordAll.size()==1){
			ExchangeOrderRecordDTO recordDTO2 = new ExchangeOrderRecordDTO();
			recordDTO2.setOldUnitCode(exchangeOrderRecordAll.get(0).getOldUnitCode());
			recordDTO2.setConversionStatus(Integer.valueOf(1));
			List<ExchangeOrderRecordDTO> exchangeOrderRecordAll2 = exchangeOrderRecordReadService.findExchangeOrderRecordAll(recordDTO2);
			logger.info("exchangeOrderRecordAll2="+exchangeOrderRecordAll2);
			if(EmptyUtil.isNotEmpty(exchangeOrderRecordAll2)&&exchangeOrderRecordAll2.get(0).getOrderCode().equals(soById.getOrderCode())){
				Integer status = exchangeOrderRecordAll.get(0).getOldUnitStatus();
				CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
				couponUnitDTO.setCouponUnitStatus(status);
				couponUnitDTO.setCouponUnitCode(exchangeOrderRecordAll.get(0).getOldUnitCode());
				couponUnitWriteService.updateCouponUnitByParamWithTx(couponUnitDTO);
				//释放unit锁
				jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+exchangeOrderRecordAll2.get(0).getOldUnitCode());
				//int i = couponUnitWriteService.updateCouponUnitRemoveLock(exchangeOrderRecordAll2.get(0).getOldUnitCode());
				logger.info("释放锁成功");



				if(EmptyUtil.isNotEmpty(exchangeOrderRecordAll.get(0).getNewUnitCode())){
					CouponUnitDTO unitDTO = new CouponUnitDTO();
					unitDTO.setCouponUnitCode(exchangeOrderRecordAll.get(0).getNewUnitCode());
					couponUnitWriteService.deleteCouponUnitByParamWithTx(unitDTO);
				}
			}


			//取消订单成功,如果是以旧换新更新相关记录
			ExchangeOrderRecordDTO recordDTO1 = new ExchangeOrderRecordDTO();
			recordDTO1.setConversionStatus(Integer.valueOf(2));
			recordDTO1.setOrderCode(soById.getOrderCode());
			int rt=exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO1);



		}



	}

	/**
	 * 记录订单完成日志
	 * @param oldSoDTO
	 */
	private void recordConfirmOrderLog(SoDTO oldSoDTO, String operObject) {
		SoDTO newSoDTO = soReadService.findSoById(oldSoDTO.getId());
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
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}
	}

	/**
	 * 是否允许京东下单
	 * @param companyId
	 * @return
	 */
	private boolean jdOrderAllow(Long companyId){
		boolean jdOrderAllow = true;
		List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(companyId);
		if (EmptyUtil.isNotEmpty(configs)){
			for(CompanyConfigDTO config : configs) {
				if(config.getKey().equalsIgnoreCase("order.jd.forbid")&& config.getValue()!=null && config.getValue().length()==1) {
					jdOrderAllow = (Integer.valueOf(config.getValue()).intValue()==1)?false:true;
					break;
				}
			}
		}
		return jdOrderAllow;
	}


	/**
	 * 取消订单,自动退款
	 * @param order
	 * @param items
	 * @param userId
	 * @param soRefundCodeByFubi
	 * @param soRefundCodeByCash
	 */
	@Override
	public void cancelAndRefundOrderWithTx(String userName,SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
										   String soRefundCodeByCash,String soRefundCodeByJiDian,String soRefundCodeByBuyCard, HttpServletRequestDTO req){
		logger.info("订单:{}进入退款流程",order.getOrderCode());
		// 退款前检查账户是否异常
		checkAccountBeforeRefund(order);

		// 2.退还现金
		// 2.1)退还现金成功,生成现金退款批次和流水,生成退款单,记录订单已退现金,生成微信/支付宝退款日志
		// 2.2)退还现金失败,发送预警邮件,走后台手动退款逻辑
		boolean isFullRefund = refundCash(userId,order,order.getOrderPaidByCash(),items,soRefundCodeByCash);
		// 1.退还积分,生成积分退款批次和流水,生成退款单,记录订单已退积分
		// 3.更新订单: 订单状态(已取消),确认状态(已取消),支付状态(是否全部退还, 是:已退款 否:不变),已退积分,已退现金
		SoDTO soDTO = new SoDTO();
		soDTO.setId(order.getId());
		soDTO.setOrderCode(order.getOrderCode());
		soDTO.setOrderPayStatus(isFullRefund ? 2 : null);
		soDTO.setRefundCash(isFullRefund ? order.getOrderPaidByCash() : BigDecimal.ZERO);
		soDTO.setRefundFubi(order.getOrderPaidByFubi());
		soDTO.setRefundJidian(order.getOrderPaidByJidian());
		soDTO.setRefundCard(order.getOrderCardPaid());
		soDTO.setUserId(order.getUserId());
		soDTO.setOrderConfirmStatus(2);
		soDTO.setOrderStatus(OrderConstant.ORDER_STATUS_CANCELED.getStatus());
		soDTO.setPlatformId(order.getPlatformId());
		List<RefundItemVo> itemVos=new ArrayList<>();
		for (SoItemDTO item : items) {

			BigDecimal canRefundAmount = item.getPrice().multiply(new BigDecimal(item.getPuCount())).setScale(2);
			int subCount = item.getPuCount();
			if(item.getRefundAmount() !=null){
				canRefundAmount = canRefundAmount.subtract(item.getRefundAmount());
			}
			//加上承担的运费
			if(item.getDeliveryFeeAver() !=null){
				canRefundAmount = canRefundAmount.add(item.getDeliveryFeeAver());
			}
			//减去已退运费
			if(item.getRefundDeliveryFee() !=null){
				canRefundAmount = canRefundAmount.subtract(item.getRefundDeliveryFee());
			}
			if(item.getPuCount() !=null){
				subCount = subCount - item.getRefundCount();
			}
			RefundItemVo itemVo=new RefundItemVo();
			itemVo.setRefundAmount(canRefundAmount);
			itemVo.setRefundNum(subCount);
			itemVo.setSkuId(String.valueOf(item.getPuId()));
			itemVo.setSkuName(item.getPuName());
			itemVo.setPrice(item.getPrice());
			itemVo.setRefundDeliveryFee(BigDecimal.ZERO);
			itemVo.setSoItemId(item.getId());
			itemVo.setSource(item.getSource());
			itemVo.setPlatformId(item.getPlatformId());
			itemVos.add(itemVo);
		}



		RefundVo refundVo = new RefundVo();
		refundVo.setSoItemDTOS(items);
		refundVo.setRefundItemVos(itemVos);
		soDTO.setRefundVo(refundVo);
		logger.info("订单:{}现金退款金额:{}",order.getOrderCode(),soDTO.getRefundCash());
		logger.info("订单:{}积分退款金额:{}",order.getOrderCode(),soDTO.getRefundFubi());
		logger.info("订单:{}积点退款金额:{}",order.getOrderCode(),soDTO.getRefundJidian());
		logger.info("订单:{}卡劵退款金额:{}",order.getOrderCode(),soDTO.getRefundCard());
		soRefundWithTx(soDTO, soRefundReason, userId, soRefundCodeByFubi, soRefundCodeByCash, soRefundCodeByJiDian,soRefundCodeByBuyCard,true,null, req);

		//判断是取消还是退款
		if(order.getOrderStatus().intValue() ==OrderConstant.ORDER_STATUS_UNPAY.getStatus()){
			//pushOrderManage.pushOrderInfo(order.getId(),null,null);
		}else if(order.getOrderStatus().intValue() ==OrderConstant.ORDER_STATUS_CANCELED.getStatus() || order.getOrderStatus().intValue() ==OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus()){
			//pushOrderManage.pushOrderInfo(order.getId(),null,null);
		}else{
			//走的退款
			CancelAndRefundOrderExtendsWithTxDTO cancelAndRefundOrderExtendsWithTxDTO = new CancelAndRefundOrderExtendsWithTxDTO();
			cancelAndRefundOrderExtendsWithTxDTO.setOrder(soDTO);
			cancelAndRefundOrderExtendsWithTxDTO.setReq(req);
			cancelAndRefundOrderExtendsWithTxDTO.setSoRefundCodeByCash(soDTO.getRefundCash().toPlainString());
			cancelAndRefundOrderExtendsWithTxDTO.setSoRefundCodeByJiDian(soDTO.getRefundJidian().toPlainString());
			cancelAndRefundOrderExtendsWithTxDTO.setSoRefundCodeByFubi(soDTO.getRefundFubi().toPlainString());
			cancelAndRefundOrderExtendsWithTxDTO.setSoRefundCodeByBuyCard(soDTO.getRefundCard().toPlainString());
			cancelAndRefundOrderExtendsWithTxDTO.setUserName(userName);
			cancelAndRefundOrderExtendsWithTxDTO.setItems(items);
			cancelAndRefundOrderExtendsWithTxDTO.setReason(soRefundReason);

			for (SoItemDTO item : items) {
				BigDecimal refundAmount = item.getPrice().multiply(new BigDecimal(item.getPuCount())).setScale(2);
				item.setRefundAmount(refundAmount);
				item.setRefundCount(item.getPuCount());
			}
			pushOrderManage.pushRefundOrder(cancelAndRefundOrderExtendsWithTxDTO);
		}


		// 5.取消订单成功,记录取消订单的日志
		SoDTO newSoDTO = soReadService.querySoById(order.getId());
		EgeoLog log = new EgeoLog();
		log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
		log.setOperObject("SoServiceImpl_cancelAndRefundOrderWithTx");
		log.setMsgId(LogConstant.ORDER_CANCEL.getStatus());
		log.setType(LogTypeConstant.SO.getStatus());
		log.setOperatorObjId(newSoDTO.getId());
		log.setOperatorObjCode(newSoDTO.getOrderCode());
		log.setNewObj(newSoDTO);
		log.setOldObj(order);

		EgeoBusinessLogCommon.fillLogValueForBasic(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}


//校验是否为正式公司
		if(checkCompany(order.getCompanyId())){
			// 4.回滚库存: 根据orderid释放库存(订单项有可能是unit商品,回滚时检验unit存在性,回滚unit冻结库存)
			for (SoItemDTO it : items) {
				if (it.isQM() || it.isCake()|| it.isWorld()){
					continue;
				}
				CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
				commodityProductUnitDTO.setId(it.getPuId());
				CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
						.findCommodityProductUnitById(commodityProductUnitDTO);

				//根据订单ID查询 puIds
				//根据puIds
				List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
						commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(Arrays.asList(order.getOrderCode()),StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
				List<Long> puIdList = new ArrayList<>();
				Set<Long> puIdSet = new HashSet<>();
				for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
					puIdSet.add(waterDTO.getCommodityProductUnitId());
				}
				puIdList.addAll(puIdSet);
				List<CommodityProductUnitDTO> commodityProductUnitDTOs = null;
				if(!CollectionUtils.isEmpty(puIdList) && commodityProductUnitDTO2 !=null){
					commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());
				}

				if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
					puIdList.add(it.getPuId());
					commodityProductUnitDTOs.add(commodityProductUnitDTO2);
					stockWriteService.recoverOrderStockBatch(new RecoverOrderStockBatchDTO(it.getPuId(), it.getPuCount(), order.getOrderCode(),
							StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), userId,
							userName, null, null, puIdList, commodityProductUnitDTOs));
				}else {
					if(commodityProductUnitDTO2 !=null){
						stockWriteService.recoverOrderStock(it.getPuId(), it.getPuCount(), order.getOrderCode(),
								StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(),
								commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
								userName, "", "");
					}
				}

			}
			// 更新门店pu库存信息
			storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), order, items));
		}
	}


	/**
	 * 退款
	 * @param vo
	 */
	@Override
	public void refundOrderProductWithTx(RefundProductRequestVO vo) {
		checkParam(vo);
		SoDTO order = getSoDTO(vo);
		List<SoItemDTO>  items =choiceSoItems(vo,order);
		if(EmptyUtil.isNotEmpty(vo.getProductId()) && CollectionUtils.isEmpty(items)){
			throw new BusinessException("未找到对应的商品");
		}
		UserDTO userDTO = userReadService.findUserByID(order.getUserId());
		cancelAndRefundOrderWithTx(userDTO.getName(),order,items,order.getUserId(),vo.getSoRefundCodeByFubi(),vo.getSoRefundCodeByCash(),vo.getSoRefundCodeByJiDian(),vo.getSoRefundCodeByBuyCard(),vo.getReq());
	}

	private List<SoItemDTO> choiceSoItems(RefundProductRequestVO vo,SoDTO order){
		SoChildDTO soChildDTO = null;
		if(EmptyUtil.isNotEmpty(vo.getChildCode())){
			soChildDTO = soChildReadService.querySoChildByChildCode(vo.getChildCode());
			throw new BusinessException("未找到对应的子订单");
		}
		SoItemDTO soItemDTO = new SoItemDTO();
		soItemDTO.setExternalProductId(vo.getProductId());
		soItemDTO.setPuId(EmptyUtil.isNotEmpty(vo.getPuId())?Long.valueOf(vo.getPuId()):null);
		soItemDTO.setSoId(order.getId());
		soItemDTO.setSoChildId(soChildDTO !=null ?soChildDTO.getId():null);
		return soItemReadService.findAll(soItemDTO);
	}

	private void checkParam(RefundProductRequestVO vo){
		if(EmptyUtil.isEmpty(vo.getOrderId()) && EmptyUtil.isEmpty(vo.getOrderCode())){
			throw new BusinessException("缺少订单信息必要条件");
		}


	}

	private SoDTO getSoDTO(RefundProductRequestVO vo){
		if(EmptyUtil.isNotEmpty(vo.getOrderId())){
			return soReadService.findSoById(Long.valueOf(vo.getOrderId()));
		}
		if(EmptyUtil.isNotEmpty(vo.getOrderCode())){
			return soReadService.querySoByOrderCode(vo.getOrderCode());
		}
		return null;
	}

	private void saveBuyCardUseDetail(SoRefundDTO dto,String orderCode){
		if(dto.getSoRefundByBuyCard()==null || dto.getSoRefundByBuyCard().compareTo(BigDecimal.ZERO) <=0){
			logger.info("订单:{}进入退款，不是卡劵退款，无需保存明细saveBuyCardUseDetail...",orderCode);
			return;
		}
		List<SoRefundItemDTO> list = dto.getSoRefundItemDTOS();
		if(CollectionUtils.isEmpty(list)){
			logger.error("订单id:{},订单:{}未找到订单项退款",dto.getSoId(),orderCode);
			return;
		}
		List<BuyCardItemRefundDTO> dtos = new ArrayList<>();
		for (SoRefundItemDTO soRefundItemDTO : list) {
			BuyCardItemRefundDTO buyCardItemRefundDTO = new BuyCardItemRefundDTO();
			buyCardItemRefundDTO.setItemId(soRefundItemDTO.getSoItemId());
			buyCardItemRefundDTO.setRefundAmount(soRefundItemDTO.getRefundAmount());
			buyCardItemRefundDTO.setRefundNo(soRefundItemDTO.getRefundCode());
			buyCardItemRefundDTO.setRemark(dto.getSoRefundReason());
			buyCardItemRefundDTO.setSoId(dto.getSoId());
			dtos.add(buyCardItemRefundDTO);
		}

		if(!CollectionUtils.isEmpty(dtos)){
			BuyCardRefundReqVO reqVO = new BuyCardRefundReqVO();
			reqVO.setDtos(dtos);
			buyCardClient.buyCardRefund(reqVO);
		}

	}
}
