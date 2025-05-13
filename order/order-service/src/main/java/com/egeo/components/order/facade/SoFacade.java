package com.egeo.components.order.facade;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.SoRefundNewManage;
import com.egeo.components.order.business.WorldBuyOrderManage;
import com.egeo.components.order.business.thread.CommonChildThreadPoolExecutor;
import com.egeo.components.order.business.thread.CommonThreadPoolExecutor;
import com.egeo.components.order.business.thread.OrderCreateProductThread;
import com.egeo.components.order.business.thread.SyncCreateThirdPartyOrderThread;
import com.egeo.components.order.condition.SoExtendsCondition;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.manage.read.SoItemReadManage;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.components.order.vo.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.utils.*;
import com.egeo.utils.*;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.service.read.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.business.impl.SoThirdpartyManageImpl;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.controller.client.SoController;
import com.egeo.components.order.dto.CancelAndRefundOrderWithTxDTO;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.NewSoOrderDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildDTOCondition;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.dto.SoDetailDTO;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoRefundWithTxDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.service.write.LimitRuleRecordWriteService;
import com.egeo.components.order.service.write.ReceiverAddressWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoDeliveryItemWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.vo.jd.JdOrderSubmit;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.pay.client.AwaitQueueClient;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MembershipClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.client.ProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardProductUnitAttNameClient;
import com.egeo.components.product.client.StandardProductUnitAttValueClient;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.client.StoreTreeNodeClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.SoItemSkuAttValueDTO;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.vo.JdResponse;
import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.client.ExchangeActivityClient;
import com.egeo.components.promotion.client.ExchangeOrderRecordClient;
import com.egeo.components.promotion.client.FuCoinClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.ContactGroupPuStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.FreeseOrderStockBatchDTO;
import com.egeo.components.stock.dto.FreezeStockWithTxDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.dto.RecoverOrderStockBatchDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.dto.UnFreeseOrderStockBatchDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;
import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.pay.PayUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Component
public class SoFacade {

	private static final String CommodityProductUnitDTO = null;

	private static final XLogger logger = XLogger.getLogger(SoFacade.class);

	@Autowired
	private SoController soService;

	@Resource
	private SoReadService soReadService;

	@Resource
	private SoWriteService soWriteService;

	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockWriteService;

	@Autowired
	private MerchantProductClient merchantProductReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient stockWriteService;

	@Autowired
	private MerchantProductVirtualStockClient unitStockWriteService;

	@Autowired
	private SoItemClient soItemReadService;

	@Autowired
	private SkuClient skuReadServcie;
	@Resource
	private SoThirdpartyWriteService soThirdpartyWriteService;
	@Autowired
	private FuCoinClient fuCoinReadService;
	@Autowired
	private MerchantClient merchantReadService;
	@Autowired
	private UserClient userReadService;

	@Autowired
	private UserExtendClient userExtendReadService;

	@Resource
	private SoChildReadService soChildReadService;

	@Autowired
	private CommodityProductUnitClient puReadService;

	@Resource
	private SoChildWriteService soChildWriteService;

	@Resource
	private ReceiverAddressWriteService raWriteService;

	@Autowired
	private AccountBatchClient abWrteService;

	@Autowired
	private UserAccountClient uaReadService;

	@Autowired
	private CompanyAccountClient caReadService;

	@Autowired
	private SaltClient saltReadService;

	@Autowired
	private AwaitQueueClient awaitQueueReadService;

	@Autowired
	private AwaitQueueClient awaitQueueWriteService;

	@Resource
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;

	@Autowired
	private CouponUnitClient couponUnitReadService;

	@Autowired
	private CouponUnitClient couponUnitWriteService;

	@Autowired
	private SoFreezeFubiClient ffReadService;

	@Autowired
	private SoFreezeFubiClient ffWriteService;

	@Autowired
	private UserAccountClient userAccountWriteService;

	@Autowired
	private UserAccountClient userAccountReadService;

	@Resource
	private SoRefundReadService soRefundReadService;

	@Autowired
	private CompanyCoreClient companyCoreReadService;

	@Autowired
	private SendInfoClient sendInfoWriteService;

	@Resource
	private ReceiverAddressWriteService receiverAddressWriteService;

	@Autowired
	private ProductUnitClient productUnitReadService;

	@Autowired
	private MembershipClient membershipReadService;
	@Autowired
	private MembershipUserClient membershipUserReadService;

	@Autowired
	private MembershipUserClient membershipUserWriteService;
	@Autowired
	private StoreClient storeReadService;
	@Autowired
	private StoreTreeNodeClient storeTreeNodeReadService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockService;
	@Resource
	private SoDeliveryItemWriteService soDeliveryItemWriteService;
	@Autowired
	private StandardProductUnitClient standardProductUnitReadService;
	@Autowired
	private StandardProductUnitAttNameClient standardProductUnitAttNameReadService;
	@Autowired
	private StandardProductUnitAttValueClient standardProductUnitAttValueReadService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockReadService;
	@Autowired
	private CompanyClient companyReadService;
	@Resource
	private SoPackageReadService soPackageReadService;

	@Resource
	private LimitRuleRecordWriteService limitRuleRecordWriteService;
	@Resource
	private LimitRuleRecordReadService limitRuleRecordReadService;
	@Autowired
	private CouponBatchClient couponBatchReadService;
	@Autowired
	private CouponClient couponReadService;
	@Autowired
	private ExchangeActivityClient exchangeActivityReadService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordWriteService;
	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordReadService;

	@Autowired
	private ContactGroupPuStockClient contactGroupPuStockReadService;

	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
	@Resource
	private LimitRuleReadService limitRuleReadService;
	@Autowired
	private JdUtils jdUtils;
	@Autowired
	private PayUtil payUtil;
    @Resource
    private QmOrderReadService qmOrderReadService;
	@Autowired
	private CakeUtil cakeUtil;

	@Resource(name = "productFacade")
	private ProductFacade productFacade;
	@Autowired
	private JdUtils2 jdUtils2;

	@Autowired
	private WorldBuyOrderManage worldBuyOrderManage;

	@Resource
	private WorldBuyUtil worldBuyUtil;
	@Resource
	private PushOrderManage pushOrderManage;

	@Resource
	private SoThirdpartyReadService soThirdpartyReadService;

	public Boolean checkOrderValidation(List<SoItemDTO> soItemDTOList) {
		// 目前只考虑库存，其他因素以后再考虑
		return lockItemsStock(soItemDTOList);
	}

	private boolean lockItemsStock(List<SoItemDTO> soItemDTOList) {
		List<MerchantProductWarehouseStockDTO> mpsDtoList = converSoItemListToMpsList(soItemDTOList);
		boolean b = merchantProductWarehouseStockWriteService.batchLockMerchantProductStockWithTx(mpsDtoList);
		return b;
	}

	private List<MerchantProductWarehouseStockDTO> converSoItemListToMpsList(List<SoItemDTO> soItemDTOList) {
		List<MerchantProductWarehouseStockDTO> mpsDtoList = new ArrayList<MerchantProductWarehouseStockDTO>();
		for (SoItemDTO soItemDto : soItemDTOList) {
			mpsDtoList.add(converSoItemToMps(soItemDto));
		}
		return mpsDtoList;
	}

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

	/**
	 * 改变订单状态
	 *
	 * @param orderCode
	 * @param status
	 *            总状态
	 * @param confirmStatus
	 *            确认状态
	 * @param payStatus
	 *            支付状态
	 * @param deliveryStatus
	 *            物流状态
	 * @return
	 */
	public boolean changeOrderStatus(String orderCode, Integer status, Integer confirmStatus, Integer payStatus,
			Integer deliveryStatus) {
		return soWriteService.changeOrderStatusByOrderCode(orderCode, status, confirmStatus, payStatus, deliveryStatus);

	}

	/**
	 * 改变订单状态
	 *
	 * @param
	 * @param orderCode
	 *@param status
	 *            总状态
	 * @param confirmStatus
 *            确认状态
	 * @param payStatus
*            支付状态
	 * @param deliveryStatus
*            物流状态     @return
	 */
	public boolean changeOrderStatus(String orderCode, Long id, Integer status, Integer confirmStatus, Integer payStatus,
									 Integer deliveryStatus) {

		boolean isChangeStatus = soWriteService.changeOrderStatusByOrderId(orderCode,id, status, confirmStatus, payStatus,
				deliveryStatus);

		return isChangeStatus;

	}

	public boolean changeStockStatusLockToReal(List<SoItemDTO> soItemDTOList) {

		// 改变所有购买项的库存，把锁定的库存，变为实际减少的库存
		List<MerchantProductWarehouseStockDTO> mpsDtoList = new ArrayList<>();
		for (SoItemDTO soItemDto : soItemDTOList) {
			mpsDtoList.add(converSoItemToMps(soItemDto));
		}
		return merchantProductWarehouseStockWriteService.batchChangeStockStatusLockToRealWithTx(mpsDtoList);

	}

	/**
	 * 解锁订单库存 这个方法使用skuId解锁库存,该逻辑已过时
	 *
	 * @param soItemDTOList
	 * @return
	 */
	@Deprecated
	public boolean unlockItemsStock(List<SoItemDTO> soItemDTOList) {
		// 改变所有购买项的库存，解锁锁定的库存
		List<MerchantProductWarehouseStockDTO> mpsDtoList = new ArrayList<>();
		for (SoItemDTO soItemDto : soItemDTOList) {
			mpsDtoList.add(converSoItemToMps(soItemDto));
		}
		return merchantProductWarehouseStockWriteService.batchUnlockItemsStockWithTx(mpsDtoList);

	}

