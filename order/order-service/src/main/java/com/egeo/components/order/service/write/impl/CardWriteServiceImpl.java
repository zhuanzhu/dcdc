package com.egeo.components.order.service.write.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.write.CardWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.StoreProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.fo.FindECardOfPageFO;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.user.client.InfoClient;
import com.egeo.components.user.client.InfoTemplateClient;
import com.egeo.components.user.client.InfoTemplateSendWayClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;


@Service("cardWriteService")
public class CardWriteServiceImpl implements CardWriteService {
	private static final XLogger logger = XLogger.getLogger(CardWriteServiceImpl.class);
	@Autowired
	private SoItemReadService soItemReadService;
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;
	@Autowired
	private SoChildWriteService soChildWriteService;
	@Autowired
	private SoWriteService soWriteService;
	/*@Autowired
	private ECardClient eCardClient;*/
	@Autowired
	private ECardClient eCardWriteService;
	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	/*@Resource(name = "sendInfoProvider")
	private Provider provider;*/

	@Autowired
	private ECardClient eCardReadService;
	/*@Autowired
	private InfoClient infoWriteService;
	@Autowired
	private InfoTemplateClient infoTemplateReadService;
	@Autowired
	private InfoTemplateSendWayClient infoTemplateSendWayReadService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockWriteService;*/
	@Autowired
	private StoreProductUnitClient storeProductUnitReadService;
	@Autowired
	private SoReadService soReadService;
	@Autowired
	private SendInfoClient sendInfoWriteService;
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
	@Autowired
	private UserClient userReadService;