	/**
	 * 查询用户不同状态的订单列表
	 *
	 * @param userId
	 * @param orderStatus
	 * @param platformId
	 * @param page
	 * @return
	 */
	public PageResult<SoDetailVO> findOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId,
			Long f, Long clientId, Pagination page) {

		PageResult<SoDetailDTO> dtoResult = soReadService.findOrderByUserAndStatus(userId, orderStatus, platformId,
				page);
		PageResult<SoDetailVO> result = new PageResult<>();
		result.copy(dtoResult);
		// dto转vo
		List<SoDetailDTO> dtoList = dtoResult.getList();
		List<SoDetailVO> voList = new ArrayList<>();
		//存放数据为(子订单ID, 是否自营)
		Map<Long, Integer> merchantMap = new HashMap<>();

		for (SoDetailDTO d : dtoList) {
			SoDetailVO vo = new SoDetailVO();
			SoDTO so = d.getSoDTO();
			// 查询子订单列表
			List<SoChildDTO> scList = soChildReadService.querySoChildListBySoId(so.getId());
			vo.setIfChildOrder("0");
			vo.setId(so.getId());
			vo.setOrderAmount(so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
			//vo.setPayAmount(so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
			vo.setUseFubi(so.getUseFubi());
			vo.setOrderCode(so.getOrderCode());
			vo.setOrderStatus(so.getOrderStatus());
			vo.setCreateTime(so.getCreateTime() != null ? so.getCreateTime().getTime() : null);
			Integer goodsAccount = 0;
			List<OrderConfirmGoodsVO> ocgs = new ArrayList<>();
			List<SoChildWebVO> soChildWebList = new ArrayList<>();
			Boolean buyTypeFlag = false;
			if (f.equals(3L) && clientId.equals(3L)) {
				// web商城订单列表
				for (SoChildDTO soChildDTO : scList) {
					SoChildWebVO soChildWebVO = new SoChildWebVO();
					SoItemDTO soItemDTO = new SoItemDTO();
					soItemDTO.setSoId(so.getId());
					soItemDTO.setSoChildId(soChildDTO.getId());
					soItemDTO.setUserId(userId);
					List<SoItemDTO> soItemList = soReadService.mergeSoItems(soItemReadService.findAll(soItemDTO));

//					boolean unitExist = false;
					List<OrderConfirmGoodsVO> ocgsForWeb = new ArrayList<>();

					for (SoItemDTO it : soItemList) {
						OrderConfirmGoodsVO ocg = new OrderConfirmGoodsVO();
						goodsAccount += it.getPuCount();
						ocg.setNum(it.getPuCount());
						ocg.setPrice(it.getPrice().doubleValue());
						ocg.setPuId(it.getPuId());
						ocg.setPuImg(it.getPuPicUrl());
						ocg.setPuName(it.getPuName());
						ocg.setSource(it.getSource());
						ocg.setChannelProductId(it.getExternalProductId());
						ocg.setStandardUnitId(it.getPuId());
						CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();

						CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
						StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(pu.getStandardUnitId());
						if(standardUnitById.getBuyType()!=3){
							buyTypeFlag = true;
						}
						if(it.isThirdParty()){
							productUnitDTO.setId(it.getPuId());
						}else if(pu !=null){
							ocg.setStandardUnitId(pu.getStandardUnitId());
						}

						Long soChildId = it.getSoChildId();
						if (!merchantMap.containsKey(soChildId)) {
							SoChildDTO soChild = soChildReadService.findSoChildById(it.getSoChildId());
							merchantMap.put(soChildId, soChild.getPerformingParty().equals(1L) ? 1 : 0);
						}
						ocg.setIsOwnMerchant(merchantMap.get(soChildId));
						if (it.getUnitExist() == 1) {
//							unitExist = true;
						}
						ocgsForWeb.add(ocg);
					}
					soChildWebVO.setId(soChildDTO.getId());
					soChildWebVO.setChildCode(soChildDTO.getChildCode());
					soChildWebVO.setGoodsList(ocgsForWeb);
					soChildWebVO.setCreateTime(soChildDTO.getCreateTime().getTime());
					soChildWebVO.setChildOrderAmount(soChildDTO.getAmount());

					soChildWebList.add(soChildWebVO);
				}
			} else {
				// 其他订单列表
				List<SoItemDTO> items = soReadService.mergeSoItems(d.getSoItemDTOList());
				boolean unitExist = false;
				for (SoItemDTO it : items) {
					OrderConfirmGoodsVO ocg = new OrderConfirmGoodsVO();
					goodsAccount += it.getPuCount();
					ocg.setNum(it.getPuCount());
					ocg.setPrice(it.getPrice().doubleValue());
					ocg.setPuId(it.getPuId());
					ocg.setPuImg(it.getPuPicUrl());
					ocg.setPuName(it.getPuName());
					ocg.setSource(it.getSource());
					ocg.setChannelProductId(it.getExternalProductId());
					if(it.isThirdParty()) {
						ocg.setIsOwnMerchant(0);
						ocg.setStandardUnitId(it.getPuId());
					}else {
						CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
						productUnitDTO.setId(it.getPuId());
						CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
						if(pu!=null) {
							StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(pu.getStandardUnitId());
							if(standardUnitById.getBuyType()!=3){
								buyTypeFlag = true;
							}
							ocg.setStandardUnitId(pu.getStandardUnitId());
						}

						Long soChildId = it.getSoChildId();
						if (!merchantMap.containsKey(soChildId)) {
							SoChildDTO soChild = soChildReadService.findSoChildById(it.getSoChildId());
							merchantMap.put(soChildId, soChild.getPerformingParty().equals(1L) ? 1 : 0);
						}
						ocg.setIsOwnMerchant(merchantMap.get(soChildId));
					}

					if (it.getUnitExist() == 1) {
						unitExist = true;
					}
					ocgs.add(ocg);
				}
				vo.setGoodsList(ocgs);
				vo.setUnitExist(unitExist);
			}
			vo.setSoChildWebList(soChildWebList);
			vo.setGoodsAccount(goodsAccount);
			// 收集子订单物流状态
			List<Integer> scDeliveryStatusList = new ArrayList<>();
			Integer thirdpartyType = 0;
			String jumpUrl=null;
			boolean flag=false;
			for (SoChildDTO sc : scList) {
				if(EmptyUtil.isNotEmpty(sc.getReceiverAddressId())){
					flag=true;
				}
				scDeliveryStatusList.add(sc.getDeliveryStatus());
				if (sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
					thirdpartyType = 1;
				}
				if(sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC){
					thirdpartyType = 2;
				}
				if (sc.getThirdpartyType()== ThirdConst.ThirdPartyType.QM){
					thirdpartyType = ThirdConst.ThirdPartyType.QM;
					jumpUrl=sc.getExt();
				}
			}

			//当订单内商品全部为仅支持现金支付，或用户无可用积分（即积分全部冻结或无积分）时，[积分支付]按钮隐藏
			vo.setOperateStatus(judgeOrderOperateStatus(clientId,so.getId(),so.getOrderPayStatus(), so.getOrderConfirmStatus(),
					scDeliveryStatusList, thirdpartyType,so.getLimitCashPayAmount(),so.getOrderAmount(),userId,platformId));

			if(so.getOrderStatus()==4){
				//如果是加价购查询优惠券类型
				if(so.getSaleWay()==8){
					vo.setOperateStatus(6);
					List<ExchangeOrderRecordDTO> list=exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(so.getOrderCode());
					if(EmptyUtil.isEmpty(list)||list.size()>1){
						logger.info("[该订单号数据有误,订单详情]orderCode="+so.getOrderCode());
						throw new BusinessException("该订单号数据有误");
					}
					vo.setExchangeCouponType(list.get(0).getNewCouponType());
				}
			}
			logger.debug("订单操作状态:operateStatus={},id={}", new Object[]{vo.getOperateStatus(), vo.getId()});

			// 电子卡券类商品没有查看物流和确认收货按钮的特殊处理(话费充值类/券仓已特殊处理)
			if (!flag && ((vo.getOperateStatus().equals(Integer.valueOf(2))
					|| vo.getOperateStatus().equals(Integer.valueOf(3))))) {
				logger.debug("订单操作状态:电子卡券类商品没有查看物流和确认收货按钮的特殊处理,id=" + vo.getId());
				vo.setOperateStatus(4);
			}

			if(!buyTypeFlag){
				if(vo.getOperateStatus()==0){
					vo.setOperateStatus(8);
				}
			}
			if (Objects.equals(thirdpartyType,ThirdConst.ThirdPartyType.QM)){
				vo.setOperateStatus(9);//清美订单，直接跳转到清美页面，操作按钮不显示
				vo.setJumpUrl(jumpUrl);
			}
			// 查询订单的优惠卷信息
			vo.setCouponType(this.findCouponUnitByOrder(so));


			voList.add(vo);
		}
		result.setList(voList);
		return result;
	}

	/**
	 * 判断订单操作状态 0:取消和去支付 1:仅取消 2:查看物流和确认收货 3:查看物流和删除订单 4:删除订单 5:无按钮
	 *
	 *
	 *
	 * @param clientId
	 * @param id
	 * @param orderPayStatus
	 *            订单支付状态 0:未支付、1:已支付、2:已退款
	 * @param orderConfirmStatus
	 *            订单确认状态 0:未确认，1:已确认，2:已取消 3:已完成
	 * @param scDeliveryStatusList
	 * @param thirdpartyType
	 *            第三方订单类型: 0:无第三方订单  1:话费充值
	 * @return
	 */
	public Integer judgeOrderOperateStatus(Long clientId, Long id, Integer orderPayStatus, Integer orderConfirmStatus,
										   List<Integer> scDeliveryStatusList, Integer thirdpartyType, BigDecimal limitCashPayAmount,
										   BigDecimal orderAmount, Long userId, Long platformId) {
		logger.debug("订单操作状态判断:orderPayStatus={},orderConfirmStatus={},scDeliveryStatusList={},thirdpartyType={}",
				new Object[]{orderPayStatus, orderConfirmStatus, scDeliveryStatusList, thirdpartyType});
		List<SoChildDTO> soChildDTOList = soChildReadService.querySoChildListBySoId(id);
		boolean isJd = false;
		if(EmptyUtil.isNotEmpty(soChildDTOList)){
			for(SoChildDTO dto:soChildDTOList){
				if(dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD || dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_CAKE ||  dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_WORLD){
					isJd = true;
					if(orderPayStatus==0&&orderConfirmStatus==0){
						return 0;
					}

				}

			}

		}


		if (orderConfirmStatus == 3) {
			if (thirdpartyType == 1||thirdpartyType==2) {
				return 4;
			}
			return 3;
		}
		if (orderConfirmStatus == 2) {
			return 4;
		}
		if (orderPayStatus == 0){
			if (platformId == 7){
				//整体订单积分可抵扣金额为0 或用户无可用积分（即积分全部冻结或无积分）时,[积分支付]按钮隐藏
				boolean flag = userAvailableBalanceJudgment(userId);
				if (limitCashPayAmount.compareTo(orderAmount) == 0 || !flag) {
					if(clientId==4){
						return 1;
					}
					return 7;
				}
			}
			return 0;
		}
		if (orderPayStatus == 1 && orderConfirmStatus == 1) {
			int deliveryStatusSum = 0;
			for (Integer ds : scDeliveryStatusList) {
				deliveryStatusSum += ds;
			}
			// 如果是话费充值/券仓卡券,已支付,已确认,不可取消
			if (thirdpartyType == 1||thirdpartyType==2||thirdpartyType==3){
				return 4;
			}
			//京东订单无取消按钮
			if(isJd&&deliveryStatusSum == 0){
				return 5;
			}else if(isJd&&deliveryStatusSum>0){
				return 3;
			}
			// 在当前条件下,只有所有子订单物流状态都是待发货,订单状态才是已付款
			if (deliveryStatusSum == 0) {
				return 1;
			} else {
				return 2;
			}
		}
		//
		return 4;
	}

	private boolean userAvailableBalanceJudgment(Long userId) {
		//查询用户可用余额:积分账户余额-冻结积分金额
		UserAccountDTO uaFubi=userAccountReadService.queryUserAccountByUserIdAndType(userId,0);
		if (EmptyUtil.isEmpty(uaFubi)) {
			uaFubi.setBalance(BigDecimal.valueOf(0));
		}
		UserAccountDTO uaFreeze=userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
		if (EmptyUtil.isEmpty(uaFreeze)) {
			uaFreeze.setBalance(BigDecimal.valueOf(0));
		}
		//用户可用积分
		BigDecimal useableFubi=uaFubi.getBalance().subtract(uaFreeze.getBalance());

		if (uaFubi.getBalance().compareTo(BigDecimal.ZERO)==0 || useableFubi.compareTo(BigDecimal.ZERO)==0) {
			return false;
		}
		return true;
	}
	/**
	 * 完成sku规格信息
	 *
	 * @param skuAttValues
	 * @param skuId
	 * @return name:XX;value:XX;
	 */
	private String completeSkuInfo(List<SoItemSkuAttValueDTO> skuAttValues, Long skuId) {
		StringBuilder sb = new StringBuilder();
		for (SoItemSkuAttValueDTO sav : skuAttValues) {
			if (sav.getSkuId().longValue() == skuId.longValue()) {
				sb.append(sav.getSkuName()).append(":").append(sav.getAttValueCustom()).append("  ");
			}
		}
		return sb.toString();
	}

	/**
	 * 根据商品名称查询商品列表 应为merchantProductList
	 *
	 *
	 * @return
	 */
	public List<MerchantProductDTO> queryMerchantListByName(String merchantName, Long platformId) {
		return null;
		// return
		// merchantProductReadService.queryMerchantListByName(merchantName,platformId);

	}

	/**
	 * 根据商品id列表查询涉及的所有订单项
	 *
	 * @param merchantIdList
	 * @param platformId
	 * @return
	 */
	public List<SoItemDTO> querySoItemListByMerchantIds(List<Long> merchantIdList, Long platformId) {
		return soItemReadService.querySoItemListByMerchantIds(com.egeo.utils.StringUtils.longsToStrings(merchantIdList), platformId);

	}

	/**
	 * 条件查询订单列表
	 *
	 * @param orderCodeList
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @param orderStatus
	 * @param platformId
	 * @return
	 */
	public List<SoDTO> querySoListByCondition(List<String> orderCodeList, Pagination page, Date startTime, Date endTime,
			Integer orderStatus, String exactOrderCode, Long platformId) {
		return soReadService.querySoListByCondition(orderCodeList, page, startTime, endTime, orderStatus,
				exactOrderCode, platformId);
	}

	/**
	 * 条件查询订单列表的总条目
	 *
	 * @param orderCodeList

	 * @param startTime
	 * @param endTime
	 * @param orderStatus
	 * @param exactOrderCode
	 * @param platformId
	 * @return
	 */
	public int querySoCountByCondition(List<String> orderCodeList, Date startTime, Date endTime, Integer orderStatus,
			String exactOrderCode, Long platformId) {

		return soReadService.querySoCountByCondition(orderCodeList, startTime, endTime, orderStatus, exactOrderCode,
				platformId);
	}

	/**
	 * 修改订单金额信息
	 *
	 * @param orderId
	 * @param orderAmount
	 * @param deliveryAmount
	 * @param payMoney
	 * @return
	 */
	public int updateOrderMoneyInfo(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount,
			BigDecimal payMoney) {
		return soWriteService.updateOrderMoneyInfo(orderId, orderAmount, deliveryAmount, payMoney);
	}

	/**
	 * 修改订单收货信息
	 *
	 * @param orderId
	 * @param receiverName
	 * @param receiverPhone
	 * @param receiverAddress
	 * @return
	 */
	public int updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone, String receiverAddress) {
		return soWriteService.updateDeliveryInfo(orderId, receiverName, receiverPhone, receiverAddress);
	}

	/**
	 * 根据id查询订单
	 *
	 * @param orderId
	 * @return
	 */
	public SoDTO querySoById(Long orderId) {
		return soReadService.querySoById(orderId);
	}

	/**
	 * 查询单个sku信息
	 *
	 * @param skuId
	 * @return
	 */
	public SkuDTO querySkuById(Long skuId) {
		SkuDTO sku = new SkuDTO();
		sku.setId(skuId);
		return skuReadServcie.findSkuById(sku);
	}

	/**
	 * 批量查询sku
	 *
	 * @param skuIds

	 *            是否查询图片,查询图片比较影响速度
	 * @return
	 */
	public List<SkuDTO> querySkusByIds(List<Long> skuIds, Long platformId) {
		return null;

		// return skuReadServcie.querySkusByIds(skuIds,platformId);
	}

	/**
	 * 批量查询商品
	 *
	 * @param mpIds
	 * @param platformId
	 * @return
	 */
	public List<MerchantProductDTO> queryMerchantProductListByIds(List<Long> mpIds, Long platformId) {
		return null;
		// return
		// merchantProductReadService.queryMerchantProductListByIds(mpIds,platformId);
	}

	/**
	 * 删除订单
	 *

	 * @return
	 */
	public int deleteByOrderCode(String orderCode, Long userId) {

		return soWriteService.deleteByOrderCode(orderCode, userId);
	}

	/**
	 * 确认收货
	 *
	 *
	 * @param platformId
	 * @return
	 */
	public String affirmOrderBySoId(SoDTO so, Long platformId) {
		String rt = soWriteService.affirmOrderBySoId(so.getId(), platformId);
		// 发送订单确认状态变更消息

		InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
		Map<String,String> infoMap = new HashMap<String,String>();
		infoDto.setInfoTemplateId(InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus());
		infoDto.setUserId(so.getUserId());
		infoMap.put("platformId", platformId.longValue()+"");
		infoMap.put("orderCode", so.getOrderCode());
		infoMap.put("orderConfirmStatus", OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus()+"");
		infoDto.setParams(infoMap);
		sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(infoDto);


		return rt;
	}

	/**
	 * 根据skuId查询商品
	 *
	 * @param skuId
	 * @return
	 */
	public MerchantProductDTO merchantProductBySkuId(Long skuId) {
		return null;
		// return merchantProductReadService.merchantProductBySkuId(skuId);
	}

	/**
	 * 根据订单id修改订单收货人信息
	 *
	 * @return
	 */
	public JsonResult<String> updateOrderByOrderId(SoDTO dto) {

		return soWriteService.updateOrderByOrderId(dto);
	}

	/**
	 * 根据用户id查询用户积分额度
	 *
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public FuCoinDTO findFCoinByUserId(Long memberId, Long platformId) {

		return fuCoinReadService.findFCoinByUserId(memberId, platformId);
	}

	/**
	 * 根据订单号查询订单
	 *
	 * @param orderCode
	 * @return
	 */
	public SoDTO querySoByOrderCode(String orderCode) {
		return soReadService.querySoByOrderCode(orderCode);
	}

    public QmOrderDTO queryQmOrderBySoId(Long soId) {
        return qmOrderReadService.findBySoId(soId);
    }

	/**
	 * 查询未删除的订单详情
	 *
	 * @param orderCode
	 * @return
	 */
	public SoDTO queryUndeleteSoByOrderCode(String orderCode) {
		return soReadService.queryUndeleteSoByOrderCode(orderCode);
	}

	/**
	 * 根据用户id查询用户手机号码
	 *
	 * @param id
	 * @return
	 */
	public String findOrderUserMobile(Long id) {
		UserDTO findUser = userReadService.findUserByID(id);
		if (findUser == null) {
			return null;
		} else {
			return findUser.getMobile();
		}
	}

	/**
	 * 查询订单列表2.0 soDTO,null,1,startTime,endTime,page
	 *
	 * @param soDTO
	 *
	 * @param startDateTime
	 * @param endDateTime
	 * @param page
	 * @return
	 */
	public PageResult<SoDTO> findSoOfPage(SoDTO soDTO, Integer cashPayType, Integer useFubi, Date startDateTime,
			Date endDateTime, Pagination page) {

		return soReadService.findSoOfPage(soDTO, cashPayType, useFubi, startDateTime, endDateTime, page);
	}

	public UserDTO finduserByUserId(Long userId) {

		return userReadService.findUserByID(userId);
	}

	public UserExtendDTO findUserExtendByUserId(Long userId) {

		return userExtendReadService.findById(userId);
	}

	/**
	 * 普通顶订单创建接口 调用可参考com.egeo.components.order.business.impl.SoManageImpl
	 * 的createOrder方法
	 * @param req
	 * @param jedisUtil
	 * @param addr
	 * @param orderPayByCash
	 * @param exchangeId
	 * @param exchangeCouponUnitId
	 * @param exchangeCouponBatchId
	 * @param sodto
	 * @param sd
	 * @param soItems
	 * @param cartItemIds
	 * @param unitFlag                                  */
	public Long normalOrderCreate(HttpServletRequest req, JedisUtil jedisUtil, ReceiverAddressDTO addr, double orderPayByCash, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, SoDTO sodto, List<SoChildDTO> soChildDTOList, SoDeviceDTO sd, List<SoItemDTO> soItems,
								  List<Long> cartItemIds, boolean unitFlag, List<SoThirdpartyDTO> soThirdpartyDTOList, Long userId, String userName,
								  String ip, String mac, List<LimitRuleRecordDTO> limitRuleRecordList, Integer couponType,
								  Long couponUnitId, Long companyId, Map<Long, BigDecimal> deliveryMap,List<CompanyConfigDTO> configs) {

        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		if (EmptyUtil.isNotEmpty(limitRuleRecordList)) {
			// 拼接su商品id集合
			Set<Long> standardUnitIds = new HashSet<>();
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				standardUnitIds.add(limitRuleRecordDTO.getStandardUnitId());
			}
			// 根据su商品id集合查询su商品信息
			List<StandardUnitDTO> standardUnitList = standardUnitReadService
					.findBymerchantProdId(com.egeo.utils.StringUtils.longsToStrings(new ArrayList<>(standardUnitIds)));
			// 给限购规则su序列号赋值
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				for (StandardUnitDTO standardUnitDTO : standardUnitList) {
					if (limitRuleRecordDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
						limitRuleRecordDTO
								.setStandardUnitSerialNumber(standardUnitDTO.getMerchantProductSerialNumber());
						break;
					}
				}
			}
		}
		if (companyType == 0) {
			//只有正式公司才操作库存
			// 冻结库存
			for (SoItemDTO item : soItems) {
				if(item.isThirdParty()) {
					continue;
				}
				CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
				commodityProductUnitDTO.setId(item.getPuId());
				CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
						.findCommodityProductUnitById(commodityProductUnitDTO);

				int freezeFlag;

				//创建订单时 只根据共用库存表查询是否有共用库存
				List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(contactGroupPuStockReadService.findPuIdListByPuId(item.getPuId()));

				List<CommodityProductUnitDTO> commodityProductUnitDTOs = null;
				if (puIds != null && puIds.size() > 1) {
					logger.info("当前pu:{},共用库存puIds:{},{}", item.getPuId(), puIds.size(), puIds);
					commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIds), commodityProductUnitDTO2.getSkuId());
				}

				if (commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 1) {
					freezeFlag = stockWriteService.freezeStockBatchWithTx(new FreeseOrderStockBatchDTO(item.getPuId(), item.getPuCount(),
							sodto.getOrderCode(), StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(), userId, userName,
							ip, mac, puIds, commodityProductUnitDTOs));
				} else {
					// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
					freezeFlag = stockWriteService.freezeStockWithTx(new FreezeStockWithTxDTO(item.getPuId(), item.getPuCount(),
							sodto.getOrderCode(), StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(),
							commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
							userName, ip, mac));
				}


				if (freezeFlag != 1) {
					throw new BusinessException(BusinessExceptionConstant.PRODUCT_INSUFFICIENT, "该商品库存不足");
				}

				if (unitFlag && item.getSkuId() != null) {
					int unitFreezeFlag = unitStockWriteService.freezeStockWithTx(item.getSkuId(), item.getPuCount());
					if (unitFreezeFlag != 1) {
						throw new BusinessException(BusinessExceptionConstant.PRODUCT_INSUFFICIENT, "该商品库存不足");
					}
				}
			}
			//如果不是总店的情况下更新门店pu库存
			if (sodto.getStoreId() != 1L && sodto.getStoreId() != 2L) {
				// 更新门店pu库存信息
				storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(), sodto, soItems));
			}
		}

		// 优惠卷相关处理
		boolean couponFlag = isUseCouponByCreateOrder(couponType, couponUnitId, sodto, soChildDTOList, soItems, userId, deliveryMap);

		// 创建订单,子订单,订单项,订单设备,更改发票,删除购物车,修改优惠卷状态为已冻结
		int i = sodto.getOrderAmount().subtract(sodto.getOrderPromotionDiscount()).compareTo(BigDecimal.ZERO);
		if (i < 0) {
			throw new BusinessException("订单实付金额不能小于0");
		}
		NewSoOrderDTO nso = new NewSoOrderDTO();
		nso.setCartItemIds(cartItemIds);
		nso.setItemList(soItems);


		//计算现金支付最小金额
		BigDecimal orderPayByCashLimit = BigDecimal.valueOf(0);
		BigDecimal compareAmount = sodto.getProductAmount().subtract(BigDecimal.valueOf(orderPayByCash)).subtract(sodto.getOrderPromotionDiscount());
		if (compareAmount.compareTo(BigDecimal.valueOf(0)) >= 0) {
			orderPayByCashLimit = BigDecimal.valueOf(orderPayByCash);
		} else {
			orderPayByCashLimit = sodto.getProductAmount().subtract(sodto.getOrderPromotionDiscount());
		}
		sodto.setLimitCashPayAmount(orderPayByCashLimit);
		nso.setSoDTO(sodto);
		nso.setSoChild(soChildDTOList);
//		nso.setSoChild(sc);
		nso.setSoDevice(sd);
		//nso.setSoThirdpartyDTO(soThirdpartyDTO);
		nso.setSoThirdpartyDTOList(soThirdpartyDTOList);
		if(EmptyUtil.isNotEmpty(limitRuleRecordList)){
			List<LimitRuleRecordDTO> limitBuyRecordList = getLimitBuyRecordList(sodto.getStoreId(),limitRuleRecordList, companyId, sodto.getPlatformId());
			nso.setLimitRuleRecordList(limitBuyRecordList);
		}
		Long orderId = soWriteService.createOrder(nso);
		sodto.setId(orderId);
		if (EmptyUtil.isNotEmpty(orderId) && EmptyUtil.isNotEmpty(sodto.getSaleWay()) && sodto.getSaleWay() == 8) {
			//创建订单成功后,如果是以旧换新补差价则创建记录表
			insertExchangeOrderRecord(userId, exchangeId, exchangeCouponUnitId, exchangeCouponBatchId, sodto.getOrderCode(), sodto.getOrderAmount());
		}

		if (couponFlag) {
			// 使用优惠卷成功,冻结已使用的优惠卷
			CouponUnitDTO couponUnit = new CouponUnitDTO();
			couponUnit.setId(couponUnitId);
			couponUnit.setCouponUnitStatus(couponType == 0 ? 2 : 1);
			couponUnit.setOrderId(orderId);
			couponUnit.setUsedTime(new Date());
			couponUnit.setUsedCount(1);
			couponUnitWriteService.updateCouponUnitWithTx(couponUnit);
		}
		//对京东订单进行操作
		//String accessToken = jdUtils.getAccessToken(jedisUtil);
		String accessToken = jdUtils2.getAccessToken(jedisUtil);
		for(SoChildDTO soChildDTO:soChildDTOList){
			logger.info("进入校验");
			Long performingParty = soChildDTO.getPerformingParty();
			if(performingParty.equals(ThirdConst.Merchant.JD)){
				SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
				logger.info("对京东订单进行京东下单->"+ JSON.toJSONString(soChildDTO));
				String parseAddress = jdUtils.parseAddress(addr.getGoodReceiverCountry()+addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverAddress(), accessToken);
				JSONObject jsonObject = JSONObject.parseObject(parseAddress);
				ParseAddressJson addressJson = JSON.parseObject(jsonObject.getString("result"), ParseAddressJson.class);
				//根据公司配置是否允许京东下单(true-允许,false-禁止);
				boolean jdOrderAllow=jdOrderAllow(companyId,configs);
				if((SpringContextTool.isPrd()&&jdOrderAllow) || jdUtils2.TEST_TO_PRO.equals("1")) {
					String json = jdUtils.jdOrderSubmit(accessToken, soChildDTO.getChildCode(),
							JSON.toJSONString(soChildDTO.getSkuInfoList()), addr.getGoodReceiverName(), addressJson.getProvinceId(), addressJson.getCityId(),
							addressJson.getCountyId(), addressJson.getTownId(), addr.getGoodReceiverAddress(), null,
							null, addr.getGoodReceiverMobile(), addr.getGoodReceiverMobile(), null,
							Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(5),
							"大厨管家（上海)网络科技有限公司", Integer.valueOf(1), Integer.valueOf(4),
							Integer.valueOf(1), Integer.valueOf(0), null,
							"18616833602", null, null,
							null, null, null,
							null, null, null, null,
							null, null, null,
							null, null, null,
							null, null,
							null, null, null);

					JdResponse jdResponse = JSON.parseObject(json, JdResponse.class);
					if(jdResponse.isSuccess()&&jdResponse.getResultCode().equals("0001")){
						//京东下单成功
							JdOrderSubmit jdOrderSubmit = JSON.parseObject(jdResponse.getResult(), JdOrderSubmit.class);
						Long jdOrderId = jdOrderSubmit.getJdOrderId();
						soChildDTO.setThirdpartySoChildId(jdOrderId);
						//更新京东运费价格
						BigDecimal oldFree =EmptyUtil.isNotEmpty(soChildDTO1.getOrdinaryDeliveryFee())?soChildDTO1.getOrdinaryDeliveryFee():BigDecimal.ZERO;
						JSONObject jdOrderDetail = jdUtils.getJdOrderDetail(accessToken, jdOrderId.toString());
						if(EmptyUtil.isNotEmpty(jdOrderDetail)){
							logger.info("正式环境--对京东订单进行京东下单成功->"+ JSON.toJSONString(jdOrderDetail));
							BigDecimal freight = jdOrderDetail.getBigDecimal("freight");
							BigDecimal orderAmount= jdOrderDetail.getBigDecimal("orderPrice");
							soChildDTO.setDeliveryFee(freight);
							soChildDTO.setOrdinaryDeliveryFee(freight);
							soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
							soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
							soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
							BigDecimal addFree = freight.subtract(oldFree);
							sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
							sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
							sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
							sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
							sodto.setId(orderId);
							soWriteService.updateOrderWithTX(sodto);
							/*soThirdpartyDTO.setThirdpartyId(jdOrderId+"");
							soThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
							soThirdpartyDTO.setThirdpartyPayTime(new Date());
							soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(soThirdpartyDTO);*/
							SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
							if(oldSoThirdpartyDTO !=null){
								SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
								editSoThirdpartyDTO.setThirdpartyId(jdOrderId+"");
								editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
								editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
								editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
								soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);

							}
						}
					}else{
						//京东下单失败
						cancelOrderJd(sodto,userId,ip,userName,mac,req);
						throw new BusinessException("订单下单失败："+jdResponse.getResultMessage());
					}
				}else {
					//京东下单成功
					logger.info("测试环境--对京东订单进行京东下单成功->");
					Long jdOrderId = 1000000000l+RandomUtil.randomId();
					soChildDTO.setThirdpartySoChildId(jdOrderId);
					//更新京东运费价格
					BigDecimal oldFree = EmptyUtil.isNotEmpty(soChildDTO1.getOrdinaryDeliveryFee())?soChildDTO1.getOrdinaryDeliveryFee():BigDecimal.ZERO;
					//soChildDTO.setDeliveryFee(soChildDTO.getDeliveryFee());
					soChildDTO.setDeliveryFee(soChildDTO1.getDeliveryFee());
					soChildDTO.setOrdinaryDeliveryFee(oldFree);

					soChildDTO.setThirdpartySoChildPayAmount(soChildDTO.getAmount());
					soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
					soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
					BigDecimal addFree = oldFree.subtract(oldFree);
					sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
					sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
					sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
					sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
					sodto.setId(orderId);
					soWriteService.updateOrderWithTX(sodto);
					SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
					if(oldSoThirdpartyDTO !=null){
						SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
						editSoThirdpartyDTO.setThirdpartyId(jdOrderId+"");
						editSoThirdpartyDTO.setThirdpartyPayAmount(soChildDTO.getAmount());
						editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
						editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
						soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);

					}
				}
			}else if(performingParty.equals(ThirdConst.Merchant.CAKE)){
				SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
				try {
					String reqData = buildCakeOrderParam(orderId,soChildDTO,addr,null,null);
					JSONObject submitOrderResult = cakeUtil.submitOrder(reqData);
					logger.info("母订单id:{},子订单code:{}提交蛋糕叔叔订单请求结果:{}",orderId,soChildDTO.getChildCode(),submitOrderResult !=null?JSON.toJSONString(submitOrderResult):"响应为空");
					//若是订单取消会抛出异常，不用担心往下继续执行订单提交成功的流程
					checkCakeOrCancelOrder(req, sodto, userId, userName, ip, mac, submitOrderResult);
					//若未取消则订单提交成功
					submitCakeOrderSuccess(sodto, null, orderId, soChildDTO1, submitOrderResult);
				}catch (Exception e){
					cancelOrderJd(sodto, userId, ip, userName, mac, req);
					e.printStackTrace();
					logger.info("订单{},子订单号:{}推送蛋糕叔叔时异常:{}",sodto.getOrderCode(),soChildDTO.getChildCode(),e);
					throw new BusinessException("提交订单至蛋糕叔叔时异常");
				}

			}else if (Objects.equals(performingParty, ThirdConst.Merchant.WORLD)){
				SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
				try {
					JSONObject jsonObject  = buildWorldOrderParam(orderId,soChildDTO1,addr,null,null,userId);
					logger.info("提交全球购订单请求参数:{}",JSON.toJSONString(jsonObject));
					JSONObject submitOrderResult  = worldBuyOrderManage.createOrder(jsonObject);
					logger.info("提交全球购订单请求结果:{}",submitOrderResult !=null?JSON.toJSONString(submitOrderResult):"响应为空");
					checkWorldOrCancelOrder(req, sodto, userId, userName, ip, mac, submitOrderResult);
					submitWorldOrderSuccess(sodto, null, orderId, soChildDTO1, submitOrderResult);
				}catch (Exception e){
					cancelOrderJd(sodto, userId, ip, userName, mac, req);
					e.printStackTrace();
					logger.info("订单{},子订单号:{}推送订单至全球购时异常:{}",sodto.getOrderCode(),soChildDTO.getChildCode(),e);
					throw new BusinessException("订单创建异常");
				}
			}else if (Objects.equals(performingParty, ThirdConst.Merchant.QM)){
				soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
				soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
				SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
				if(oldSoThirdpartyDTO !=null){
					SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
					editSoThirdpartyDTO.setThirdpartyId(String.valueOf(soChildDTO.getThirdpartySoChildId()));
					editSoThirdpartyDTO.setThirdpartyPayAmount(soChildDTO.getThirdpartySoChildPayAmount());
					editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
					editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
					soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
				}
			}

		}
		return orderId;
	}

	private JSONObject buildWorldOrderParam(Long orderId, SoChildDTO soChildDTO, ReceiverAddressDTO addr,String spuId,String skuId,Long userId) {
		UserDTO userDTO = userReadService.findUserByID(userId);
		String phone = (StringUtils.isEmpty(addr.getGoodReceiverPhone()) || StringUtils.isBlank(addr.getGoodReceiverPhone()))?addr.getGoodReceiverMobile():addr.getGoodReceiverPhone();
		JSONObject rtObj = new JSONObject();
		rtObj.put("orderSn",soChildDTO.getChildCode());
		rtObj.put("buyerPhone",userDTO.getMobile());
		rtObj.put("buyerRealName",userDTO.getName());
		rtObj.put("buyerIdCard",userDTO.getIdCardNo());
		rtObj.put("receiveAddress",addr.getGoodReceiverAddress());
		rtObj.put("receiveName",addr.getGoodReceiverName());
		rtObj.put("recveivePhone",phone);
		rtObj.put("province",addr.getGoodReceiverProvince());
		rtObj.put("city",addr.getGoodReceiverCity());
		rtObj.put("district",addr.getGoodReceiverCounty());
		rtObj.put("orderRemarks",soChildDTO.getRemark());
		rtObj.put("orderSource","大厨管家（上海)网络科技有限公司");
		rtObj.put("isOrderDeclare","0");
		rtObj.put("isPayDeclare","0");
		rtObj.put("discountMoney","0");
		rtObj.put("freight","0");
		JSONArray jsonArray = new JSONArray();
		BigDecimal orderAmount = addGoodsInfoToList(jsonArray,soChildDTO);
		rtObj.put("goods",jsonArray);
		rtObj.put("orderMoney",orderAmount);
		//return  JSON.toJSONString(rtObj);
		return rtObj;
	}

	private BigDecimal addGoodsInfoToList(JSONArray jsonArray,SoChildDTO soChildDTO){
		List<SoItemDTO> soItemDTOS = soItemReadService.findSoItemsBySoChild(soChildDTO.getId());
		BigDecimal totalAmt = BigDecimal.ZERO;
		for (SoItemDTO soItemDTO : soItemDTOS) {
			JSONObject goodObj = new JSONObject();
			ChannelProductDetailVO vo = getChannelProductDetailVO(soItemDTO);
			List<ChannelProductSkuDTO> skuList = vo.getSkuList();
			List<ChannelProductBatchDTO>  batchDTOList = vo.getBatchDTOList();
			ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(soItemDTO.getPuId()+"",batchDTOList);
			ChannelProductSkuDTO skuDTO = productFacade.getCurrSkuInfo(batchDTO.getLinkSkuId(),skuList);
			BigDecimal batchAmount = batchDTO.getPriceSettleMent().multiply(new BigDecimal(soItemDTO.getPuCount())).setScale(2);
			totalAmt = totalAmt.add(batchAmount);
			goodObj.put("goodsSku",skuDTO.getSkuCode());
			goodObj.put("goodsNum",soItemDTO.getPuCount());
			goodObj.put("goodsBatchNo",batchDTO.getBatchNo());
			goodObj.put("goodsSpecNum",batchDTO.getSpecNum());
			goodObj.put("goodsPrice", batchDTO.getPriceSettleMent());
			goodObj.put("startExpDate","");
			goodObj.put("endExpDate","");
			jsonArray.add(goodObj);
		}

		return totalAmt;
	}

	private ChannelProductDetailVO getChannelProductDetailVO(SoItemDTO soItemDTO){
		if(EmptyUtil.isNotEmpty(soItemDTO.getSnapshot())){
			return JSONObject.parseObject(soItemDTO.getSnapshot(),ChannelProductDetailVO.class);
		}
		return productFacade.findWorldProduct(soItemDTO.getExternalProductId(),soItemDTO.getPuId()+"");
	}




	//京东下单失败,取消订单
	private void cancelOrderJd(SoDTO order,Long userId, String ip,
							   String userName, String mac, HttpServletRequest req){
		//京东下单失败
		// 生成唯一退款单编号(取消已付款订单需要,取消待付款不需要)
		String soRefundCodeByCash = null;
		String soRefundCodeByFubi = null;
		String soRefundCodeByJidian = null;
		String soRefundCodeByBuyCard = null;
		if (order.getOrderStatus() == OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
			List<String> soRefundNOList = genSoRefundNO();
			soRefundCodeByCash = soRefundNOList.get(0);
			soRefundCodeByFubi = soRefundNOList.get(1);
			soRefundCodeByJidian = soRefundNOList.get(2);
			soRefundCodeByBuyCard = soRefundNOList.get(3);
		}
		cancelOrderWithTx(order,ip,userId,userName,mac,soRefundCodeByFubi,soRefundCodeByCash,soRefundCodeByJidian,soRefundCodeByBuyCard,req);
	}
	private List<LimitRuleRecordDTO> getLimitBuyRecordList(Long storeId, List<LimitRuleRecordDTO> goodsList, Long companyId, Long platformId){
		Map<Long, List<LimitRuleRecordDTO>> map = new HashMap<>();
		for (LimitRuleRecordDTO limitRuleRecordDTO : goodsList) {
			if(map.containsKey(limitRuleRecordDTO.getStandardUnitId())){
				List<LimitRuleRecordDTO> list = map.get(limitRuleRecordDTO.getStandardUnitId());
				list.add(limitRuleRecordDTO);
				map.put(limitRuleRecordDTO.getStandardUnitId(), list);
			}else{
				List<LimitRuleRecordDTO> list = new ArrayList<>();
				list.add(limitRuleRecordDTO);
				map.put(limitRuleRecordDTO.getStandardUnitId(), list);
			}
		}
		// 把需要添加的限购记录保存到集合
		List<LimitRuleRecordDTO> limitRuleRecords = new ArrayList<>();
		for (Map.Entry<Long, List<LimitRuleRecordDTO>> entry : map.entrySet()) {
			// 根据su商品id查询所有启用限购规则信息
			List<Long> suCombinationIdList = com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.querySuCombinationBySuId(entry.getKey()));
			List<LimitRuleDTO> list = limitRuleReadService.startLimitRuleDTOByStandardUnitId(entry.getKey(), suCombinationIdList, platformId);

			// 获取当前su商品的总数量和总金额
			List<LimitRuleRecordDTO> value = entry.getValue();
			int num = 0;
			double priceSum = 0;
			for (LimitRuleRecordDTO limitRuleRecordPO : value) {
				num = num + limitRuleRecordPO.getBuySum().intValue();
				priceSum = priceSum + (limitRuleRecordPO.getBuySum().doubleValue() * limitRuleRecordPO.getBuyMoneySum().doubleValue());
			}

			for (LimitRuleDTO limitRulePO : list) {
				// 添加su商品每个限购规则的限购记录
				for (LimitRuleRecordDTO limitRuleRecordPO : value) {
					LimitRuleRecordDTO po = new LimitRuleRecordDTO();
					po.setStandardUnitId(limitRuleRecordPO.getStandardUnitId());
					po.setStandardUnitSerialNumber(limitRuleRecordPO.getStandardUnitSerialNumber());
					po.setProductUnitId(limitRuleRecordPO.getProductUnitId());
					po.setProductUnitSerialNumber(limitRuleRecordPO.getProductUnitSerialNumber());
					po.setBuySum(limitRuleRecordPO.getBuySum());
					po.setBuyMoneySum(limitRuleRecordPO.getBuyMoneySum());
					po.setCreateUserid(limitRuleRecordPO.getCreateUserid());
					po.setCreateUsername(limitRuleRecordPO.getCreateUsername());
					po.setCreateUserMobile(limitRuleRecordPO.getCreateUserMobile());
					po.setLimitRuleId(limitRulePO.getId());
					po.setLimitRuleName(limitRulePO.getName());
					po.setLimitRuleSerialNumber(limitRulePO.getSerialNumber());
//						po.setLimitType(limitRulePO.getLimitType());
					po.setBuySum(Long.valueOf(num));
					po.setBuyMoneySum(BigDecimal.valueOf(priceSum));
					po.setPlatformId(platformId);
					po.setCompanyId(companyId);
					po.setStoreId(storeId);
					limitRuleRecords.add(po);
				}
			}


		}

		return limitRuleRecords;


	}
	//记录以旧换新
	private void insertExchangeOrderRecord(Long userId, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, String orderCode, BigDecimal orderAmount){
		//查询用户
		UserDTO userDTO = userReadService.findUserByID(userId);
		//查询旧优惠券unit
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(exchangeCouponUnitId);
		CouponUnitDTO oldCouponUnit = couponUnitReadService.findCouponUnitById(couponUnitDTO);
		//查询旧优惠券
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setId(oldCouponUnit.getCouponId());
		CouponDTO oldCoupon = couponReadService.findCouponById(couponDTO);
		//查询旧批次
		CouponBatchDTO batchDTO = new CouponBatchDTO();
		batchDTO.setId(oldCouponUnit.getCouponBatchId());
		CouponBatchDTO oldCouponBatch = couponBatchReadService.findCouponBatchById(batchDTO);
		//查询新优惠券批次
		batchDTO.setId(exchangeCouponBatchId);
		CouponBatchDTO newCouponBatch = couponBatchReadService.findCouponBatchById(batchDTO);
		if(EmptyUtil.isEmpty(oldCouponUnit)||EmptyUtil.isEmpty(newCouponBatch)||EmptyUtil.isEmpty(oldCouponBatch)||EmptyUtil.isEmpty(oldCoupon)){
			throw new BusinessException("[创建订单后记录依旧换新]新旧优惠券/unit/优惠券批次不存在");
		}
		//查询以旧换新活动
		ExchangeActivityDTO exchangeActivityDTO = new ExchangeActivityDTO();
		exchangeActivityDTO.setId(exchangeId);
		ExchangeActivityDTO activityDTO = exchangeActivityReadService.findExchangeActivityById(exchangeActivityDTO);
		if(EmptyUtil.isEmpty(activityDTO)){
			throw new BusinessException("[创建订单后记录依旧换新]活动不存在");
		}
		ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
		recordDTO.setExchangeId(exchangeId);
		recordDTO.setExchangeName(activityDTO.getExchangeName());
		recordDTO.setNewBatchCode(newCouponBatch.getCouponBatchCode());
		recordDTO.setNewCouponType(newCouponBatch.getCouponRelType());
		recordDTO.setOldBatchCode(oldCouponBatch.getCouponBatchCode());
		recordDTO.setOldUnitCode(oldCouponUnit.getCouponUnitCode());
		recordDTO.setOldCouponName(oldCoupon.getTitle());
		recordDTO.setOldCouponType(oldCoupon.getCouponType());
		recordDTO.setOrderCode(orderCode);
		recordDTO.setOrderAmount(orderAmount);
		recordDTO.setPlatformId(oldCouponBatch.getPlatformId());
		recordDTO.setExchangeTime(new Date());
		recordDTO.setUserMail(userDTO.getMail());
		recordDTO.setOldUnitStatus(oldCouponUnit.getCouponUnitStatus());
		exchangeOrderRecordWriteService.insertExchangeOrderRecordWithTx(recordDTO);
	}




	private Long getStoreDiscount(Long storeId){
		if(EmptyUtil.isEmpty(storeId)){
			throw new BusinessException("storeId不能为空");
		}
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(storeId);
		StoreDTO storeById = storeReadService.findStoreById(storeDTO);
		//没有优惠
		if(storeById.getDiscount()==null){
			StoreTreeNodeDTO dto = new StoreTreeNodeDTO();
			dto.setStoreId(storeId);
			List<StoreTreeNodeDTO> storeTreeNodeAll = storeTreeNodeReadService.findStoreTreeNodeAll(dto);
			if(storeTreeNodeAll.size()==0||storeTreeNodeAll==null){
				throw new BusinessException(storeId + "是总门店,但没有折扣率,请联系管理员");
			}else if(storeTreeNodeAll.size()>1){
				throw new BusinessException(storeId + "同时属于多个门店,配置有误,联系管理元");
			}else{
				this.getStoreDiscount(storeTreeNodeAll.get(0).getParentId());
			}


		}

		return storeById.getDiscount();
	}

	/**
	 * 没有优惠券时，计算各项金额
	 * @param sodto
	 * @param soChildDTOList
	 * @param soItems
	 * @param deliveryMap
	 * @param storeDis
	 */
	private void calculateOrderWithoutCoupon(SoDTO sodto, List<SoChildDTO> soChildDTOList,
			List<SoItemDTO> soItems, Map<Long, BigDecimal> deliveryMap, BigDecimal storeDis) {
		//门店优惠
		sodto.setStoreDiscount(sodto.getProductAmount().subtract(sodto.getProductAmount().multiply(storeDis)).setScale(2,BigDecimal.ROUND_DOWN));
		//运费优惠
		sodto.setDeliveryFeeDiscount(new BigDecimal(0));
		//优惠券优惠
		sodto.setCouponDiscount(new BigDecimal(0));
		//订单总优惠
		sodto.setOrderPromotionDiscount(sodto.getOrderPromotionDiscount().add(sodto.getStoreDiscount()));
		//订单结算金额(实付总额)
		sodto.setOrderAmountPay(sodto.getOrderAmount().subtract(sodto.getOrderPromotionDiscount()));

		Map<String, Long> childMerchantMap = new HashMap<>();
		Map<Long, BigDecimal> merchantAmountMap = new HashMap<>();
		Map<String, BigDecimal> childDevlieryMap = new HashMap<>();
		Map<String, BigDecimal> childAmountMap = new HashMap<>();

		for (SoChildDTO soChildDTO : soChildDTOList) {
			if (Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.QM) || Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.CAKE)){
				childDevlieryMap.put(soChildDTO.getChildCode(),soChildDTO.getDeliveryFee());
				childAmountMap.put(soChildDTO.getChildCode(),soChildDTO.getAmount());
			}else {
				//子订单结算金额，计算方式：子订单商品原价总金额-所有优惠金额（优惠券、门店折扣）(不含运费)
				soChildDTO.setAmount(soChildDTO.getProductAmount().multiply(storeDis));
				//设置门店优惠
				soChildDTO.setStoreDiscount(soChildDTO.getProductAmount().subtract(soChildDTO.getAmount()));
			}

			//运费优惠为0
			soChildDTO.setDeliveryFeeDiscount(BigDecimal.valueOf(0));
			//优惠券优惠为0
			soChildDTO.setCouponDiscount(BigDecimal.valueOf(0));


			childMerchantMap.put(soChildDTO.getChildCode(), soChildDTO.getPerformingParty());
			Long merchantId = soChildDTO.getPerformingParty();
			if (merchantAmountMap.containsKey(merchantId)) {
				BigDecimal merchantAmount = merchantAmountMap.get(merchantId);
				merchantAmount = merchantAmount.add(soChildDTO.getAmount());
				merchantAmountMap.put(merchantId, merchantAmount);
			} else {
				merchantAmountMap.put(merchantId, soChildDTO.getAmount());
			}
		}
		for (SoChildDTO soChildDTO : soChildDTOList) {
			if(Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.CAKE)){
				if(soChildDTO.getDeliveryFee() ==null){
					soChildDTO.setDeliveryFee(new BigDecimal("0"));
				}
				continue;
			}
			if(Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.WORLD)){
				soChildDTO.setDeliveryFee(new BigDecimal("0"));
				continue;
			}
			BigDecimal merchantdeliverFee = deliveryMap.get(soChildDTO.getPerformingParty());
			BigDecimal merchantAmount = merchantAmountMap.get(soChildDTO.getPerformingParty());
			//判断结算金额是否为0
			int amount = merchantAmount.compareTo(BigDecimal.ZERO);
			if (amount == 0 ) {
				soChildDTO.setDeliveryFee(new BigDecimal("0"));
			}else if (Objects.nonNull(merchantdeliverFee)){
				soChildDTO.setDeliveryFee(merchantdeliverFee.multiply(soChildDTO.getAmount()).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN));
			}
		}
		Map<String,List<SoItemDTO>> itemMap =new HashMap<>();
		soItems.forEach(item->{
			List<SoItemDTO> list=itemMap.getOrDefault(item.getChildCode(),new ArrayList<>());
			list.add(item);
			if (!itemMap.containsKey(item.getChildCode())){
				itemMap.put(item.getChildCode(),list);
			}
		});
		for (List<SoItemDTO> soItemList:itemMap.values()){
			BigDecimal splitSumDeliverFeeAver=BigDecimal.ZERO;
			for (int i=0;i<soItemList.size();i++) {
				SoItemDTO soItem =soItemList.get(i);
				//单个商品优惠后金额（扣除优惠券、门店优惠，不含运费）
				soItem.setAfterDiscountPriceAver(soItem.getPrice().multiply(storeDis));
				//单个商品门店优惠平摊优惠金额
				soItem.setStoreDiscountAver(soItem.getPrice().subtract(soItem.getAfterDiscountPriceAver()));
				//单个商品优惠券平摊优惠金额
				soItem.setPromotionAver(new BigDecimal(0));
				//单个商品运费优惠平摊优惠金额
				soItem.setDeliveryFeeDiscountAver(new BigDecimal(0));

				Long mId = childMerchantMap.get(soItem.getChildCode());
				BigDecimal merchantdeliverFee = (soItem.isQM() || soItem.isCake())?childDevlieryMap.get(soItem.getChildCode()):deliveryMap.get(mId);
				BigDecimal merchantAmount = (soItem.isQM() || soItem.isCake())?childAmountMap.get(soItem.getChildCode()):merchantAmountMap.get(mId);
				BigDecimal deliveryFeeAver=BigDecimal.ZERO;
				if (merchantdeliverFee == null || merchantdeliverFee.compareTo(new BigDecimal(0)) < 1 || merchantAmount.compareTo(new BigDecimal(0)) < 1) {
					deliveryFeeAver=BigDecimal.ZERO;
				} else {
					if (i==soItemList.size()-1){
						deliveryFeeAver=merchantdeliverFee.subtract(splitSumDeliverFeeAver);
					}else {
						deliveryFeeAver=merchantdeliverFee.multiply(soItem.getAfterDiscountPriceAver())
								.multiply(new BigDecimal(soItem.getPuCount())).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN);
					}
				}
				soItem.setDeliveryFeeAver(deliveryFeeAver);
				splitSumDeliverFeeAver=splitSumDeliverFeeAver.add(deliveryFeeAver);
			}
		}
	}

	/**
	 * 单个商品触发优惠券时，计算各项金额
	 * @param sodto
	 * @param soChildDTOList
	 * @param soItems
	 * @param deliveryMap
	 * @param storeDis
	 * @param couponUnit
	 * @param couponSoItem
	 */
	private void calculateOrderWithSuCoupon(SoDTO sodto, List<SoChildDTO> soChildDTOList,
			List<SoItemDTO> soItems, Map<Long, BigDecimal> deliveryMap, BigDecimal storeDis, CouponUnitDTO couponUnit, SoItemDTO couponSoItem) {
		//门店优惠
		sodto.setStoreDiscount(sodto.getProductAmount().subtract(couponUnit.getDiscountAmount()).multiply(BigDecimal.valueOf(1).subtract(storeDis)).setScale(2,BigDecimal.ROUND_DOWN));
		//运费优惠
		sodto.setDeliveryFeeDiscount(new BigDecimal(0));
		//优惠券优惠
		sodto.setCouponDiscount(couponUnit.getDiscountAmount());
		//订单总优惠
		sodto.setOrderPromotionDiscount(sodto.getCouponDiscount().add(sodto.getStoreDiscount()));
		//订单结算金额(实付总额)
		sodto.setOrderAmountPay(sodto.getOrderAmount().subtract(couponUnit.getDiscountAmount()).subtract(sodto.getStoreDiscount()));

		Map<String, Long> childMerchantMap = new HashMap<>();
		Map<Long, BigDecimal> merchantAmountMap = new HashMap<>();
		for (SoChildDTO soChildDTO : soChildDTOList) {
			BigDecimal couponDiscount = new BigDecimal(0);
			if (couponSoItem.getChildCode().equals(soChildDTO.getChildCode())) {
				couponDiscount = couponUnit.getDiscountAmount();
			}
			//子订单结算金额，计算方式：子订单商品原价总金额-所有优惠金额（优惠券、门店折扣）(不含运费)
			soChildDTO.setAmount(soChildDTO.getProductAmount().subtract(couponDiscount).multiply(storeDis));
			//运费优惠为0
			soChildDTO.setDeliveryFeeDiscount(BigDecimal.valueOf(0));
			//优惠券优惠
			soChildDTO.setCouponDiscount(couponDiscount);
			//设置门店优惠
			soChildDTO.setStoreDiscount(soChildDTO.getProductAmount().subtract(soChildDTO.getAmount()).subtract(couponDiscount));

			childMerchantMap.put(soChildDTO.getChildCode(), soChildDTO.getPerformingParty());
			Long merchantId = soChildDTO.getPerformingParty();
			if (merchantAmountMap.containsKey(merchantId)) {
				BigDecimal merchantAmount = merchantAmountMap.get(merchantId);
				merchantAmount = merchantAmount.add(soChildDTO.getAmount());
				merchantAmountMap.put(merchantId, merchantAmount);
			} else {
				merchantAmountMap.put(merchantId, soChildDTO.getAmount());
			}
		}
		for (SoChildDTO soChildDTO : soChildDTOList) {
			BigDecimal merchantdeliverFee = deliveryMap.get(soChildDTO.getPerformingParty());
			BigDecimal merchantAmount = merchantAmountMap.get(soChildDTO.getPerformingParty());

			//判断结算金额是否为0
			int amount = merchantAmount.compareTo(BigDecimal.ZERO);
			if (amount == 0 ) {
				soChildDTO.setDeliveryFee(new BigDecimal("0"));
			}else {
				soChildDTO.setDeliveryFee(merchantdeliverFee.multiply(soChildDTO.getAmount()).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN));
			}
		}
		Map<String,List<SoItemDTO>> itemMap =new HashMap<>();
		soItems.forEach(item->{
			List<SoItemDTO> list=itemMap.getOrDefault(item.getChildCode(),new ArrayList<>());
			list.add(item);
			if (!itemMap.containsKey(item.getChildCode())){
				itemMap.put(item.getChildCode(),list);
			}
		});
		for (List<SoItemDTO> soItemList:itemMap.values()){
			BigDecimal splitSumDeliverFeeAver=BigDecimal.ZERO;
			for (int i=0;i<soItemList.size();i++) {
				SoItemDTO soItem =soItemList.get(i);
				BigDecimal couponDiscount = new BigDecimal(0);
				if (couponSoItem.getPuId().equals(soItem.getPuId())) {
					couponDiscount = couponUnit.getDiscountAmount();
				}
				//单个商品优惠券平摊优惠金额
				soItem.setPromotionAver(couponDiscount.divide(BigDecimal.valueOf(soItem.getPuCount()), 2, BigDecimal.ROUND_DOWN));
				//单个商品优惠券总优惠金额
				soItem.setFinalPromotionAver(couponDiscount);
				//单个商品优惠后金额（扣除优惠券、门店优惠，不含运费）
				soItem.setAfterDiscountPriceAver(soItem.getPrice().subtract(soItem.getPromotionAver()).multiply(storeDis));
				//单个商品门店优惠平摊优惠金额
				soItem.setStoreDiscountAver(soItem.getPrice().subtract(soItem.getAfterDiscountPriceAver()).subtract(soItem.getPromotionAver()));
				//单个商品运费优惠平摊优惠金额
				soItem.setDeliveryFeeDiscountAver(new BigDecimal(0));

				Long mId = childMerchantMap.get(soItem.getChildCode());
				BigDecimal merchantdeliverFee = deliveryMap.get(mId);
				BigDecimal merchantAmount = merchantAmountMap.get(mId);
				BigDecimal deliveryFeeAver=BigDecimal.ZERO;
				if (merchantdeliverFee == null || merchantdeliverFee.compareTo(new BigDecimal(0)) < 1 || merchantAmount.compareTo(new BigDecimal(0)) < 1) {
					deliveryFeeAver=BigDecimal.ZERO;
				} else {
					if (i==soItemList.size()-1){
						deliveryFeeAver=merchantdeliverFee.subtract(splitSumDeliverFeeAver);
					}else {
						deliveryFeeAver=merchantdeliverFee.multiply(soItem.getAfterDiscountPriceAver()).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN);
					}
				}
				soItem.setDeliveryFeeAver(deliveryFeeAver);
				splitSumDeliverFeeAver=splitSumDeliverFeeAver.add(deliveryFeeAver);
			}
		}
	}

	private void calculateOrderWithSuCombCoupon(SoDTO sodto, List<SoChildDTO> soChildDTOList,
			List<SoItemDTO> soItems, Map<Long, BigDecimal> deliveryMap, BigDecimal storeDis,
			CouponUnitDTO couponUnit, List<SoItemDTO> couponSoItemList, BigDecimal triggerTotalAmount) {
		//门店优惠
		sodto.setStoreDiscount(sodto.getProductAmount().subtract(couponUnit.getDiscountAmount()).multiply(BigDecimal.valueOf(1).subtract(storeDis)).setScale(2,BigDecimal.ROUND_DOWN));
		//运费优惠
		sodto.setDeliveryFeeDiscount(new BigDecimal(0));
		//优惠券优惠
		sodto.setCouponDiscount(couponUnit.getDiscountAmount());
		//订单总优惠
		sodto.setOrderPromotionDiscount(sodto.getCouponDiscount().add(sodto.getStoreDiscount()));
		//订单结算金额(实付总额)
		sodto.setOrderAmountPay(sodto.getOrderAmount().subtract(couponUnit.getDiscountAmount()).subtract(sodto.getStoreDiscount()));

		boolean isFirst = true;
		BigDecimal sumPromotionAver = new BigDecimal(0);
		for(SoItemDTO couponSoItem : couponSoItemList) {
			if (isFirst) {
				isFirst = false;
				continue;
			}
			//总优惠/总金额*该pu的总金额
			BigDecimal finalPromotionAver = couponUnit.getDiscountAmount().multiply(couponSoItem.getPrice().multiply(BigDecimal.valueOf(couponSoItem.getPuCount()))).divide(triggerTotalAmount,2,BigDecimal.ROUND_DOWN);
			BigDecimal promotionAver = couponUnit.getDiscountAmount().multiply(couponSoItem.getPrice()).divide(triggerTotalAmount,2,BigDecimal.ROUND_DOWN);
			couponSoItem.setPromotionAver(finalPromotionAver);
			couponSoItem.setFinalPromotionAver(promotionAver);
			sumPromotionAver.add(finalPromotionAver);
		}
		//设置最后一个soitem的平均优惠金额(防止平均优惠时出现金额精度缺失)
		SoItemDTO finalSoItemDTO =couponSoItemList.get(0);
		finalSoItemDTO.setFinalPromotionAver(couponUnit.getDiscountAmount().subtract(sumPromotionAver));
		finalSoItemDTO.setPromotionAver(couponUnit.getDiscountAmount().subtract(sumPromotionAver).divide(BigDecimal.valueOf(finalSoItemDTO.getPuCount()), 2, BigDecimal.ROUND_DOWN));

		Map<String, Long> childMerchantMap = new HashMap<>();
		Map<Long, BigDecimal> merchantAmountMap = new HashMap<>();
		for (SoChildDTO soChildDTO : soChildDTOList) {
			BigDecimal childCouponDiscount = new BigDecimal(0);
			for (SoItemDTO couponSoItem : couponSoItemList) {
				if (soChildDTO.getChildCode().equals(couponSoItem.getChildCode())) {
					childCouponDiscount = childCouponDiscount.add(couponSoItem.getFinalPromotionAver());
				}
			}
			//子订单结算金额，计算方式：子订单商品原价总金额-所有优惠金额（优惠券、门店折扣）(不含运费)
			soChildDTO.setAmount(soChildDTO.getProductAmount().subtract(childCouponDiscount).multiply(storeDis));
			//运费优惠为0
			soChildDTO.setDeliveryFeeDiscount(BigDecimal.valueOf(0));
			//优惠券优惠
			soChildDTO.setCouponDiscount(childCouponDiscount);
			//设置门店优惠
			soChildDTO.setStoreDiscount(soChildDTO.getProductAmount().subtract(soChildDTO.getAmount()).subtract(childCouponDiscount));

			childMerchantMap.put(soChildDTO.getChildCode(), soChildDTO.getPerformingParty());
			Long merchantId = soChildDTO.getPerformingParty();
			if (merchantAmountMap.containsKey(merchantId)) {
				BigDecimal merchantAmount = merchantAmountMap.get(merchantId);
				merchantAmount = merchantAmount.add(soChildDTO.getAmount());
				merchantAmountMap.put(merchantId, merchantAmount);
			} else {
				merchantAmountMap.put(merchantId, soChildDTO.getAmount());
			}
		}

		for (SoChildDTO soChildDTO : soChildDTOList) {
			BigDecimal merchantdeliverFee = deliveryMap.get(soChildDTO.getPerformingParty());
			BigDecimal merchantAmount = merchantAmountMap.get(soChildDTO.getPerformingParty());

			//判断结算金额是否为0
			int amount = merchantAmount.compareTo(BigDecimal.ZERO);
			if (amount == 0 ) {
				soChildDTO.setDeliveryFee(new BigDecimal("0"));
			}else {
				soChildDTO.setDeliveryFee(merchantdeliverFee.multiply(soChildDTO.getAmount()).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN));
			}
		}
		Map<String,List<SoItemDTO>> itemMap =new HashMap<>();
		soItems.forEach(item->{
			List<SoItemDTO> list=itemMap.getOrDefault(item.getChildCode(),new ArrayList<>());
			list.add(item);
			if (!itemMap.containsKey(item.getChildCode())){
				itemMap.put(item.getChildCode(),list);
			}
		});
		for (List<SoItemDTO> soItemList:itemMap.values()){
			BigDecimal splitSumDeliverFeeAver=BigDecimal.ZERO;
			for (int i=0;i<soItemList.size();i++) {
				SoItemDTO soItem =soItemList.get(i);
				if (soItem.getPromotionAver() == null) {
					soItem.setPromotionAver(new BigDecimal(0));
				}
				if (soItem.getFinalPromotionAver() == null) {
					soItem.setFinalPromotionAver(new BigDecimal(0));
				}
				//单个商品优惠后金额（扣除优惠券、门店优惠，不含运费）
				soItem.setAfterDiscountPriceAver(soItem.getPrice().subtract(soItem.getPromotionAver()).multiply(storeDis));
				//单个商品门店优惠平摊优惠金额
				soItem.setStoreDiscountAver(soItem.getPrice().subtract(soItem.getAfterDiscountPriceAver()).subtract(soItem.getPromotionAver()));
				//单个商品运费优惠平摊优惠金额
				soItem.setDeliveryFeeDiscountAver(new BigDecimal(0));

				Long mId = childMerchantMap.get(soItem.getChildCode());
				BigDecimal merchantdeliverFee = deliveryMap.get(mId);
				BigDecimal merchantAmount = merchantAmountMap.get(mId);
				BigDecimal deliveryFeeAver=BigDecimal.ZERO;
				if (merchantdeliverFee == null || merchantdeliverFee.compareTo(new BigDecimal(0)) < 1 || merchantAmount.compareTo(new BigDecimal(0)) < 1) {
					deliveryFeeAver=BigDecimal.ZERO;
				} else {
					if (i==soItemList.size()-1){
						deliveryFeeAver=merchantdeliverFee.subtract(splitSumDeliverFeeAver);
					}else {
						deliveryFeeAver=merchantdeliverFee.multiply(soItem.getAfterDiscountPriceAver()).divide(merchantAmount, 2, BigDecimal.ROUND_DOWN);
					}
				}
				soItem.setDeliveryFeeAver(deliveryFeeAver);
				splitSumDeliverFeeAver=splitSumDeliverFeeAver.add(deliveryFeeAver);
			}
		}
	}

	/**
	 * 优惠卷在创建订单时的相关操作
	 *
	 * @param couponType
	 * @param couponUnitId
	 * @param sodto
	 * @param soItems
	 * @return
	 */
	private boolean isUseCouponByCreateOrder(Integer couponType, Long couponUnitId, SoDTO sodto, List<SoChildDTO> soChildDTOList,
			List<SoItemDTO> soItems, Long userId, Map<Long, BigDecimal> deliveryMap) {
		//根据storeId查询门店优惠
		BigDecimal storeDis = BigDecimal.valueOf(getStoreDiscount(sodto.getStoreId())).divide(BigDecimal.valueOf(100));
		if (couponType == null || couponUnitId == null
				|| (couponType != null && couponUnitId != null && couponType == 0 && couponUnitId == 0)) {
			calculateOrderWithoutCoupon(sodto, soChildDTOList, soItems, deliveryMap, storeDis);
			return false;
		}

		// 查询是否存在可用的优惠卷,然后判断当前商品是否在其中
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setCouponType(couponType);
		couponUnitDTO.setUserId(userId);
		couponUnitDTO.setId(couponUnitId);

		List<CouponUnitDTO> couponUnitDTOList = couponUnitReadService.findCouponUnitOfAllByUser(couponUnitDTO);
		if (EmptyUtil.isEmpty(couponUnitDTOList) && couponType.equals(Integer.valueOf(1))) {

			throw new BusinessException(BusinessExceptionConstant.COUPON_INVALID, "该兑换券已失效");
		} else if (EmptyUtil.isEmpty(couponUnitDTOList) && couponType.equals(Integer.valueOf(0))) {

			throw new BusinessException(BusinessExceptionConstant.COUPON_INVALID, "该满减券已失效");
		}
		CouponUnitDTO couponUnitDTO_ = couponUnitDTOList.get(0);

		if (couponType == 0) {
			// 1.满减卷
			if (couponUnitDTO_.getGoodsType() == 0) {
				// 0: 单su
				// 订单中包含优惠券关联的商品同时关联商品的总额达到优惠券的最低消费金额
				for (SoItemDTO soItemDTO : soItems) {

					CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
					commodityProductUnitDTO.setId(soItemDTO.getPuId());
					CommodityProductUnitDTO commodityProductUnitDTO_ = commodityProductUnitReadService
							.findCommodityProductUnitById(commodityProductUnitDTO);

					if (commodityProductUnitDTO_.getStandardUnitId().equals(couponUnitDTO_.getGoodsId())
							&& couponUnitDTO_.getTriggerAmount().doubleValue() <= soItemDTO.getPrice().doubleValue()
									* soItemDTO.getPuCount().doubleValue()) {
						calculateOrderWithSuCoupon(sodto, soChildDTOList, soItems, deliveryMap, storeDis, couponUnitDTO_, soItemDTO);
						return true;
					}
				}

			} else if (couponUnitDTO_.getGoodsType() == 1) {
				// 1:商品组
				List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
						.findByStandardUnitCombinationId(couponUnitDTO_.getGoodsId(), null);

				double totalAmount = 0D;
				List<SoItemDTO> soItemsList = new ArrayList<>();
				double totalDiscount = 0D;
				for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
					// 3.如果在就判断符合商品合计金额是否达到满减的触发金额
					for (SoItemDTO soItemDTO : soItems) {
						CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
						commodityProductUnitDTO.setId(soItemDTO.getPuId());
						CommodityProductUnitDTO commodityProductUnitDTO_ = commodityProductUnitReadService
								.findCommodityProductUnitById(commodityProductUnitDTO);

						if (commodityProductUnitDTO_.getStandardUnitId().equals(standardUnitDTO.getId())) {
							totalAmount += soItemDTO.getPrice().doubleValue() * soItemDTO.getPuCount().doubleValue();
							soItemsList.add(soItemDTO);//
							totalDiscount += soItemDTO.getPrice().doubleValue() * soItemDTO.getPuCount().doubleValue();
						}
					}
				}
				// 符合满减要求
				if (couponUnitDTO_.getTriggerAmount().doubleValue() <= totalAmount) {
					calculateOrderWithSuCombCoupon(sodto, soChildDTOList, soItems, deliveryMap, storeDis, couponUnitDTO_, soItemsList, BigDecimal.valueOf(totalAmount));
					return true;
				}
			}

			calculateOrderWithoutCoupon(sodto, soChildDTOList, soItems, deliveryMap, storeDis);
			return false;
		} else if (couponType == 1) {
			// 2.兑换卷
			// 2.1 同1.1-1.3
			if (soItems.size() != 1 && !soItems.get(0).getPuCount().equals(Integer.valueOf(1)))
				throw new BusinessException("兑换商品唯一,且数量只能是1个");

			SoItemDTO soItemDTO = soItems.get(0);

			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(soItemDTO.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO_ = commodityProductUnitReadService
					.findCommodityProductUnitById(commodityProductUnitDTO);

			// 判断关联商品类型
			if (couponUnitDTO_.getGoodsType() == 0) {
				// 0: 单su
				if (commodityProductUnitDTO_.getStandardUnitId().equals(couponUnitDTO_.getGoodsId())) {
					// 符合兑换要求(兑换卷子订单只会有一个)
					sodto.setCouponUnitId(couponUnitDTO_.getId());
					soChildDTOList.get(0).setCouponId(couponUnitDTO_.getId());
					return dealExchangeOrder(sodto, soChildDTOList.get(0), soItemDTO);

				}
			} else if (couponUnitDTO_.getGoodsType() == 1) {
				// 1:商品组
				List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
						.findByStandardUnitCombinationId(couponUnitDTO_.getGoodsId(), null);

				for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
					// 3.判断商品是否在兑换商品范围内
					if (commodityProductUnitDTO_.getStandardUnitId().equals(standardUnitDTO.getId())) {
						// 符合兑换要求
						sodto.setCouponUnitId(couponUnitDTO_.getId());
						soChildDTOList.get(0).setCouponId(couponUnitDTO_.getId());
						return dealExchangeOrder(sodto, soChildDTOList.get(0), soItemDTO);
					}
				}
			}
		}

		return false;
	}

	/**
	 * 兑换订单的统一操作
	 *
	 * @param sodto
	 * @param sc
	 * @param soItemDTO
	 * @return
	 */
	private boolean dealExchangeOrder(SoDTO sodto, SoChildDTO sc, SoItemDTO soItemDTO) {
		sodto.setOrderDeliveryFee(BigDecimal.ZERO);
		sodto.setOrderAmount(sodto.getProductAmount());
		sodto.setUseFubi(0);
		sodto.setOrderAmountPay(BigDecimal.ZERO);
		//订单的优惠总额
		sodto.setOrderPromotionDiscount(sodto.getProductAmount());
		sodto.setCouponDiscount(sodto.getProductAmount());
		sodto.setStoreDiscount(BigDecimal.ZERO);
		sodto.setDeliveryFeeDiscount(BigDecimal.ZERO);

		// 1.2 设置订单商品的优惠金额(最终优惠金额+平摊优惠金额)
		soItemDTO.setPromotionAver(sodto.getOrderAmount());
		soItemDTO.setFinalPromotionAver(sodto.getOrderAmount());
		// 1.3 设置子订单的优惠金额
		sc.setAmount(BigDecimal.ZERO);
		// 2.2 订单状态,订单支付状态,订单确认状态设置为已付款,已付款,已确认
		/*sodto.setOrderStatus(1);
		sodto.setOrderPayStatus(1);
		sodto.setOrderConfirmStatus(1);*/
		return true;
	}

	public PageResult<SoChildDTO> findAllSOChild(SoChildDTO soChildDTO, Pagination page) {
		return soChildReadService.findSoChildOfPage(soChildDTO, page);
	}

	/**
	 * 根据订单id查询订单
	 *
	 * @param soDTO
	 * @return
	 */
	public SoDTO findSoById(SoDTO soDTO) {
		return soReadService.querySoById(soDTO.getId());
	}

	/**
	 * 根据条件查询子订单的信息和发票信息
	 *
	 * @param soChildId
	 * @param platformId
	 * @return
	 */
	public List<SoChildDTOCondition> findSoChildAndInvoiceByCondition(Long[] soChildId, Long platformId) {

		return soChildReadService.findSoChildByCondition(soChildId, platformId);
	}

	// /**
	// * 根据子订单id关联查询子订单信息
	// *
	// * @param soChildDTO
	// * @return
	// */
	// public SoChildDTOCondition findSochildById(Long id) {
	// SoChildDTO condition = new SoChildDTO();
	// condition.setId(id);
	// return soChildReadService.findSochildById(condition);
	// }

	@Deprecated
	public boolean openOrder(Long sochildId, Map<Long, Integer> puIdAndCountMap, BigDecimal orderPrice,
			Map<Long, BigDecimal> puIdAndPriceMap, Integer orChangeReceiveInfo, ReceiverAddressDTO receiverAddressDTO) {
		return soChildWriteService.openOrder(sochildId, puIdAndCountMap, orderPrice, puIdAndPriceMap,
				orChangeReceiveInfo, receiverAddressDTO);
	}

	/**
	 * 拆单
	 *
	 * @param insertSoChild
	 *            原子订单id
	 * @param insertSoChild
	 *            新子订单信息
	 * @param insertItems
	 *            待插入的新订单项
	 * @param updateItems
	 *            待更新的订单项
	 * @param updateSoChild
	 *            已变更的旧子订单总额
	 * @return
	 */
	public Long openOrder(SoChildDTO insertSoChild, List<SoItemDTO> insertItems,
			List<SoItemDTO> updateItems, SoChildDTO updateSoChild) {
		return soChildWriteService.openOrderWithTx(insertSoChild, insertItems, updateItems, updateSoChild);
	}

	/**
	 * 母单分拣
	 *
	 * @param soIdArr
	 * @param platformId
	 * @return
	 */
	public int SoSort(List<Long> soIdArr, Long platformId) {

		return soWriteService.SoSort(soIdArr, platformId);
	}

	/**
	 * 单表查询子订单
	 *
	 * @param soChildId
	 * @return
	 */
	public SoChildDTO findSoChildById(Long soChildId) {
		return soChildReadService.findSoChildById(soChildId);
	}

	/**
	 * 新建收货地址地址 将子订单收获地址id更新为新建的收货地址
	 *
	 * @param ra
	 * @param soChildId
	 */
	public int createAndUpdateSoChildReceiverAddress(ReceiverAddressDTO ra, Long soChildId) {
		Long raId = raWriteService.insertReceiverAddressWithTx(ra);
		SoChildDTO update = new SoChildDTO();
		update.setId(soChildId);
		update.setReceiverAddressId(raId);
		update.setModifyAddressTime(new Date());
		return soChildWriteService.updateSoChildWithTx(update);
	}

	/**
	 * 新增后台创建收件地址
	 * @param ra
	 */
	public void insertReceiverAddressByBackStage(ReceiverAddressDTO ra) {
		raWriteService.insertReceiverAddressWithTx(ra);
	}

	/**
	 * 更新子订单收货地址
	 *
	 * @param raId
	 * @param soChildId
	 * @return
	 */
	public int updateSoChildReceiverAddress(Long raId, Long soChildId) {
		SoChildDTO update = new SoChildDTO();
		update.setId(soChildId);
		update.setReceiverAddressId(raId);
		update.setModifyAddressTime(new Date());
		return soChildWriteService.updateSoChildWithTx(update);
	}


	/**
	 * 根据母订单id查询左右子订单
	 *
	 * @param soId
	 * @return
	 */
	public List<SoChildDTO> querySoChildListBySoId(Long soId) {
		SoChildDTO condition = new SoChildDTO();
		condition.setSoId(soId);
		return soChildReadService.findSoChildAll(condition);
	}

	public List<OrderSortExportVO> querySoDetailOrderExport(List<Long> soIdList) {
		return soChildReadService.soDetailOrderExport(soIdList);
	}

	/**
	 * 查询当前订单下最大的子订单编号+1
	 *
	 * @param soId
	 * @return
	 */
	public String queryMaxChildCodePlus1BySoId(Long soId) {
		return soChildReadService.queryMaxChildCodePlus1BySoId(soId);
	}

	/**
	 * 后台查询订单分页列表
	 * @param orderCode
	 * @param startDateTime
	 * @param endDateTime
	 * @param status
	 * @param confirmStatus
	 * @param payStatus
	 * @param paymentType
	 * @param showTest
	 * @param platformId
	 * @param page
	 * @param refundFlag            @return
	 * */
	public PageResult<SoDTO> queryBackStageSoPage(Long storeId, String orderCode, List<Long> userIds, List<Long> puIds,
												  Date startDateTime, Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus,
												  Integer paymentType, Boolean showTest, Long platformId, Pagination page, boolean refundFlag,
												  List<Long> testCompanyIds,List<Long> companyIds,Integer auditStatus,List<Long> soIds) {

		return soReadService.queryBackStageSoPage(storeId,orderCode, userIds, puIds, startDateTime, endDateTime, status,
				confirmStatus, payStatus, paymentType, showTest, platformId, page, refundFlag, testCompanyIds,
				companyIds,auditStatus,soIds);
	}

	public boolean refundCashWithTx(RefundCashWithTxDTO dto){
		return soService.refundCashWithTx(dto);
	}

	/**
	 * 事务操作订单退款 资金流动,订单退款额变动,(如果订单全额退款)订单状态变动,生成退款单
	 *
	 * @param soDTO
	 * @param reason
	 * @param operatorId
	 */
	public List<String> soRefundWithTx(SoDTO soDTO, String reason, Long operatorId, String soRefundCodeByFubi,
			String soRefundCodeByCash, boolean isCancel,String thirdRefundCode, HttpServletRequest req,String soRefundCodeByJidian,String soRefundCodeByBuyCard) {
		// 退款前检查账户是否异常
//		soService.checkAccountBeforeRefund(soDTO);

		List<String> refundNos=soService.soRefundWithTx(new SoRefundWithTxDTO(soDTO, reason, operatorId, soRefundCodeByFubi, soRefundCodeByCash,
				isCancel, thirdRefundCode,new HttpServletRequestDTO(req),soRefundCodeByJidian,soRefundCodeByBuyCard));

		/***********发送消息*********/
		Map<String, String> params = new HashMap<>();
		params.put("订单编号", soDTO.getOrderCode());
		params.put("积分退款金额", soDTO.getRefundFubi() + "");
		params.put("现金退款金额", soDTO.getRefundCash() + "");
		String cashpayType = null;
		if (soDTO.getCashPayType() != null) {
			if (soDTO.getCashPayType() == 1) {
				cashpayType = "支付宝";
			} else if (soDTO.getCashPayType() == 2) {
				cashpayType = "微信";
			} else if (soDTO.getCashPayType() == 3) {
				cashpayType = "银联";
			} else if (soDTO.getCashPayType() == 4) {
				cashpayType = "建行";
			}
		}
		params.put("现金支付方式", cashpayType);
		Long infoTemplateId;
		Long infoTemplateId1;
		if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(soDTO.getPlatformId())) {
			infoTemplateId = InfoConstant.MYY_BACK_REFUND_CUSTOMER.getStatus();
			infoTemplateId1 = InfoConstant.MYY_BACK_REFUND_OPERATOR.getStatus();
		} else {
			infoTemplateId = InfoConstant.FGJ_BACK_REFUND_CUSTOMER.getStatus();
			infoTemplateId1 = InfoConstant.FGJ_BACK_REFUND_OPERATOR.getStatus();
		}
		sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, soDTO.getUserId(), null));
		sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId1, params, operatorId, null));
		/***********发送消息*********/
        return refundNos;
	}

	/**
	 * 根据订单id查询等待队列
	 *
	 * @param orderId
	 * @return
	 */
	public AwaitQueueDTO queryAwaiteQueueByOrderId(Long orderId) {
		return awaitQueueReadService.queryAwaitQueueByOrderId(orderId);
	}

	/**
	 * 根据订单编号撤销订单支付
	 *
	 * @param aq
	 */
	public boolean revocationOrderPay(AwaitQueueDTO aq, Long platformId) {
		return awaitQueueReadService.revocationOrderPay(aq, platformId);

	}

	/**
	 * 根据id删除等待队列
	 *
	 * @param id
	 */
	public int deleteAwaitQueueById(Long id) {
		AwaitQueueDTO cond = new AwaitQueueDTO();
		cond.setId(id);
		return awaitQueueWriteService.deleteAwaitQueueWithTx(cond);

	}

	/**
	 * 生成原始快照和快照
	 */
	public Map<String, Long> saveSnapsAddress(ReceiverAddressDTO originalSnapAddressDTO,
								 ReceiverAddressDTO snapAddressDTO){
		//TODO
		//插入原始快照
		Long originalSnapAddressId = receiverAddressWriteService.insertReceiverAddressWithTx(originalSnapAddressDTO);
		//插入快照
		Long snapAddressId = receiverAddressWriteService.insertReceiverAddressWithTx(snapAddressDTO);
		Map<String, Long> map = new HashMap<>();
		map.put("originalSnapAddressId",originalSnapAddressId);
		map.put("snapAddressId",snapAddressId);
		return map;
	}

	/**
	 * 把锁定的库存解锁
	 *
	 * @param soItemDTOList
	 * @return
	 */
	public boolean unlockItemsProductUnitStock(List<SoItemDTO> soItemDTOList, Long userId, String userName, String ip,
			String mac) {
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
			commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(Arrays.asList(soDTO.getOrderCode()),StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			List<Long> puIdList = new ArrayList<>();
			Set<Long> puIdSet = new HashSet<>();
			for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
				puIdSet.add(waterDTO.getCommodityProductUnitId());
			}
			puIdList.addAll(puIdSet);

			List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());

			if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
				puIdList.add(soItemDto.getPuId());
				commodityProductUnitDTOs.add(commodityProductUnitDTO2);
				stockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(soItemDto.getPuId(), soItemDto.getPuCount(),
						StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), soDTO.getOrderCode(), userId, userName,
						ip, mac, puIdList, commodityProductUnitDTOs));
			}else {
			// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
			stockWriteService.unfreezeAndDeductStockWithTx(soItemDto.getPuId(), soItemDto.getPuCount(),
					StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),
					commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),
					soDTO.getOrderCode(), userId, userName, ip, mac);
			}


		}
		result = true;
		return result;
	}

	/**
	 * 解冻订单库存(若为unit订单 同时解冻unit库存)
	 *
	 */
	public void unFreeseOrderStock(List<SoItemDTO> items, SoDTO order, Long userId, String userName, String ip,
			String mac) {
		List<SoItemDTO> platformItems = new ArrayList<SoItemDTO>();
		for (SoItemDTO it : items) {
			if(it.isThirdParty()) {

			}else {
				platformItems.add(it);
				Long puId = it.getPuId();
				Integer puCount = it.getPuCount();
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

				List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());

				if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
					puIdList.add(it.getPuId());
					commodityProductUnitDTOs.add(commodityProductUnitDTO2);
					logger.info("解冻订单库存 , puIdList:{},commodityProductUnitDTOs size:{}",puIdList,commodityProductUnitDTOs.size());
					stockWriteService.unFreeseOrderStockBatch(new UnFreeseOrderStockBatchDTO(puId, puCount, order.getOrderCode(),
							StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), userId,
							userName, ip, mac, puIdList, commodityProductUnitDTOs));
				}else {

					stockWriteService.unFreeseOrderStock(puId, puCount, order.getOrderCode(),
							StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),
							commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
							userName, ip, mac);
				}


				// 判断pu是否是unit商品
				boolean isUnit = puReadService.queryIsUnit(puId);
				if (isUnit) {
					// 查询sku信息
					CommodityProductUnitDTO condition = new CommodityProductUnitDTO();
					condition.setId(puId);
					CommodityProductUnitDTO pu = puReadService.findCommodityProductUnitById(condition);
					// 回滚unit冻结库存
					unitStockWriteService.unfreezeStockWithTx(pu.getSkuId(), puCount);
				}
			}

		}
		// 更新门店pu库存信息
		if(platformItems!=null && platformItems.size()>0) {
			storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), order, items));
		}
	}

	/**
	 * 回复订单库存
	 *
	 * @param items
	 */
	public void recoverOrderStock(List<SoItemDTO> items, String orderCode, Long userId, String userName, String ip,
			String mac) {
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
				stockWriteService.recoverOrderStockBatch(new RecoverOrderStockBatchDTO(it.getPuId(), it.getPuCount(), orderCode,
						StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), userId,
						userName, ip, mac, puIdList, commodityProductUnitDTOs));
			}else {
				stockWriteService.recoverOrderStock(it.getPuId(), it.getPuCount(), orderCode,
						StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(),
						commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
						userName, ip, mac);
			}

		}
	}

	/**
	 * 变更订单的所有子订单为分拣状态
	 *
	 * @param orderIds
	 */
	public void orderSort(List<Long> orderIds, Long operatorId) {
		if(orderIds!=null && orderIds.size()>0) {
			soChildWriteService.orderSort(orderIds, operatorId);
			for (Long orderId : orderIds) {
				SoDTO soDTO = soReadService.querySoById(orderId);
				// 发送订单物流状态变更消息
//				sendInfoWriteService.insertOrderDeliveryStatusInfoAndSend(
//						InfoConstant.ORDER_DELIVERY_STATUS_INFO_ID.getStatus(), soDTO.getOrderCode(), OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus(), soDTO.getUserId());
				/***********发送消息*********/
				if (soDTO.getSaleWay() == 7) {
					//saleWay=7 预售
					Map<String, String> params = new HashMap<>();
					params.put("订单编号", soDTO.getOrderCode());
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(soDTO.getPlatformId())) {
						infoTemplateId = InfoConstant.MYY_PRE_SELL_SEND.getStatus();
					} else {
						infoTemplateId = InfoConstant.FGJ_PRE_SELL_SEND.getStatus();
					}
					sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, soDTO.getUserId(), null));
				}
				/***********发送消息*********/
			}
		}

	}

	public void orderChildSort(List<Long> soChildrenIds, Long operatorId) {
		Long[] soChildrenIdss = soChildrenIds.toArray(new Long[soChildrenIds.size()]);
		Arrays.stream(soChildrenIdss).forEach(System.out::println);
		List<SoChildDTO> soChildren = soChildReadService.findSoChildByIds(soChildrenIdss, null);
		if(soChildren!=null && soChildren.size()>0) {

			soChildWriteService.orderChildSort(soChildren, operatorId);
			for (SoChildDTO soChild : soChildren) {
				SoDTO soDTO = soReadService.querySoById(soChild.getSoId());
				// 发送订单物流状态变更消息
//				sendInfoWriteService.insertOrderDeliveryStatusInfoAndSend(
//						InfoConstant.ORDER_DELIVERY_STATUS_INFO_ID.getStatus(), soDTO.getOrderCode(), OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus(), soDTO.getUserId());
				/***********发送消息*********/
				if (soDTO.getSaleWay() == 7) {
					//saleWay=7 预售
					Map<String, String> params = new HashMap<>();
					params.put("订单编号", soDTO.getOrderCode());
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(soDTO.getPlatformId())) {
						infoTemplateId = InfoConstant.MYY_PRE_SELL_SEND.getStatus();
					} else {
						infoTemplateId = InfoConstant.FGJ_PRE_SELL_SEND.getStatus();
					}
					sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, soDTO.getUserId(), null));
				}
				/***********发送消息*********/
			}
		}

	}
	/**
	 * 根据子订单编号查询子订单
	 *
	 * @param childCode
	 * @return
	 */
	public SoChildDTO querySoChildByChildCode(String childCode) {
		return soChildReadService.querySoChildByChildCode(childCode);
	}

	/**
	 * 通过订单id集合查询母订单和子订单信息
	 *
	 * @param orderIds
	 * @return
	 */
	public Map<String, Object> getOrderInfoByOrderIds(List<Long> orderIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SoDTO> soDTOList = new ArrayList<>();
		List<SoChildDTO> soChildDTOList = new ArrayList<>();
		for (Long id : orderIds) {
			SoDTO soDTO = soReadService.querySoById(id);
			SoChildDTO soChildDTO = new SoChildDTO();
			soChildDTO.setSoId(soDTO.getId());
			List<SoChildDTO> soChildDTOList_ = soChildReadService.findSoChildAll(soChildDTO);
			soDTOList.add(soDTO);
			for (SoChildDTO soChild : soChildDTOList_) {
				//非网店管家订单导出
				if (!Long.valueOf(3L).equals(soChild.getPerformingParty())&&!Long.valueOf(6L).equals(soChild.getPerformingParty())) {
					soChildDTOList.add(soChild);
				}
			}
		}
		map.put("soDTOList", soDTOList);
		map.put("soChildDTOList", soChildDTOList);

		return map;
	}

	public Map<String, Object> getOrderInfoByChildOrderIds(List<Long> childOrderIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String,SoDTO> soDTOMap = new HashMap<String,SoDTO>();


		List<SoChildDTO> soChildDTOList = new ArrayList<>();

		for (Long id : childOrderIds) {
			SoChildDTO soChildDTO = soChildReadService.findSoChildById(id);
			if(!soDTOMap.containsKey(soChildDTO.getSoId())) {
				SoDTO soDTO = soReadService.querySoById(soChildDTO.getSoId());
				soDTOMap.put(soDTO.getId().longValue()+"", soDTO);
			}
			if (!Long.valueOf(3L).equals(soChildDTO.getPerformingParty())&&!Long.valueOf(6L).equals(soChildDTO.getPerformingParty())) {
				soChildDTOList.add(soChildDTO);
			}

		}/*


		for (Long id : childOrderIds) {
			SoChildDTO soChildDTO = soChildReadService.findSoChildById(id);
			SoDTO soDTO = soReadService.querySoById(soChildDTO.getSoId());
			SoChildDTO soChildQueryDTO = new SoChildDTO();
			soChildQueryDTO.setSoId(soDTO.getId());
			List<SoChildDTO> soChildDTOList_ = soChildReadService.findSoChildAll(soChildQueryDTO);
			soDTOList.add(soDTO);
			for (SoChildDTO soChild : soChildDTOList_) {
				//非网店管家订单导出
				if (!Long.valueOf(3L).equals(soChild.getPerformingParty())&&!Long.valueOf(6L).equals(soChild.getPerformingParty())) {
					soChildDTOList.add(soChild);
				}
			}
		}*/
		map.put("soDTOList", new ArrayList<SoDTO>(soDTOMap.values()));
		map.put("soChildDTOList", soChildDTOList);

		return map;
	}
	public Integer findCouponUnitByOrderId(Long orderId) {

		return couponUnitReadService.findCouponUnitByOrderId(orderId);
	}

	public Integer findCouponUnitByOrder(SoDTO soDTO) {
		if (soDTO.getOrderPromotionDiscount().compareTo(BigDecimal.ZERO) == 0) {
			// 未使用优惠卷
			return -1;
		} else {
			if (soDTO.getOrderPromotionDiscount().equals(soDTO.getOrderAmount())) {
				// 使用兑换卷
				return 1;
			} else {
				// 使用满减卷
				return 0;
			}
		}
	}

	/**
	 * 取消订单
	 *
	 * @param order
	 * @throws AlipayApiException
	 */
	public void cancelOrderWithTx(SoDTO order, String ip, Long userId, String userName, String mac,
			String soRefundCodeByFubi, String soRefundCodeByCash,String soRefundCodeByJiDian,String soRefundCodeByBuyCard, HttpServletRequest req) {
		List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(order.getId());
		if (order.getOrderStatus() == OrderConstant.ORDER_STATUS_UNPAY.getStatus()) {
		logger.info("订单:{}取消订单，状态为待付款");
			// 待付款
			// 查看订单是否处于等待队列
			AwaitQueueDTO aq = this.queryAwaiteQueueByOrderId(order.getId());
			// 查询第三方是否支付成功(支付宝)
			String alipayResult = aq != null ? payUtil.alipayTradeQueryRequest(order.getOrderCode()) : null;
			String wxResult = aq != null ? payUtil.wxOrderQueryRequest(order.getOrderCode() + aq.getRandomNumber(), order.getPlatformId()) : null;

			if (alipayResult != null && !alipayResult.equals("FAIL")){
				// 支付宝已付款,但未回调
				// 取消订单,自动退款,生成退款单,生成退款批次和流水,释放库存
				order.setCashPayType(1);
				order.setOrderPaidByCash(new BigDecimal(alipayResult));
				soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName,order, items, userId, soRefundCodeByFubi, soRefundCodeByCash,soRefundCodeByJiDian,soRefundCodeByBuyCard,
						new HttpServletRequestDTO(req)));
				// 取消订单且退款成功,删除等待队列
				deleteAwaitQueueById(aq.getId());
				// 根据orderid释放冻结库存(待支付订单的订单项有可能是unit商品,回滚时检验unit存在性,回滚unit冻结库存)
				//根据公司id查询公司类型,只有正视公司操作库存
				if(checkCompany(order.getCompanyId())){
					unFreeseOrderStock(items, order, userId, userName, ip, mac);
				}
			} else if (wxResult != null && !wxResult.equals("FAIL")) {
				logger.info("订单:{}取消订单，状态为微信已付款,但未回调");
				// 微信已付款,但未回调
				order.setCashPayType(2);
				order.setOrderPaidByCash(new BigDecimal(wxResult).divide(BigDecimal.valueOf(100)));
				soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName,order, items, userId, soRefundCodeByFubi, soRefundCodeByCash,soRefundCodeByJiDian,soRefundCodeByBuyCard,
						new HttpServletRequestDTO(req)));
				// 取消订单且退款成功,删除等待队列
				deleteAwaitQueueById(aq.getId());
				// 根据orderid释放冻结库存(待支付订单的订单项有可能是unit商品,回滚时检验unit存在性,回滚unit冻结库存)
				if(checkCompany(order.getCompanyId())){
					unFreeseOrderStock(items, order, userId, userName, ip, mac);
				}
			} else {
				// 待付款
				// 处于等待队列:从队列删除,调用支付宝/微信关闭订单接口
				if (aq != null) {
					revocationOrderPay(aq, order.getPlatformId());
					deleteAwaitQueueById(aq.getId());
				}
				BigDecimal ff = ffReadService.findSoFreezeBalanceBySoId(order.getId());
				if (ff != null && ff.doubleValue() > 0) {
					// 查看订单是否存在冻结的积分,有则取消冻结
					cancelOrderFreezeFubi(order.getId(), ff, order.getUserId());
				}
				// 根据orderid释放冻结库存(待支付订单的订单项有可能是unit商品,回滚时检验unit存在性,回滚unit冻结库存)
				if(checkCompany(order.getCompanyId())){
					unFreeseOrderStock(items, order, userId, userName, ip, mac);
				}

				// 订单确认状态切换为已取消,等待后台手动退款
				changeOrderStatus(order.getOrderCode(),order.getId(), OrderConstant.ORDER_STATUS_CANCELED.getStatus(), 2, null, null);

				// 取消订单成功,记录取消订单的日志
				SoDTO newSoDTO = soReadService.querySoById(order.getId());
				EgeoLog log = new EgeoLog();
				log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
				log.setOperObject("SoFacade_cancelOrderWithTx");
				log.setMsgId(LogConstant.ORDER_CANCEL.getStatus());
				log.setType(LogTypeConstant.SO.getStatus());
				log.setOperatorObjId(newSoDTO.getId());
				log.setOperatorObjCode(newSoDTO.getOrderCode());
				log.setNewObj(newSoDTO);
				log.setOldObj(order);

				EgeoBusinessLogCommon.fillLogValue(log, req);

				try {
					ActiveMQUtils.recordBusinessLog(log);
					//logger.info("客户取消订单日志:{}",JSON.toJSONString(log));
				}catch (Exception e) {
					// TODO: handle exception
					logger.error("发送日志消息失败："+ JSON.toJSONString(log));
				}
				pushOrderManage.pushOrderToThird(order.getId(),"用户主动取消");
			}

		} else if (order.getOrderStatus() == OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
			logger.info("订单:{}取消订单，状态为已付款");
			// 已付款
			if (order.getOrderConfirmStatus() == 1 && order.getOrderPayStatus() == 1) {
				// 判断是否满足:订单状态为已付款,订单确认状态为已确认,订单支付状态为已支付----取消订单,可以自动退款
				logger.info("订单:{}取消订单，判断是否满足:订单状态为已付款,订单确认状态为已确认,订单支付状态为已支付----取消订单,可以自动退款");
				// 取消订单,自动退款,生成退款单,生成退款批次和流水,释放库存
				soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName,order, items, userId, soRefundCodeByFubi, soRefundCodeByCash,soRefundCodeByJiDian,soRefundCodeByBuyCard,
						new HttpServletRequestDTO(req)));

				/***********发送消息*********/
				Map<String, String> params = new HashMap<>();
				params.put("订单编号", order.getOrderCode());
				params.put("积分退款金额", order.getOrderPaidByFubi() + "");
				params.put("现金退款金额", order.getOrderPaidByCash() + "");
				String cashpayType = null;
				if (order.getCashPayType() != null) {
					if (order.getCashPayType() == 1) {
						cashpayType = "支付宝";
					} else if (order.getCashPayType() == 2) {
						cashpayType = "微信";
					} else if (order.getCashPayType() == 3) {
						cashpayType = "银联";
					} else if (order.getCashPayType() == 4) {
						cashpayType = "建行";
					}
				}
				params.put("现金支付方式", cashpayType);
				Long infoTemplateId;
				if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(order.getPlatformId())) {
					infoTemplateId = InfoConstant.MYY_CANCEL_PAYED_ORDER.getStatus();
				} else {
					infoTemplateId = InfoConstant.FGJ_CANCEL_PAYED_ORDER.getStatus();
				}
				sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params , userId, null));
				/***********发送消息*********/
			}
		}

		// 取消订单共同操作
		// 5.取消订单成功的优惠卷相关操作
		couponUnitWriteService.updateCouponByCancelOrderWithTx(order.getId());
		//取消订单成功,如果是以旧换新更新相关记录
		if(order.getSaleWay()==8){
			ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
			recordDTO.setConversionStatus(Integer.valueOf(2));
			recordDTO.setOrderCode(order.getOrderCode());
			int rt=exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);
			List<ExchangeOrderRecordDTO> exchangeOrderRecordAll = exchangeOrderRecordReadService.findExchangeOrderRecordAll(recordDTO);
			if(EmptyUtil.isNotEmpty(exchangeOrderRecordAll)&&exchangeOrderRecordAll.size()==1){
				CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
				couponUnitDTO.setCouponUnitCode(exchangeOrderRecordAll.get(0).getOldUnitCode());
				List<CouponUnitDTO> couponUnitAll = couponUnitReadService.findCouponUnitAll(couponUnitDTO);
				if(EmptyUtil.isEmpty(couponUnitAll)||couponUnitAll.size()>1){
					throw new BusinessException("取消订单,旧unit数据异常");
				}
				CouponUnitDTO unitDTO = couponUnitAll.get(0);
				unitDTO.setCouponUnitStatus(exchangeOrderRecordAll.get(0).getOldUnitStatus());
				couponUnitWriteService.updateCouponUnitWithTx(unitDTO);

			}

		}

		// 发送订单支付状态变更消息