	@Override
	public Boolean allocationCardAndTakeStock(Long orderId, String orderCode, Long userId, String userName, String ip,
			String mac, Integer companyType) {

		//SendInfoClient sendInfoWriteService = new SendInfoWriteServiceImpl(userReadService,provider,infoWriteService, infoTemplateReadService, infoTemplateSendWayReadService);

//		StorePuWarehouseStockService storePuWarehouseStockService = new StorePuWarehouseStockServiceImpl(storePuWarehouseStockWriteService,storeProductUnitReadService);

		SoDTO soDTO = soReadService.querySoById(orderId);
		Boolean isTrue = false;
		// 查询是否该订单是否存在unit商品
		SoItemDTO soItemDTO = new SoItemDTO();
		soItemDTO.setSoId(orderId);
		List<SoItemDTO> unitItemList = soItemReadService.findAll(soItemDTO);
		if (EmptyUtil.isNotEmpty(unitItemList)) {
			for (SoItemDTO soItemDTO2 : unitItemList) {
				// 根据puid查询pu信息
				CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
				commodityProductUnitDTO.setId(soItemDTO2.getPuId());
				CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService.findCommodityProductUnitById(commodityProductUnitDTO);
				// 如果订单项是unit商品就进行分配unit及扣除实体库存和在线库存
				if(soItemDTO2.isThirdParty()) {
//					// 修改子订单已完成状态
//					SoChildDTO soChild = new SoChildDTO();
//					soChild.setId(soItemDTO2.getSoChildId());
//					soChild.setDeliveryStatus(OrderConstant.ORDER_DELIVERY_STATUS_NO_DELIVERY.getStatus());
//					soChildWriteService.updateSoChildWithTx(soChild);
//
//					// 发货状态，0：未发货 10：部分发货 20：已发货
//					soWriteService.changeOrderStatusByOrderCode(orderCode,
//							OrderConstant.ORDER_STATUS_PAYED.getStatus(),
//							OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus(),
//							1, OrderConstant.ORDER_DELIVERY_STATUS_NO_DELIVERY.getStatus());
//					// 发送订单确认状态变更消息
//
//					InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
//					Map<String,String> infoMap = new HashMap<String,String>();
//					infoDto.setInfoTemplateId(InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus());
//					infoDto.setUserId(userId);
//					infoMap.put("platformId", soDTO.getPlatformId().longValue()+"");
//					infoMap.put("orderCode", orderCode);
//					infoMap.put("orderConfirmStatus", OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_ACCOMPLISH.getStatus()+"");
//					infoDto.setParams(infoMap);
//					sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(infoDto);
//
//
//					InsertAndSendMessageDTO infoDto2 = new InsertAndSendMessageDTO();
//					Map<String,String> infoMap2 = new HashMap<String,String>();
//					infoDto2.setInfoTemplateId(InfoConstant.ORDER_DELIVERY_STATUS_INFO_ID.getStatus());
//					infoDto2.setUserId(userId);
//					infoMap2.put("orderCode", orderCode);
//					infoMap2.put("deliveryStatus", OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus()+"");
//					infoDto2.setParams(infoMap2);
//					// 发送订单物流状态变更消息
//					sendInfoWriteService.insertOrderDeliveryStatusInfoAndSend(infoDto2);
				}else if (soItemDTO2.getUnitExist() == 1) {
					// 只有正式公司才分配unit及扣除sku实体库存
					if(companyType == 0){
						// 具体的分配unit逻辑及扣除sku实体库存逻辑
						allocationTakeUnit(commodityProductUnitDTO2.getSkuId(),soItemDTO2.getPuCount(),orderCode,userId,userName);

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
							puIdList.add(soItemDTO2.getPuId());
							commodityProductUnitDTOs.add(commodityProductUnitDTO2);
							logger.info("订单支付puIdList ：{}  商品size:{}",puIdList ,commodityProductUnitDTOs.size());

							commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(soItemDTO2.getPuId(), soItemDTO2.getPuCount(),
									StockConstant.STOCK_STATUS_PAYED.getStatus(), soDTO.getOrderCode(), userId, userName,
									null, null, puIdList, commodityProductUnitDTOs));
						}else {
							// 根据puid扣除冻结库存扣除库存
							commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(soItemDTO2.getPuId(),
									soItemDTO2.getPuCount(), StockConstant.STOCK_STATUS_PAYED.getStatus(),
									commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),
									orderCode, userId, userName, ip, mac);
						}

					}

					// 修改子订单发货状态
					SoChildDTO soChildDTO = new SoChildDTO();
					soChildDTO.setId(soItemDTO2.getSoChildId());
					soChildDTO.setDeliveryStatus(3);
					soChildWriteService.updateSoChildWithTx(soChildDTO);

					// 发货状态，0：未发货 10：部分发货 20：已发货
					soWriteService.changeOrderStatusByOrderCode(orderCode,
							OrderConstant.ORDER_STATUS_DELIVERED.getStatus(),
							OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus(),
							1, OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus());

					// 修改子订单已完成状态
					SoChildDTO soChild = new SoChildDTO();
					soChild.setId(soItemDTO2.getSoChildId());
					soChild.setDeliveryStatus(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus());
					soChildWriteService.updateSoChildWithTx(soChild);

					// 发货状态，0：未发货 10：部分发货 20：已发货
					soWriteService.changeOrderStatusByOrderCode(orderCode,
							OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(),
							OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_ACCOMPLISH.getStatus(),
							1, OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus());

					InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
					Map<String,String> infoMap = new HashMap<String,String>();
					infoDto.setInfoTemplateId(InfoConstant.ORDER_CONFIRM_STATUS_INFO_ID.getStatus());
					infoDto.setUserId(userId);
					infoMap.put("platformId", soDTO.getPlatformId().longValue()+"");
					infoMap.put("orderCode", orderCode);
					infoMap.put("orderConfirmStatus", OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_ACCOMPLISH.getStatus()+"");
					infoDto.setParams(infoMap);
					sendInfoWriteService.insertOrderConfirmStatusInfoAndSend(infoDto);


					InsertAndSendMessageDTO infoDto2 = new InsertAndSendMessageDTO();
					Map<String,String> infoMap2 = new HashMap<String,String>();
					infoDto2.setInfoTemplateId(InfoConstant.ORDER_DELIVERY_STATUS_INFO_ID.getStatus());
					infoDto2.setUserId(userId);
					infoMap2.put("orderCode", orderCode);
					infoMap2.put("deliveryStatus", OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus()+"");
					infoDto2.setParams(infoMap2);
					// 发送订单物流状态变更消息
					sendInfoWriteService.insertOrderDeliveryStatusInfoAndSend(infoDto2);

				}
				// 如果不是根据puid扣除冻结库存扣除库存
				else {
					// 根据puid扣除冻结库存扣除库存
					if(companyType == 0){
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
						if(commodityProductUnitDTO2!=null) {
							List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());

							if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
								puIdList.add(soItemDTO2.getPuId());
								commodityProductUnitDTOs.add(commodityProductUnitDTO2);
								logger.info("订单支付puIdList ：{}  商品size:{}",puIdList ,commodityProductUnitDTOs.size());
								commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(soItemDTO2.getPuId(), soItemDTO2.getPuCount(),
										StockConstant.STOCK_STATUS_PAYED.getStatus(), soDTO.getOrderCode(), userId, userName,
										null, null, puIdList, commodityProductUnitDTOs));
							}else {
								commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(soItemDTO2.getPuId(),
										soItemDTO2.getPuCount(), StockConstant.STOCK_STATUS_PAYED.getStatus(),
										commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),
										orderCode, userId, userName, ip, mac);
							}
						}


					}
				}

			}

			// 更新门店pu库存信息
			/*if(companyType == 0){
				storePuWarehouseStockService.updateStorePuWarehouseStock(StockConstant.STOCK_STATUS_PAYED.getStatus(),soDTO,unitItemList);
			}*/

		}
		isTrue = true;
		return isTrue;
	}
	/**
	 * 具体的分配unit逻辑及扣除sku实体库存逻辑
	 * @param skuId
	 * @param puCount
	 * @param orderCode
	 * @param userId
	 * @param userName
	 */
	private void allocationTakeUnit(Long skuId, Integer puCount, String orderCode, Long userId, String userName) {
		// 根据skuId查询未过期未分配的unit
		ECardDTO eCardDTO = new ECardDTO();
		eCardDTO.setSkuId(skuId);
		eCardDTO.setIsValid(1);
		eCardDTO.setIsAllocation(0);
		Pagination page = new Pagination();
		page.setPageSize(puCount);
		page.setOrderBy("end_time");
		PageResult<ECardDTO> pageResult = eCardReadService.findECardOfPage(new FindECardOfPageFO(eCardDTO, page));
		if(pageResult.getList().size() < puCount){
			logger.info("skuId:" + skuId + "unit库存不足");
			throw new BusinessException("unit库存不足");
		}
		List<ECardDTO> eCardList = pageResult.getList();
		for (ECardDTO eCardDTO2 : eCardList) {
			// 根据unitId分配unit
			ECardDTO cardDTO = new ECardDTO();
			cardDTO.setId(eCardDTO2.getId());
			cardDTO.setOrderCode(orderCode);
			cardDTO.setIsAllocation(1);
			cardDTO.setAllocationTime(new Date());
			cardDTO.setUserId(userId);
			cardDTO.setUserLoginName(userName);
			eCardWriteService.updateECardWithTx(cardDTO);
		}

		// 根据skuid扣除冻结库存扣除库存
		merchantProductVirtualStockWriteService.unfreezeAndDeductStockWithTx(skuId, puCount);

	}

}