//		sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(
//				order.getPlatformId(), InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus(), order.getOrderCode(), OrderConstant.ORDER_STATUS_PAYED.getStatus(), order.getUserId());

		//取消订单成功的限购规则记录相关操作
		/*LimitRuleRecordDTO limitRuleRecordDTO = new LimitRuleRecordDTO();
		limitRuleRecordDTO.setOrderCode(order.getOrderCode());
		limitRuleRecordDTO.setCreateUserid(userId);
		List<LimitRuleRecordDTO> limitRuleRecordAll = limitRuleRecordReadService.findLimitRuleRecordAll(limitRuleRecordDTO);
		for (LimitRuleRecordDTO dto : limitRuleRecordAll) {
			//取消订单后限购规则记录的购买总量清0
			dto.setBuySum(0L);
			limitRuleRecordWriteService.updateLimitRuleRecordWithTx(dto);
		}*/

		limitRuleRecordWriteService.updateOrderStatus(order.getOrderCode(), 0);
		//推送订单
		pushOrderManage.pushOrderInfo(order.getId(),null,null);
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

	/**
	 * 生成唯一的订单退款单编号,并校验订单退款单编号
	 *
	 * @return
	 */
	public List<String> genSoRefundNO() {

		return soRefundReadService.genSoRefundNO();
	}

	public void cancelOrderFreezeFubi(Long orderId, BigDecimal ff, Long userId) {
		// 减少积分冻结账户余额,并完成密文赋值
		UserAccountDTO ua = userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
		if (ua == null)
			throw new BusinessException("用户积分冻结账户不存在");
		SaltDTO salt = saltReadService.querySaltByUUID(ua.getUuid());
		if (salt == null)
			throw new BusinessException("用户积分冻结账户加密信息有误,请联系管理员");
		boolean cipherValid = MD5Util.md5Valid(ua.getBalance().toString(), salt.getSaltValue(), ua.getCiphertext());
		if (!cipherValid)
			throw new BusinessException("用户积分冻结账户存在被篡改嫌疑,请联系管理员");
		// 更新余额
		BigDecimal balance = ua.getBalance().subtract(ff);
		String cipher = MD5Util.MD5Salt(balance.toString(), salt.getSaltValue());
		// 根据用户积分冻结id修改冻结积分余额
		UserAccountDTO upCond = new UserAccountDTO();
		upCond.setId(ua.getId());
		upCond.setCiphertext(cipher);
		upCond.setBalance(balance);
		userAccountWriteService.updateUserAccountWithTx(upCond);
		// 删除积分冻结
		ffWriteService.delBySoId(orderId);
	}


	public void updateAddressCreateByBackStage(ReceiverAddressDTO ra) {
		receiverAddressWriteService.updateReceiverAddressWithTx(ra);
	}
	/**
	 * 查询用户订单/优惠卷的统计信息
	 * @param
	 * @return
	 */
	public Map<String, Object> queryUserStatisticalInfo(Long userId,Long platformId) {
		UserDTO userDTO = userReadService.findUserByID(userId);
		if (EmptyUtil.isEmpty(userDTO))
			throw new BusinessException("用户不存在");

		int unpayOrderCount = soReadService.querySoCountByUserAndStatus(userId, OrderConstant.ORDER_STATUS_UNPAY.getStatus(),platformId);
		int paidOrderCount = soReadService.querySoCountByUserAndStatus(userId, OrderConstant.ORDER_STATUS_PAYED.getStatus(),platformId);
		int deliveredOrderCount = soReadService.querySoCountByUserAndStatus(userId, OrderConstant.ORDER_STATUS_DELIVERED.getStatus(),platformId);
		int finishedOrderCount = soReadService.querySoCountByUserAndStatus(userId, OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(),platformId);

		CouponUnitDTO cuDTO = new CouponUnitDTO();
		cuDTO.setUserId(userId);
		cuDTO.setCouponUnitStatus(0);	// 未使用
		cuDTO.setPlatformId(platformId);
		List<CouponUnitDTO> cuDTOList = couponUnitReadService.findCouponUnitAll(cuDTO);
		int couponCount = 0;	// 未使用优惠券数量
		for (CouponUnitDTO couponUnitDTO : cuDTOList) {
			if (couponUnitDTO.getEffectEndTime() == null ||
					couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis())
				couponCount ++;
		}


		Map<String, Object> result = new HashMap<>();
		result.put("unpayOrderCount", unpayOrderCount);
		result.put("paidOrderCount", paidOrderCount);
		result.put("deliveredOrderCount", deliveredOrderCount);
		result.put("finishedOrderCount", finishedOrderCount);
		result.put("couponCount", couponCount);

		return result;
	}


    /*public ProductUnitDTO findPuByid(Long puId) {
		ProductUnitDTO productUnitDTO = new ProductUnitDTO();
		productUnitDTO.setId(puId);
		return productUnitReadService.findProductUnitById(productUnitDTO);
	}*/

	public MembershipDTO findMembershipBySkuId(Long skuId, Long platformId) {
		MembershipDTO membership= membershipReadService.findMembershipBySkuId(skuId,platformId);
		return membership;
	}


	public List<MembershipUserDTO> findMembershipUserByUserId(Long userId) {
		MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
		membershipUserDTO.setUserId(userId);
		List<MembershipUserDTO> membershipUserAll = membershipUserReadService.findMembershipUserAll(membershipUserDTO);
		return membershipUserAll;


	}

	public void insertMembershipUser(MembershipUserDTO membershipUserDTO) {
		membershipUserWriteService.insertMembershipUserWithTx(membershipUserDTO);
	}

	public PageResult<SoChildDTO> getSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
													Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
													Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
													Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,
													Integer orderPayStatus,Integer auditStatus) {
		return soChildReadService.getSoChildAllList(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType,
				soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,orderPayStatus,auditStatus);
	}

	public PageResult<SoChildDTO> getSupplierSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
													Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
													Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
													Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus) {
		return soChildReadService.getSupplierSoChildAllList(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType,
				soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);
	}
    public List<SoChildDTO> findSoChildBySoId(Long id) {
        SoChildDTO soChildDTO = new SoChildDTO();
        soChildDTO.setSoId(id);
        return soChildReadService.findSoChildAll(soChildDTO);
    }

	public void sendHostoryConfirm(Long orderId, Long operatorId, Long platformId) {
		soChildWriteService.writeSendInfo(orderId, operatorId, platformId);
	}

	public List<SoDTO> findSoByCode(String orderCode) {
		SoDTO so = new SoDTO();
		so.setOrderCode(orderCode);
		return soReadService.findSoByCode(orderCode);
	}

	public void writeDeliveryDate(Long orderId,Long platformId,Date date) {
		SoDeliveryItemDTO dto = new SoDeliveryItemDTO();
		SoDTO soDTO=soReadService.findSoById(orderId);
		if(EmptyUtil.isNotEmpty(soDTO)){
			dto.setOrderCode(soDTO.getOrderCode());
			dto.setDeliveryDate(date);
			dto.setPlatformId(platformId);
			soDeliveryItemWriteService.insertSoDeliveryItemWithTx(dto);
		}
		// 查询订单项
		List<SoItemDTO> soItems = soItemReadService.querySoItemBySoId(orderId);
		// 更新门店pu库存信息 (门店手动支付订单完成库存扣减)
		storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_RECEIVE.getStatus(),soDTO,soItems));


	}

	public JsonResult<Long> findSoSum(SoDTO soDTO) {
		soDTO.setSaleWay(-1);//标记-1,去除会籍购买
		Long res=soReadService.findSoSum(soDTO);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(res);
		return jsonResult;
	}

	public SkuDTO findSKUByPuId(Long puId) {
		CommodityProductUnitDTO productUnitDTO1 = new CommodityProductUnitDTO();
		productUnitDTO1.setId(puId);
		CommodityProductUnitDTO productUnitDTO = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO1);
		if(EmptyUtil.isEmpty(productUnitDTO)){
			throw new BusinessException("pu不存在");
		}
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setId(productUnitDTO.getSkuId());
		SkuDTO skuById = skuReadServcie.findSkuById(skuDTO);
		return skuById;
	}

	public StandardProductUnitDTO findStandardProductUnitById(Long standardProductUnitId) {
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(standardProductUnitId);
		return standardProductUnitReadService.findStandardProductUnitById(standardProductUnitDTO);
	}

	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(Long standardProductUnitId) {
		StandardProductUnitAttNameDTO standardProductUnitAttNameDTO = new StandardProductUnitAttNameDTO();
		standardProductUnitAttNameDTO.setStandardProductUnitId(standardProductUnitId);
		standardProductUnitAttNameDTO.setAttNameId(4L);
		List<StandardProductUnitAttNameDTO> list=standardProductUnitAttNameReadService.findStandardProductUnitAttNameAll(standardProductUnitAttNameDTO);
		if(EmptyUtil.isEmpty(list)){
			throw new BusinessException("该商品第三方对接参数有误");
		}
		return list.get(0);
	}

	public List<StandardProductUnitAttValueDTO> findStandardProductUnitValue(Long id) {
		StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
		standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(id);
		standardProductUnitAttValueDTO.setAttValueId(10L);
		return standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
	}

	public StoreDTO findStoreById(Long storeId) {
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(storeId);
		StoreDTO dto=storeReadService.findStoreById(storeDTO);
		if(EmptyUtil.isEmpty(dto)){
			throw new BusinessException(storeId + "不存在");
		}
		return dto;
	}


	public StorePuWarehouseStockDTO findStoreStock(Long id, Long storeId) {
		StorePuWarehouseStockDTO storePuWarehouseStockDTO = new StorePuWarehouseStockDTO();
		storePuWarehouseStockDTO.setStoreId(storeId);
		storePuWarehouseStockDTO.setStoreProductUnitId(id);
		List<StorePuWarehouseStockDTO> storePuWarehouseStockAll = storePuWarehouseStockReadService.findStorePuWarehouseStockAll(storePuWarehouseStockDTO);
		if(EmptyUtil.isEmpty(storePuWarehouseStockAll)){
			throw new BusinessException("未查询到门店库存");
		}
		if(storePuWarehouseStockAll.size()>1){
			throw new BusinessException("同一个pu在同一个门店中对应多个门店库存记录");
		}
		return storePuWarehouseStockAll.get(0);
	}

    public Integer getThirdpartyAttValue(Long spuId) {
		return standardProductUnitAttValueReadService.findThirdpartyAttBySpuId(spuId);
	}

    public List<SoChildDTO> findAllCountDeliverySoChild(Long id, Integer isOriginal) {
        SoChildDTO soChildDTO = new SoChildDTO();
        soChildDTO.setSoId(id);
        soChildDTO.setNeedCountDeliveryFee(isOriginal);
        return soChildReadService.findSoChildAll(soChildDTO);
    }

	public CompanyDTO findCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}

    public String findDeliveryCompanyNameBySoChildId(Long soChildId) {
		return soPackageReadService.findDeliveryCompanyNameBySoChildId(soChildId);
    }

	public List<SoItemDTO> findSoItemListBySoChildId(Long soChildId) {
		SoItemDTO soItemDTO = new SoItemDTO();
		soItemDTO.setSoChildId(soChildId);
		return soItemReadService.findAll(soItemDTO);
	}

    public List<SoItemDTO> findSoItemListBySoId(SoItemDTO soItemDTO) {
		return soItemReadService.findAll(soItemDTO);
	}

    public void repairOrderDataWithTx() {
    	soWriteService.repairOrderDataWithTx();
    }

	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordByOrderCode(String orderCode) {
		return exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(orderCode);
	}

	public int updateOrderCancelStatus(String orderCode) {
		return soWriteService.updateOrderCancelStatus(orderCode);
	}

	public int updateOrderCancelOverStatusByOrderCode(String orderCode) {
		return soWriteService.updateOrderCancelOverStatusByOrderCode(orderCode);
	}

	public int updateCouponUnitLockedByCouponUnitId(Long couponUnitId) {
		return couponUnitWriteService.updateCouponUnitLockedByCouponUnitId(couponUnitId);
	}

    public int updateCouponUnitRemoveLock(String oldUnitCode) {
		return couponUnitWriteService.updateCouponUnitRemoveLock(oldUnitCode);
	}

	public CouponUnitDTO findCouponUnitById(Long exchangeCouponUnitId) {
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(exchangeCouponUnitId);
		return couponUnitReadService.findCouponUnitById(couponUnitDTO);
	}

	public List<SoChildDTO> findSoChildListByMerchantId(List<Long> idList, long merchantId) {
		return soChildReadService.findSoChildListByMerchantId(idList,merchantId);
	}

	public List<SoChildDTO> findSoChildListBySupplierId(List<Long> idList, long supplier) {
		return soChildReadService.findSoChildListBySupplierId(idList,supplier);
	}

	public MerchantDTO findMerchantById(Long merchantId) {
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setId(merchantId);
		return merchantReadService.findMerchantById(merchantDTO);
	}

	/**
	 * 是否允许京东下单
	 * @param companyId
	 * @param configs
	 * @return true:允许;false:不允许
	 */
	private boolean jdOrderAllow(Long companyId,List<CompanyConfigDTO> configs){
		boolean jdOrderAllow = true;
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


	private String buildCakeOrderParam(Long orderId, SoChildDTO soChildDTO, ReceiverAddressDTO addr,String spuId,String skuId) {
		JSONObject rtObj = new JSONObject();
		String phone = (StringUtils.isEmpty(addr.getGoodReceiverPhone()) || StringUtils.isBlank(addr.getGoodReceiverPhone()))?addr.getGoodReceiverMobile():addr.getGoodReceiverPhone();
		//预存信息
		putPreInfo(rtObj,soChildDTO,phone);
		//蛋糕叔叔用户id
		putUserInfo(rtObj);
		//蛋糕叔叔城市id
		putCityId(rtObj,addr);
		//蛋糕叔叔地址id以及其对应的信息
		putAddr(rtObj,addr);
		//蛋糕叔叔规则id
		putRuteInfo(rtObj,soChildDTO,null,null);
		return JSON.toJSONString(rtObj);
	}

	@Autowired
	private CakeAddressWriteManage cakeAddressWriteManage;

	@Autowired
	private SoItemReadManage soItemReadManage;

	private void putOtherInfo(JSONObject rtObj,SoChildDTO soChildDTO){

	}

	private void putRuteInfo(JSONObject rtObj,SoChildDTO soChildDTO,String productId,String skuId){
		//配送规则id(通过city_id,addr_id,spec_id,quantitys获取)
		String city_id =  rtObj.getString("city_id");
		/*JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("product_id",productId);
		jsonObject.put("city_id",city_id);
		array.add(jsonObject);*/
		SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
		List<String> productIds = soItemReadService.findProductIdsSoChild(soChildDTO1.getId());

		JSONArray array = new JSONArray();
		for (String id : productIds) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("product_id",id);
			jsonObject.put("city_id",city_id);
			array.add(jsonObject);
		}
		logger.info("批量查询规则ids的参数:"+array.toString());
		//JSONObject ruleIdsRT =  cakeUtil.getRuleIds(array.toString());
		Map<String,Object> p = new HashMap<>();
		p.put("product",array);
		JSONObject ruleIdsRT =  cakeUtil.getRuleIds(p);
		JsonResult checkRT = cakeUtil.checkResult(ruleIdsRT);
		if(Objects.nonNull(checkRT)){
			throw new BusinessException("提交蛋糕专卖订单时获取配送规则ID失败，发生异常"+JSON.toJSONString(checkRT));
		}
		logger.info("批量查询规则ids的结果:{}",JSON.toJSONString(ruleIdsRT));
		JSONArray dataList = ruleIdsRT.getJSONArray(cakeUtil.DATA_KEY);
		if(null == dataList || dataList.size() ==0){
			throw new BusinessException("提交蛋糕专卖订单时获取配送规则ID失败，发生异常"+JSON.toJSONString(checkRT));
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dataList.size(); i++) {
			JSONObject rtObject =dataList.getJSONObject(i);
			sb.append(rtObject.getString("distribution_rule_id")).append(",");
		}
		if(sb.length() >0){
			sb.deleteCharAt(sb.length() - 1);
		}
		String ruleIds = sb.toString();
		rtObj.put("rule_ids",ruleIds);
		//配送规则接口（最新版）
		String specIds =  rtObj.getString("spec_ids");
		String quantitys =  rtObj.getString("quantitys");
		String addr_id =  rtObj.getString("addr_id");
		JSONObject distributionRulesRT = cakeUtil.getDistributionRules(city_id,addr_id,specIds,quantitys);
		JsonResult checkdistributionRulesRT = cakeUtil.checkResult(distributionRulesRT);
		if(Objects.nonNull(checkdistributionRulesRT)){
			throw new BusinessException("提交蛋糕专卖订单时获取配送规则信息失败"+JSON.toJSONString(checkdistributionRulesRT));
		}
		JSONObject rulesObject = distributionRulesRT.getJSONObject(cakeUtil.DATA_KEY);
		if(null == rulesObject){
			throw new BusinessException("提交蛋糕专卖订单时获取配送规则信息data失败");
		}
		logger.info("配送规则接口（最新版）响应结果{}",JSON.toJSONString(distributionRulesRT));
		//是否可配送 0不可配送、1可配送
		String is_distribution = rulesObject.getString("is_distribution");
		//是否支持快递配送 1-支持,0-不支持
		String can_same = rulesObject.getString("can_same");
		String can_ship = rulesObject.getString("can_ship");
//		if(Objects.equals(is_distribution,"0") || Objects.equals(can_same,"0")){
//			throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递配送的商品"+JSON.toJSONString(rulesObject));
//		}
		if(Objects.equals(is_distribution,"0")){
			throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递或配送的商品");
		}
		if(Objects.equals(can_same,"0") && Objects.equals(can_ship,"0")){
			throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递或配送的商品");
		}
		JSONArray jsonArray = rulesObject.getJSONArray("validate_delivery_dates");
		Object ship_date = DateUtils.getDate();
		String ship_type = "same";
		String delivery_text =rulesObject.containsKey("delivery_text")?rulesObject.getString("delivery_text"):"";
		JSONObject groupChild = new JSONObject();
		if(Objects.equals(can_same,"1")){
			ship_date = false;
		}else if(Objects.equals(can_ship,"1")){
			//商品配送
			ship_type = "delivery";
			if(jsonArray !=null && jsonArray.size()>0){
				JSONObject deliveryJson = jsonArray.getJSONObject(0);
				ship_date=deliveryJson.getString("date");
				JSONArray validate_delivery_times = deliveryJson.getJSONArray("validate_delivery_times");
				if(validate_delivery_times !=null && validate_delivery_times.size() >0){
					delivery_text =  validate_delivery_times.getString(validate_delivery_times.size()-1);
				}
			}
		}

		JSONObject group = new JSONObject();
		List<String> list = Arrays.asList(ruleIds.split(","));
		groupChild.put("ship_type",ship_type);
		groupChild.put("ship_date", ship_date);
		groupChild.put("ship_time_text",delivery_text);
		for (String s : list) {
			group.put(s,groupChild);
		}
		rtObj.put("group",group);
	}

	private void putCityId(JSONObject rtObj,ReceiverAddressDTO addr){
		//String userId = rtObj.getString("user_id");
		String cityName =addr.getGoodReceiverCity();
		if("市辖区".equals(cityName) || "直辖市".equals(cityName)){
			cityName = addr.getGoodReceiverProvince();
		}
		String cityId = cakeAddressWriteManage.getCityId(cityName);
		rtObj.put("city_id",cityId);
	}

	private void putAddr(JSONObject rtObj,ReceiverAddressDTO addr){
		CakeAddResultDTO dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,rtObj.getString("user_id"));
		String detail = dto.getAddr().contains(dto.getCity())?dto.getAddr():dto.getCity()+dto.getAddr();
		detail = detail.contains(dto.getProvince())?detail:dto.getProvince()+detail;
		JSONObject rtAddrObj = new JSONObject();
		rtAddrObj.put("id",dto.getId());
		rtAddrObj.put("name",dto.getName());
		rtAddrObj.put("phone",dto.getPhone());
		rtAddrObj.put("detail",detail);
		rtObj.put("addr",rtAddrObj);
	}

	private void putUserInfo(JSONObject rtObj){
		//1、用户id（通过平台与渠道用户关联接口创建返回）
		JSONObject userObject = cakeUtil.userLogin(null);
		JsonResult checkUserRT = cakeUtil.checkResult(userObject);
		if(Objects.nonNull(checkUserRT)){
			throw new BusinessException("提交蛋糕叔叔订单时登录用户发生异常"+JSON.toJSONString(checkUserRT));
		}
		JSONObject userData = userObject.getJSONObject(cakeUtil.DATA_KEY);
		String userId = userData.getString("id");
		rtObj.put("user_id",userId);
	}

	private void putPreInfo(JSONObject rtObj,SoChildDTO soChildDTO,String phone){
		rtObj.put("out_order_no",soChildDTO.getChildCode());
		rtObj.put("pay_type",cakeUtil.getCakeChannelNo());
		rtObj.put("buyer_phone",phone);
		rtObj.put("buyer_msg","请尽快送达");
		List<SoChildDTO.SkuInfoDTO>  list = soChildDTO.getSkuInfoList();
		if(!CollectionUtils.isEmpty(list)){
			StringBuilder specIds = new StringBuilder();
			StringBuilder quantitys = new StringBuilder();
			for (SoChildDTO.SkuInfoDTO skuInfoDTO : list) {
				specIds.append(skuInfoDTO.getSkuId()).append(",");
				quantitys.append(skuInfoDTO.getNum()).append(",");
			}
			if(specIds.charAt(specIds.length() - 1) == ','){
				// 删除最后一个字符
				specIds.deleteCharAt(specIds.length() - 1);
			}
			if(quantitys.charAt(quantitys.length() - 1) == ','){
				// 删除最后一个字符
				quantitys.deleteCharAt(quantitys.length() - 1);
			}
			rtObj.put("spec_ids",specIds.toString());
			rtObj.put("quantitys",quantitys.toString());
		}
	}

	private void checkCakeOrCancelOrder(HttpServletRequest req, SoDTO sodto, Long userId, String userName, String ip, String mac, JSONObject submitOrderResult) {
		JsonResult checkResult = cakeUtil.checkResult(submitOrderResult);
		//检查订单是否成功，是否需要渠道订单
		if(Objects.nonNull(checkResult)){
			cancelOrderJd(sodto, userId, ip, userName, mac, req);
			throw new BusinessException("当前订单存在蛋糕专卖子订单,订单下单失败"+checkResult.getError()+",该订单已取消");
		}
		JSONObject submitRT = submitOrderResult.getJSONObject(cakeUtil.DATA_KEY);
		if(Objects.nonNull(submitRT)){
			return;
		}
		//蛋糕叔叔
		cancelOrderJd(sodto, userId, ip, userName, mac, req);
		throw new BusinessException("当前订单存在蛋糕专卖子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));

	}


	private void submitCakeOrderSuccess(SoDTO sodto, SoThirdpartyDTO soThirdpartyDTO, Long orderId, SoChildDTO soChildDTO, JSONObject submitOrderResult) {
		JsonResult checkResult = cakeUtil.checkResult(submitOrderResult);
		if(Objects.nonNull(checkResult)){
			throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
		}
		String submitRT = submitOrderResult.getString(cakeUtil.DATA_KEY);
		if(StringUtils.isEmpty(submitRT)){
			throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
		}
		CakeSubmitOrderResultDTO cakeSubmitOrderResultDTO = JsonUtils.jsonToPojo(submitRT,CakeSubmitOrderResultDTO.class);
		String cakeOrderId = cakeSubmitOrderResultDTO.getOrder_no();
		soChildDTO.setThirdpartySoChildId(Long.valueOf(cakeOrderId));
		//更新运费价格
		BigDecimal oldFree = soChildDTO.getOrdinaryDeliveryFee();

		logger.info("蛋糕叔叔订单进行下单成功->"+ JSON.toJSONString(cakeSubmitOrderResultDTO));
		BigDecimal freight = new BigDecimal(cakeSubmitOrderResultDTO.getShip_amount()).setScale(2);
		BigDecimal orderAmount= new BigDecimal(cakeSubmitOrderResultDTO.getOrder().getFinal_amount()).setScale(2);
		//soChildDTO.setDeliveryFee(freight);
		//soChildDTO.setOrdinaryDeliveryFee(freight);
		soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
		soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
		soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
		/*BigDecimal addFree = freight.subtract(oldFree);
		sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
		sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
		sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
		sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
		sodto.setId(orderId);
		soWriteService.updateOrderWithTX(sodto);*/
		SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
		if(null !=oldSoThirdpartyDTO){
			SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
			editSoThirdpartyDTO.setThirdpartyId(cakeOrderId);
			editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
			editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
			editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
			soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
		}

	}


	private void submitWorldOrderSuccess(SoDTO sodto, SoThirdpartyDTO soThirdpartyDTO, Long orderId, SoChildDTO soChildDTO, JSONObject submitOrderResult) {
		JsonResult checkResult = worldBuyUtil.checkResult(submitOrderResult);
		if(Objects.nonNull(checkResult)){
			throw new BusinessException("当前订单存在全球购子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
		}
		String dataJson = submitOrderResult.getString(worldBuyUtil.DATA_KEY);
		if(StringUtils.isEmpty(dataJson)){
			throw new BusinessException("当前订单存在全球购子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
		}
		WorldBuySubmitOrderResultDTO worldBuySubmitOrderResultDTO = JSONObject.parseObject(dataJson,WorldBuySubmitOrderResultDTO.class);
		WorldOrderItemsResponseDTO worldOrderItemsResponseDTO = worldBuySubmitOrderResultDTO.getOrderItems().get(0);
		String cakeOrderId = worldOrderItemsResponseDTO.getOrderNo();
		soChildDTO.setThirdpartySoChildId(Long.valueOf(cakeOrderId));
		//更新运费价格
		BigDecimal oldFree = soChildDTO.getOrdinaryDeliveryFee()==null?BigDecimal.ZERO:soChildDTO.getOrdinaryDeliveryFee();

		logger.info("全球购订单进行下单成功->"+ JSON.toJSONString(worldBuySubmitOrderResultDTO));
		BigDecimal freight = new BigDecimal(worldOrderItemsResponseDTO.getTotalTemplateDelivery()).setScale(2);
		BigDecimal orderAmount= new BigDecimal(worldOrderItemsResponseDTO.getRealTotalMoney()).setScale(2);
		//soChildDTO.setDeliveryFee(freight);
		//soChildDTO.setOrdinaryDeliveryFee(freight);
		soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
		soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
		soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
		//BigDecimal addFree = freight.subtract(oldFree);
		//sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
		//sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
		//sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
		//sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
		/*sodto.setId(orderId);
		try {
			soWriteService.updateOrderWithTX(sodto);
		}catch (Exception e){
			logger.info("提交全球购订单时更新本地订单其他信息失败:{}",e);
		}*/

		SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
		if(null !=oldSoThirdpartyDTO){
			SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
			editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
			editSoThirdpartyDTO.setThirdpartyId(cakeOrderId);
			editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
			editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
			soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
		}
	}

	private void checkWorldOrCancelOrder(HttpServletRequest req, SoDTO sodto, Long userId, String userName, String ip, String mac, JSONObject submitOrderResult) {
		JsonResult checkResult = worldBuyUtil.checkResult(submitOrderResult);
		//检查订单是否成功，是否需要渠道订单
		if(Objects.nonNull(checkResult)){
			cancelOrderJd(sodto, userId, ip, userName, mac, req);
			throw new BusinessException("当前订单存在全球购专卖子订单,订单下单失败"+checkResult.getError()+",该订单已取消");
		}
		JSONObject submitRT = submitOrderResult.getJSONObject(worldBuyUtil.DATA_KEY);
		if(Objects.nonNull(submitRT)){
			return;
		}
		//全球购
		cancelOrderJd(sodto, userId, ip, userName, mac, req);
		throw new BusinessException("当前订单存在全球购子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));

	}

	/**
	 * 查询用户不同状态的订单列表
	 *
	 * @param userId
	 * @param orderStatus
	 * @param platformId
	 * @param page
	 * @return
	 */
	public PageResult<SoDetailVO> getOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId,
														   Long f, Long clientId, Pagination page) {

		PageResult<SoExtendsCondition> dtoResult = soReadService.getOrderByUserAndStatus(userId, orderStatus, platformId,
				page);
		List<SoDetailVO> list = new ArrayList<>();
		PageResult<SoDetailVO> result = new PageResult<>();
		result.setTotalSize(dtoResult.getTotalSize());
		result.setPageNo(dtoResult.getPageNo());
		result.setPageSize(dtoResult.getPageSize());
		result.setList(list);
		if(CollectionUtils.isEmpty(dtoResult.getList())){
			logger.info("没有查到订单数据,查询条件userId{},orderStatus:{},platformId:{}",userId,orderStatus,platformId);
			return result;
		}
		OrderByUserAndStatusRequestVO vo = new OrderByUserAndStatusRequestVO();
		vo.setF(f);
		vo.setClientId(clientId);
		vo.setUserId(userId);
		vo.setOrderStatus(orderStatus);
		vo.setPlatformId(platformId);

		List<SoExtendsCondition> dataList = dtoResult.getList();
		List<Long> soOrderIds = FHCollectionUtils.listToStrList(dataList,SoExtendsCondition::getId,e->e.getIfChildOrder().equals("0"));
		List<Long> soChildIds = FHCollectionUtils.listToStrList(dataList,SoExtendsCondition::getId,e->e.getIfChildOrder().equals("1"));
		List<SoDTO> soDTOList = soReadService.getSoByIds(soOrderIds);
		List<SoChildDTO> soChildList = soChildReadService.findSoChildByIdList(soChildIds);
		Map<Long,SoDTO> soDTOMap = FHCollectionUtils.listToMap(soDTOList,SoDTO::getId,e->e);
		Map<Long,SoChildDTO> soChildDTOMap = FHCollectionUtils.listToMap(soChildList,SoChildDTO::getId,e->e);
		//存放数据为(子订单ID, 是否自营)
		Map<Long, Integer> merchantMap = new HashMap<>();
		for (SoExtendsCondition soExtendsCondition : dataList) {
			logger.info("soExtendsCondition:{}",JSON.toJSONString(soExtendsCondition));
			setBySoOrder(soDTOMap,soExtendsCondition,list,vo,merchantMap);
			setBySoChildOrder(soChildDTOMap,soExtendsCondition,list,vo,merchantMap);
		}
		result.setList(list);
		return result;
	}

	private void setBySoOrder(Map<Long,SoDTO> soDTOMap,SoExtendsCondition soExtendsCondition,List<SoDetailVO> resultList,OrderByUserAndStatusRequestVO requestVO,Map<Long, Integer> merchantMap){
		if(!soExtendsCondition.getIfChildOrder().equals("0")){
			return;
		}
		if(EmptyUtil.isEmpty(soDTOMap)){
			return;
		}
		SoDTO so = soDTOMap.get(soExtendsCondition.getId());
		if(so == null){
			return;
		}
			SoDetailVO vo = new SoDetailVO();
			// 查询子订单列表
			List<SoChildDTO> scList = soChildReadService.querySoChildListBySoId(so.getId());
			vo.setId(so.getId());
			vo.setOrderAmount(so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
			vo.setUseFubi(so.getUseFubi());
			vo.setOrderCode(so.getOrderCode());
			vo.setOrderStatus(so.getOrderStatus());
			vo.setCreateTime(so.getCreateTime() != null ? so.getCreateTime().getTime() : null);
			vo.setIfChildOrder(soExtendsCondition.getIfChildOrder());
			Integer goodsAccount = 0;
			List<OrderConfirmGoodsVO> ocgs = new ArrayList<>();
			List<SoChildWebVO> soChildWebList = new ArrayList<>();
			Boolean buyTypeFlag = false;
			if (requestVO.getF().equals(3L) && requestVO.getClientId().equals(3L)) {
				// web商城订单列表
				for (SoChildDTO soChildDTO : scList) {
					SoChildWebVO soChildWebVO = new SoChildWebVO();
					SoItemDTO soItemDTO = new SoItemDTO();
					soItemDTO.setSoId(so.getId());
					soItemDTO.setSoChildId(soChildDTO.getId());
					soItemDTO.setUserId(requestVO.getUserId());
					List<SoItemDTO> soItemList = soReadService.mergeSoItems(soItemReadService.findAll(soItemDTO));

//					boolean unitExist = false;
					List<OrderConfirmGoodsVO> ocgsForWeb = new ArrayList<>();

					for (SoItemDTO it : soItemList) {
						OrderConfirmGoodsVO ocg = new OrderConfirmGoodsVO();
						goodsAccount += it.getPuCount();
						ocg.setNum(it.getPuCount());
						ocg.setPrice(it.getPrice().doubleValue());
						ocg.setPuId(it.getPuId());
						ocg.setPuImg(it.getPuPicUrl());
						ocg.setPuName(it.getPuName());
						ocg.setSource(it.getSource());
						ocg.setChannelProductId(it.getExternalProductId());
						CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
						productUnitDTO.setId(it.getPuId());
						CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
						StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(pu.getStandardUnitId());
						if(standardUnitById.getBuyType()!=3){
							buyTypeFlag = true;
						}
						if(it.isThirdParty()){
							ocg.setStandardUnitId(it.getPuId());
						}else{
							if(pu !=null ){
								ocg.setStandardUnitId(pu.getStandardUnitId());
							}
						}

						Long soChildId = it.getSoChildId();
						if (!merchantMap.containsKey(soChildId)) {
							SoChildDTO soChild = soChildReadService.findSoChildById(it.getSoChildId());
							merchantMap.put(soChildId, soChild.getPerformingParty().equals(1L) ? 1 : 0);
						}
						ocg.setIsOwnMerchant(merchantMap.get(soChildId));
						if (it.getUnitExist() == 1) {
//							unitExist = true;
						}
						ocgsForWeb.add(ocg);
					}
					soChildWebVO.setId(soChildDTO.getId());
					soChildWebVO.setChildCode(soChildDTO.getChildCode());
					soChildWebVO.setGoodsList(ocgsForWeb);
					soChildWebVO.setCreateTime(soChildDTO.getCreateTime().getTime());
					soChildWebVO.setChildOrderAmount(soChildDTO.getAmount());

					soChildWebList.add(soChildWebVO);
				}
			} else {
				// 其他订单列表
				SoItemPO soItem = new SoItemPO();
				soItem.setUserId(requestVO.getUserId());
				soItem.setSoId(so.getId());
				//订单项列表
				List<SoItemPO> soItemPOList = soItemReadManage.findSoItemList(soItem);
				List<SoItemDTO> items = soReadService.mergeSoItems(SoItemConverter.toDTO(soItemPOList));
				boolean unitExist = false;
				for (SoItemDTO it : items) {
					OrderConfirmGoodsVO ocg = new OrderConfirmGoodsVO();
					goodsAccount += it.getPuCount();
					ocg.setNum(it.getPuCount());
					ocg.setPrice(it.getPrice().doubleValue());
					ocg.setPuId(it.getPuId());
					ocg.setPuImg(it.getPuPicUrl());
					ocg.setPuName(it.getPuName());
					ocg.setSource(it.getSource());
					ocg.setChannelProductId(it.getExternalProductId());
					if(it.isThirdParty()) {
						ocg.setIsOwnMerchant(0);
						ocg.setStandardUnitId(it.getPuId());
					}else {
						CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
						productUnitDTO.setId(it.getPuId());
						CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
						if(pu!=null) {
							StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(pu.getStandardUnitId());
							if(standardUnitById.getBuyType()!=3){
								buyTypeFlag = true;
							}
							ocg.setStandardUnitId(pu.getStandardUnitId());
						}
						Long soChildId = it.getSoChildId();
						if (!merchantMap.containsKey(soChildId)) {
							SoChildDTO soChild = soChildReadService.findSoChildById(it.getSoChildId());
							merchantMap.put(soChildId, soChild.getPerformingParty().equals(1L) ? 1 : 0);
						}
						ocg.setIsOwnMerchant(merchantMap.get(soChildId));
					}

					if (it.getUnitExist() == 1) {
						unitExist = true;
					}
					ocgs.add(ocg);
				}
				vo.setGoodsList(ocgs);
				vo.setUnitExist(unitExist);
			}
			vo.setSoChildWebList(soChildWebList);
			vo.setGoodsAccount(goodsAccount);
			// 收集子订单物流状态
			List<Integer> scDeliveryStatusList = new ArrayList<>();
			Integer thirdpartyType = 0;
			String jumpUrl=null;
			boolean flag=false;
			for (SoChildDTO sc : scList) {
				if(EmptyUtil.isNotEmpty(sc.getReceiverAddressId())){
					flag=true;
				}
				scDeliveryStatusList.add(sc.getDeliveryStatus());
				if (sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
					thirdpartyType = 1;
				}
				if(sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC){
					thirdpartyType = 2;
				}
				if (sc.getThirdpartyType()== ThirdConst.ThirdPartyType.QM){
					thirdpartyType = ThirdConst.ThirdPartyType.QM;
					jumpUrl=sc.getExt();
				}
			}

			//当订单内商品全部为仅支持现金支付，或用户无可用积分（即积分全部冻结或无积分）时，[积分支付]按钮隐藏
			vo.setOperateStatus(judgeOrderOperateStatus(requestVO.getClientId(),so.getId(),so.getOrderPayStatus(), so.getOrderConfirmStatus(),
					scDeliveryStatusList, thirdpartyType,so.getLimitCashPayAmount(),so.getOrderAmount(),requestVO.getUserId(),requestVO.getPlatformId()));

			if(so.getOrderStatus()==4){
				//如果是加价购查询优惠券类型
				if(so.getSaleWay()==8){
					vo.setOperateStatus(6);
					List<ExchangeOrderRecordDTO> list=exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(so.getOrderCode());
					if(EmptyUtil.isEmpty(list)||list.size()>1){
						logger.info("[该订单号数据有误,订单详情]orderCode="+so.getOrderCode());
						throw new BusinessException("该订单号数据有误");
					}
					vo.setExchangeCouponType(list.get(0).getNewCouponType());
				}
			}
		logger.info("订单操作状态:operateStatus={},orderCode={}", new Object[]{vo.getOperateStatus(), vo.getOrderCode()});

		logger.debug("订单操作状态:operateStatus={},id={}", new Object[]{vo.getOperateStatus(), vo.getId()});

			// 电子卡券类商品没有查看物流和确认收货按钮的特殊处理(话费充值类/券仓已特殊处理)
			if (!flag && ((vo.getOperateStatus().equals(Integer.valueOf(2))
					|| vo.getOperateStatus().equals(Integer.valueOf(3))))) {
				logger.debug("订单操作状态:电子卡券类商品没有查看物流和确认收货按钮的特殊处理,id=" + vo.getId());
				vo.setOperateStatus(4);
			}

			if(!buyTypeFlag){
				if(vo.getOperateStatus()==0){
					vo.setOperateStatus(8);
				}
			}
			if (Objects.equals(thirdpartyType,ThirdConst.ThirdPartyType.QM)){
				vo.setOperateStatus(9);//清美订单，直接跳转到清美页面，操作按钮不显示
				vo.setJumpUrl(jumpUrl);
			}
			// 查询订单的优惠卷信息
			vo.setCouponType(this.findCouponUnitByOrder(so));
		resultList.add(vo);
	}

	private void setBySoChildOrder(Map<Long,SoChildDTO> soChildDTOMap,SoExtendsCondition soExtendsCondition,List<SoDetailVO> resultList,OrderByUserAndStatusRequestVO requestVO,Map<Long, Integer> merchantMap){
		if(!soExtendsCondition.getIfChildOrder().equals("1")){
			return;
		}
		if(EmptyUtil.isEmpty(soChildDTOMap)){
			return;
		}
		SoChildDTO soChildDTO = soChildDTOMap.get(soExtendsCondition.getId());
		if(soChildDTO == null){
			return;
		}
		SoDTO so = soReadService.findSoById(soChildDTO.getSoId());
		SoDetailVO vo = new SoDetailVO();
		vo.setId(soChildDTO.getId());
		vo.setOrderAmount(soChildDTO.getAmount().add(soChildDTO.getDeliveryFee() !=null?soChildDTO.getDeliveryFee():BigDecimal.ZERO).subtract(soChildDTO.getCouponDiscount()));
		vo.setUseFubi(so.getUseFubi());
		vo.setOrderCode(soChildDTO.getChildCode());
		vo.setOrderStatus(soExtendsCondition.getOrderStatus());
		vo.setCreateTime(so.getCreateTime() != null ? so.getCreateTime().getTime() : null);
		vo.setIfChildOrder(soExtendsCondition.getIfChildOrder());
		Integer goodsAccount = 0;
		List<OrderConfirmGoodsVO> ocgs = new ArrayList<>();
		List<SoChildWebVO> soChildWebList = new ArrayList<>();
		Boolean buyTypeFlag = false;


		SoChildWebVO soChildWebVO = new SoChildWebVO();
		SoItemDTO soItemDTO = new SoItemDTO();
		soItemDTO.setSoId(so.getId());
		soItemDTO.setSoChildId(soChildDTO.getId());
		soItemDTO.setUserId(requestVO.getUserId());
		List<SoItemDTO> soItemList = soReadService.mergeSoItems(soItemReadService.findAll(soItemDTO));

//					boolean unitExist = false;
		List<OrderConfirmGoodsVO> ocgsForWeb = new ArrayList<>();

		for (SoItemDTO it : soItemList) {
			OrderConfirmGoodsVO ocg = new OrderConfirmGoodsVO();
			goodsAccount += it.getPuCount();
			ocg.setNum(it.getPuCount());
			ocg.setPrice(it.getPrice().doubleValue());
			ocg.setPuId(it.getPuId());
			ocg.setPuImg(it.getPuPicUrl());
			ocg.setPuName(it.getPuName());
			ocg.setSource(it.getSource());
			ocg.setChannelProductId(it.getExternalProductId());
			if(it.isThirdParty()) {
				ocg.setIsOwnMerchant(0);
				ocg.setStandardUnitId(it.getPuId());
			}else {
				CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
				productUnitDTO.setId(it.getPuId());
				CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
				StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(pu.getStandardUnitId());
				if (standardUnitById.getBuyType() != 3) {
					buyTypeFlag = true;
				}
				if(pu !=null){
					ocg.setStandardUnitId(pu.getStandardUnitId());
				}

				Long soChildId = it.getSoChildId();
				if (!merchantMap.containsKey(soChildId)) {
					SoChildDTO soChild = soChildReadService.findSoChildById(it.getSoChildId());
					merchantMap.put(soChildId, soChild.getPerformingParty().equals(1L) ? 1 : 0);
				}
				ocg.setIsOwnMerchant(merchantMap.get(soChildId));
			}
			if (it.getUnitExist() !=null && it.getUnitExist() == 1) {
//							unitExist = true;
			}
			ocgsForWeb.add(ocg);
		}
		soChildWebVO.setId(soChildDTO.getId());
		soChildWebVO.setChildCode(soChildDTO.getChildCode());
		soChildWebVO.setGoodsList(ocgsForWeb);
		soChildWebVO.setCreateTime(soChildDTO.getCreateTime().getTime());
		soChildWebVO.setChildOrderAmount(soChildDTO.getAmount().add(soChildDTO.getDeliveryFee()!=null?soChildDTO.getDeliveryFee():BigDecimal.ZERO));

		soChildWebList.add(soChildWebVO);


		vo.setSoChildWebList(soChildWebList);
		vo.setGoodsAccount(goodsAccount);
		Integer thirdpartyType = 0;
		String jumpUrl=null;
		boolean flag=false;
		if(EmptyUtil.isNotEmpty(soChildDTO.getReceiverAddressId())){
			flag=true;
		}
		if (soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
			thirdpartyType = 1;
		}
		if(soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC){
			thirdpartyType = 2;
		}
		if (soChildDTO.getThirdpartyType()== ThirdConst.ThirdPartyType.QM){
			thirdpartyType = ThirdConst.ThirdPartyType.QM;
			jumpUrl=soChildDTO.getExt();
		}

		//当订单内商品全部为仅支持现金支付，或用户无可用积分（即积分全部冻结或无积分）时，[积分支付]按钮隐藏
		vo.setOperateStatus(judgeChildOrderOperateStatus(requestVO.getClientId(),so.getId(),so.getOrderPayStatus(), so.getOrderConfirmStatus(),
				soChildDTO.getDeliveryStatus(), thirdpartyType,so.getLimitCashPayAmount(),so.getOrderAmount(),requestVO.getUserId(),requestVO.getPlatformId(),so.getOrderStatus(),soChildDTO));

		if(so.getOrderStatus()==4){
			//如果是加价购查询优惠券类型
			if(so.getSaleWay()==8){
				vo.setOperateStatus(6);
				List<ExchangeOrderRecordDTO> list=exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(so.getOrderCode());
				if(EmptyUtil.isEmpty(list)||list.size()>1){
					logger.info("[该订单号数据有误,订单详情]orderCode="+so.getOrderCode());
					throw new BusinessException("该订单号数据有误");
				}
				vo.setExchangeCouponType(list.get(0).getNewCouponType());
			}
		}
		logger.info("子订单订单操作状态:operateStatus={},childCode={}", new Object[]{vo.getOperateStatus(), vo.getOrderCode()});
		//logger.debug("子订单订单操作状态:operateStatus={},childCode={}", new Object[]{vo.getOperateStatus(), vo.getOrderCode()});

		// 电子卡券类商品没有查看物流和确认收货按钮的特殊处理(话费充值类/券仓已特殊处理)
		if (!flag && ((vo.getOperateStatus().equals(Integer.valueOf(2))
				|| vo.getOperateStatus().equals(Integer.valueOf(3))))) {
			logger.debug("订单操作状态:电子卡券类商品没有查看物流和确认收货按钮的特殊处理,id=" + vo.getId());
			vo.setOperateStatus(4);
		}

		if(!buyTypeFlag){
			if(vo.getOperateStatus()==0){
				vo.setOperateStatus(8);
			}
		}
		if (Objects.equals(thirdpartyType,ThirdConst.ThirdPartyType.QM)){
			vo.setOperateStatus(9);//清美订单，直接跳转到清美页面，操作按钮不显示
			vo.setJumpUrl(jumpUrl);
		}
		// 查询订单的优惠卷信息
		vo.setCouponType(this.findCouponUnitByOrder(so));

		resultList.add(vo);
	}

	public List<SoDTO> getSoByIds(List<Long> soIds){
		return soReadService.getSoByIds(soIds);
	}


	@Resource
	private SoRefundNewManage soRefundNewManage;

	/**
	 * 取消订单
	 *
	 * @param order
	 * @throws AlipayApiException
	 */
	public void cancelChildOrderWithTx(SoDTO order, String ip, Long userId, String userName, String mac,
								  String soRefundCodeByFubi, String soRefundCodeByCash,String soRefundCodeByJiDian,SoChildDTO soChildDTO, HttpServletRequest req,String soRefundCodeByBuyCard) {
		if(order.getOrderStatus() == OrderConstant.ORDER_STATUS_UNPAY.getStatus() || order.getOrderStatus() == OrderConstant.ORDER_STATUS_CANCELED.getStatus()){
			throw new BusinessException("该订单的状态是已取消或待付款，不能退款");
		}
		BigDecimal amount = soChildDTO.getAmount();
		if(soChildDTO.getRefundAmount() !=null){
			amount = amount.subtract(soChildDTO.getRefundAmount());
		}
		if(amount.compareTo(BigDecimal.ZERO) <=0){
			throw new BusinessException("该订单的可退金额为0，不能退款");
		}
		List<SoItemDTO> items = soItemReadService.findSoItemsBySoChild(soChildDTO.getId());
		for (SoItemDTO itemDTO:items){
			BigDecimal canRefundAmount = itemDTO.getPrice().multiply(new BigDecimal(itemDTO.getPuCount())).setScale(2);
			int subCount = itemDTO.getPuCount();
			if(itemDTO.getRefundAmount() !=null){
				canRefundAmount = canRefundAmount.subtract(itemDTO.getRefundAmount());
			}
			//加上承担的运费
			if(itemDTO.getDeliveryFeeAver() !=null){
				canRefundAmount = canRefundAmount.add(itemDTO.getDeliveryFeeAver());
			}
			//减去已退运费
			if(itemDTO.getRefundDeliveryFee() !=null){
				canRefundAmount = canRefundAmount.subtract(itemDTO.getRefundDeliveryFee());
			}
			if(itemDTO.getPuCount() !=null){
				subCount = subCount - itemDTO.getRefundCount();
			}
			itemDTO.setRefundAmount(canRefundAmount);
			itemDTO.setRefundCount(subCount);
		}
		CancelAndRefundOrderExtendsWithTxDTO dto = new CancelAndRefundOrderExtendsWithTxDTO();
		dto.setReason("用户主动取消");
		dto.setOrder(order);
		dto.setUserName(userName);
		dto.setItems(items);
		dto.setReq(new HttpServletRequestDTO(req));
		dto.setUserId(userId);
		dto.setSoRefundCodeByFubi(soRefundCodeByFubi);
		dto.setSoRefundCodeByCash(soRefundCodeByCash);
		dto.setSoRefundCodeByJiDian(soRefundCodeByJiDian);
		dto.setSoRefundCodeByBuyCard(soRefundCodeByBuyCard);
		dto.setAutoRefundCash(true);
		soRefundNewManage.productRefundOrderWithTx(dto);
	}

	/**
	 * 确认收货
	 *
	 *
	 * @param platformId
	 * @return
	 */
	public String affirmOrderByChildCode(SoChildDTO childDTO, Long platformId) {
		if(EmptyUtil.isNotEmpty(childDTO.getSource()) && Objects.equals(childDTO.getSource(),ThirdConst.Source.CAKE)){
			JSONObject jsonObject = cakeUtil.confirmReceipt(null,childDTO.getChildCode());
			JsonResult rt = cakeUtil.checkResult(jsonObject);
			if(Objects.nonNull(rt)){
				logger.info("子订单三方确认收货失败:{}",JSON.toJSONString(rt));
				throw new BusinessException("三方确认收货失败"+rt.getError());
			}
		}
		SoChildDTO newSoChildDTO = new SoChildDTO();
		newSoChildDTO.setId(childDTO.getId());
		newSoChildDTO.setDeliveryStatus(3);
		int i = soChildWriteService.updateSoChildWithTx(newSoChildDTO);
		SoDTO so = soService.querySoById(childDTO.getSoId());
		if(i >0){
			// 发送订单确认状态变更消息
			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
			Map<String,String> infoMap = new HashMap<String,String>();
			infoDto.setInfoTemplateId(InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus());
			infoDto.setUserId(so.getUserId());
			infoMap.put("platformId", platformId.longValue()+"");
			infoMap.put("orderCode", childDTO.getChildCode());
			infoMap.put("orderConfirmStatus", OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus()+"");
			infoDto.setParams(infoMap);
			sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(infoDto);
		}

		List<SoChildDTO> soChildDTOList = soChildReadService.querySoChildListBySoId(childDTO.getSoId());
		boolean isChildAllAffirm = true;
		for (SoChildDTO soChildDTO : soChildDTOList) {
			if(soChildDTO.getDeliveryStatus() !=2 && soChildDTO.getCancelStatus() !=1){
				isChildAllAffirm = false;
				break;
			}
		}
		if(isChildAllAffirm){
			 soWriteService.affirmOrderBySoId(so.getId(), platformId);
			// 发送订单确认状态变更消息
			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
			Map<String,String> infoMap = new HashMap<String,String>();
			infoDto.setInfoTemplateId(InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus());
			infoDto.setUserId(so.getUserId());
			infoMap.put("platformId", platformId.longValue()+"");
			infoMap.put("orderCode", so.getOrderCode());
			infoMap.put("orderConfirmStatus", OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus()+"");
			infoDto.setParams(infoMap);
			sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(infoDto);
		}
		if(i >0){
			return "确认订单成功";
		}else{
			throw new BusinessException("确认失败");
		}
	}



	/**
	 * 判断订单操作状态 0:取消和去支付 1:仅取消 2:查看物流和确认收货 3:查看物流和删除订单 4:删除订单 5:无按钮
	 *
	 *
	 *
	 * @param clientId
	 * @param id
	 * @param orderPayStatus
	 *            订单支付状态 0:未支付、1:已支付、2:已退款
	 * @param orderConfirmStatus
	 *            订单确认状态 0:未确认，1:已确认，2:已取消 3:已完成
	 * @param scDeliveryStatus
	 * @param thirdpartyType
	 *            第三方订单类型: 0:无第三方订单  1:话费充值
	 * @return
	 */
	public Integer judgeChildOrderOperateStatus(Long clientId, Long id, Integer orderPayStatus, Integer orderConfirmStatus,
												Integer scDeliveryStatus, Integer thirdpartyType, BigDecimal limitCashPayAmount,
										   BigDecimal orderAmount, Long userId, Long platformId,Integer soOrderStatus,SoChildDTO dto) {
		logger.debug("订单操作状态判断:orderPayStatus={},orderConfirmStatus={},scDeliveryStatusList={},thirdpartyType={}",
				new Object[]{orderPayStatus, orderConfirmStatus, scDeliveryStatus, thirdpartyType});

		/*取消订单 :  0,1,7,8
		查看物流 : 	2,3
		去支付	 :	0,7,8
		删除订单 :	3,4,6
		确认收货 :	2
		查看优惠券: 6*/
		//orderConfirmStatus=订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
		//orderPayStatus = 订单支付状态 0:未支付、1:已支付、2:已退款
		//thirdpartyType
		//等于已取消
		if(orderPayStatus ==2 || dto.getCancelStatus() ==1 || soOrderStatus ==10){
			//只有删除订单按钮
			return 4;
		}
		boolean isThird = false;
		if(dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD || dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_CAKE|| dto.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_WORLD) {
			//去支付或去取消,当前未确认和未支付
			if (orderPayStatus == 0 && orderConfirmStatus == 0) {
				return 0;
			}
			isThird = true;
		}

		//物流状态：待发货或分拣中,都是未发货且不是第三方
		if(!isThird && (scDeliveryStatus ==0 || scDeliveryStatus==1)){
			//自营的可以取消
			return 1;
		}
		//物流已发货或已签收那么就只能查看物流和确认收货
		if(scDeliveryStatus ==2 ){
			return 2;
		}
		//若物流状态是已完成只能看物流和删除
		if(scDeliveryStatus ==4 || scDeliveryStatus ==3){
			return 3;
		}
		return 5;
	}

	public boolean updateOrderWithTX(SoDTO soDTO){
		soWriteService.updateOrderWithTX(soDTO);
		return true;
	}


	/**
	 * 普通顶订单创建接口 调用可参考com.egeo.components.order.business.impl.SoManageImpl
	 * 的createOrder方法
	 * @param req
	 * @param jedisUtil
	 * @param addr
	 * @param orderPayByCash
	 * @param exchangeId
	 * @param exchangeCouponUnitId
	 * @param exchangeCouponBatchId
	 * @param sodto
	 * @param sd
	 * @param soItems
	 * @param cartItemIds
	 * @param unitFlag                                  */
	public Long normalOrderCreateNew(HttpServletRequest req, JedisUtil jedisUtil, ReceiverAddressDTO addr, double orderPayByCash, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, SoDTO sodto, List<SoChildDTO> soChildDTOList, SoDeviceDTO sd, List<SoItemDTO> soItems,
								  List<Long> cartItemIds, boolean unitFlag, List<SoThirdpartyDTO> soThirdpartyDTOList, Long userId, String userName,
								  String ip, String mac, List<LimitRuleRecordDTO> limitRuleRecordList, Integer couponType,
								  Long couponUnitId, Long companyId, Map<Long, BigDecimal> deliveryMap,List<CompanyConfigDTO> configs) {

		Integer companyType = null;
		if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
			companyType = companyCoreReadService.findCompanyTypeById(companyId);
		}else {
			companyType = RuntimeContext.cacheUser().getCompanyType();
		}
		if (EmptyUtil.isNotEmpty(limitRuleRecordList)) {
			// 拼接su商品id集合
			Set<Long> standardUnitIds = new HashSet<>();
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				standardUnitIds.add(limitRuleRecordDTO.getStandardUnitId());
			}
			// 根据su商品id集合查询su商品信息
			List<StandardUnitDTO> standardUnitList = standardUnitReadService
					.findBymerchantProdId(com.egeo.utils.StringUtils.longsToStrings(new ArrayList<>(standardUnitIds)));
			// 给限购规则su序列号赋值
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				for (StandardUnitDTO standardUnitDTO : standardUnitList) {
					if (limitRuleRecordDTO.getStandardUnitId().equals(standardUnitDTO.getId())) {
						limitRuleRecordDTO
								.setStandardUnitSerialNumber(standardUnitDTO.getMerchantProductSerialNumber());
						break;
					}
				}
			}
		}
		if (companyType == 0) {
			//只有正式公司才操作库存
			// 冻结库存
			for (SoItemDTO item : soItems) {
				if(item.isThirdParty()) {
					continue;
				}
				CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
				commodityProductUnitDTO.setId(item.getPuId());
				CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
						.findCommodityProductUnitById(commodityProductUnitDTO);

				int freezeFlag;

				//创建订单时 只根据共用库存表查询是否有共用库存
				List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(contactGroupPuStockReadService.findPuIdListByPuId(item.getPuId()));

				List<CommodityProductUnitDTO> commodityProductUnitDTOs = null;
				if (puIds != null && puIds.size() > 1) {
					logger.info("当前pu:{},共用库存puIds:{},{}", item.getPuId(), puIds.size(), puIds);
					commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIds), commodityProductUnitDTO2.getSkuId());
				}

				if (commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 1) {
					freezeFlag = stockWriteService.freezeStockBatchWithTx(new FreeseOrderStockBatchDTO(item.getPuId(), item.getPuCount(),
							sodto.getOrderCode(), StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(), userId, userName,
							ip, mac, puIds, commodityProductUnitDTOs));
				} else {
					// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
					freezeFlag = stockWriteService.freezeStockWithTx(new FreezeStockWithTxDTO(item.getPuId(), item.getPuCount(),
							sodto.getOrderCode(), StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(),
							commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
							userName, ip, mac));
				}


				if (freezeFlag != 1) {
					throw new BusinessException(BusinessExceptionConstant.PRODUCT_INSUFFICIENT, "该商品库存不足");
				}

				if (unitFlag && item.getSkuId() != null) {
					int unitFreezeFlag = unitStockWriteService.freezeStockWithTx(item.getSkuId(), item.getPuCount());
					if (unitFreezeFlag != 1) {
						throw new BusinessException(BusinessExceptionConstant.PRODUCT_INSUFFICIENT, "该商品库存不足");
					}
				}
			}
			//如果不是总店的情况下更新门店pu库存
			if (sodto.getStoreId() != 1L && sodto.getStoreId() != 2L) {
				// 更新门店pu库存信息
				storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_ORDERCREATE.getStatus(), sodto, soItems));
			}
		}

		// 优惠卷相关处理
		boolean couponFlag = isUseCouponByCreateOrder(couponType, couponUnitId, sodto, soChildDTOList, soItems, userId, deliveryMap);

		// 创建订单,子订单,订单项,订单设备,更改发票,删除购物车,修改优惠卷状态为已冻结
		int i = sodto.getOrderAmount().subtract(sodto.getOrderPromotionDiscount()).compareTo(BigDecimal.ZERO);
		if (i < 0) {
			throw new BusinessException("订单实付金额不能小于0");
		}
		NewSoOrderDTO nso = new NewSoOrderDTO();
		nso.setCartItemIds(cartItemIds);
		nso.setItemList(soItems);


		//计算现金支付最小金额
		BigDecimal orderPayByCashLimit = BigDecimal.valueOf(0);
		BigDecimal compareAmount = sodto.getProductAmount().subtract(BigDecimal.valueOf(orderPayByCash)).subtract(sodto.getOrderPromotionDiscount());
		if (compareAmount.compareTo(BigDecimal.valueOf(0)) >= 0) {
			orderPayByCashLimit = BigDecimal.valueOf(orderPayByCash);
		} else {
			orderPayByCashLimit = sodto.getProductAmount().subtract(sodto.getOrderPromotionDiscount());
		}
		sodto.setLimitCashPayAmount(orderPayByCashLimit);
		nso.setSoDTO(sodto);
		nso.setSoChild(soChildDTOList);
//		nso.setSoChild(sc);
		nso.setSoDevice(sd);
		//nso.setSoThirdpartyDTO(soThirdpartyDTO);
		nso.setSoThirdpartyDTOList(soThirdpartyDTOList);
		if(EmptyUtil.isNotEmpty(limitRuleRecordList)){
			List<LimitRuleRecordDTO> limitBuyRecordList = getLimitBuyRecordList(sodto.getStoreId(),limitRuleRecordList, companyId, sodto.getPlatformId());
			nso.setLimitRuleRecordList(limitBuyRecordList);
		}
		Long orderId = soWriteService.createOrder(nso);
		sodto.setId(orderId);
		if (EmptyUtil.isNotEmpty(orderId) && EmptyUtil.isNotEmpty(sodto.getSaleWay()) && sodto.getSaleWay() == 8) {
			//创建订单成功后,如果是以旧换新补差价则创建记录表
			insertExchangeOrderRecord(userId, exchangeId, exchangeCouponUnitId, exchangeCouponBatchId, sodto.getOrderCode(), sodto.getOrderAmount());
		}

		if (couponFlag) {
			// 使用优惠卷成功,冻结已使用的优惠卷
			CouponUnitDTO couponUnit = new CouponUnitDTO();
			couponUnit.setId(couponUnitId);
			couponUnit.setCouponUnitStatus(couponType == 0 ? 2 : 1);
			couponUnit.setOrderId(orderId);
			couponUnit.setUsedTime(new Date());
			couponUnit.setUsedCount(1);
			couponUnitWriteService.updateCouponUnitWithTx(couponUnit);
		}
		Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
		ThreadPoolExecutor executor = CommonChildThreadPoolExecutor.getInstance();
		// 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
		CompletionService<JsonResult<SyncCreateThirdPartyOrderRespVO>> completionService = new ExecutorCompletionService<>(executor);
		List<Future<JsonResult<SyncCreateThirdPartyOrderRespVO>>> results = new ArrayList<>();
		Future<JsonResult<SyncCreateThirdPartyOrderRespVO>> future = null;
		List<Long> supportPerformingPartys = new ArrayList<>();
		supportPerformingPartys.add(ThirdConst.Merchant.JD);
		supportPerformingPartys.add(ThirdConst.Merchant.CAKE);
		supportPerformingPartys.add(ThirdConst.Merchant.WORLD);
		supportPerformingPartys.add(ThirdConst.Merchant.QM);
		logger.info("进入校验");
		for(SoChildDTO soChildDTO:soChildDTOList){
			Long performingParty = soChildDTO.getPerformingParty();
			if(!supportPerformingPartys.contains(performingParty)){
				continue;
			}
			SyncCreateThirdPartyOrderReqVO reqVO = new SyncCreateThirdPartyOrderReqVO();
			reqVO.setCompanyId(companyId);
			reqVO.setSoChildDTO(soChildDTO);
			reqVO.setSodto(sodto);
			reqVO.setUserId(userId);
			reqVO.setAddr(addr);
			reqVO.setConfigs(configs);
			reqVO.setOrderId(orderId);
			reqVO.setPerformingParty(performingParty);
			reqVO.setEnterpriseId(enterpriseId);
			 future = completionService.submit(new SyncCreateThirdPartyOrderThread(reqVO));
			results.add(future);
		}
		boolean isExistsFail = false;
		String errorMsg = "";
		for (Future<JsonResult<SyncCreateThirdPartyOrderRespVO>> result : results) {
				try {
					JsonResult<SyncCreateThirdPartyOrderRespVO> rt = result.get();
					if(Objects.isNull(rt)){
						errorMsg="返回无商品";
						isExistsFail = true;
						continue;
					}
					if(rt.getCode() !=0){
						errorMsg=rt.getError();
						logger.info("创建订单时推送到第三方失败，失败原因:{}",JSON.toJSONString(rt));
						isExistsFail = true;
						continue;
					}
				}catch (Exception e){
					logger.error("创建订单时推送到第三方发生异常:{}",e);
					cancelOrderJd(sodto,userId,ip,userName,mac,req);
					throw new BusinessException("创建订单时推送到第三方发生异常");
				}
		}
		if(isExistsFail){
			cancelOrderJd(sodto,userId,ip,userName,mac,req);
			throw new BusinessException("第三方下单失败"+errorMsg);
		}
		return orderId;
	}

	/**
	 * 后台查询订单不分页列表
	 * @param orderCode
	 * @param startDateTime
	 * @param endDateTime
	 * @param status
	 * @param confirmStatus
	 * @param payStatus
	 * @param paymentType
	 * @param showTest
	 * @param platformId
	 * @param page
	 * @param refundFlag            @return
	 * */
	public List<SoDTO> queryBackStageSoList(Long storeId, String orderCode, List<Long> userIds, List<Long> puIds,
												  Date startDateTime, Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus,
												  Integer paymentType, Boolean showTest, Long platformId, Pagination page, boolean refundFlag,
												  List<Long> testCompanyIds,List<Long> companyIds,Integer auditStatus,List<Long> soIds) {

		return soReadService.queryBackStageSoList(storeId,orderCode, userIds, puIds, startDateTime, endDateTime, status,
				confirmStatus, payStatus, paymentType, showTest, platformId, page, refundFlag, testCompanyIds,
				companyIds,auditStatus,soIds);
	}

	public List<SoChildDTO> getSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
													Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
													Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
													Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,
													Integer orderPayStatus,Integer auditStatus) {
		return soChildReadService.getSoChildAllListToExport(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType,
				soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,orderPayStatus,auditStatus);
	}

	public List<SoChildDTO> getSupplierSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
															Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
															Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
															Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus) {
		return soChildReadService.getSupplierSoChildAllListToExport(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType,
				soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);
	}

	public SoDTO findSoDTO(Long id,String orderCode){
		if(id !=null){
			 return soReadService.querySoById(id);
		}

		return soReadService.querySoByOrderCode(orderCode);
	}
}
