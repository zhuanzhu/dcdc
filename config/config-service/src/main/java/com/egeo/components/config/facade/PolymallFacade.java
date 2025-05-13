package com.egeo.components.config.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.DeliveryCompanyClient;
import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.DeliverOrderWithTxDTO;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildPageDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.CommodityProductUnitPageDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Component
public class PolymallFacade {
	
	private static final Map<String, String> DELIVERY_COMPANY_MAP = new HashMap<>();
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private SoChildClient soChildReadService;
	@Autowired
	private SoClient soReadService;
	@Autowired
	private ReceiverAddressClient receiverAddressReadService;
	@Autowired
	private SoItemClient soItemReadService;
	@Autowired
	private SoChildClient soChildWriteService;
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;
	@Autowired
	private DeliveryCompanyClient deliveryCompanyReadService;
	
	static {
		DELIVERY_COMPANY_MAP.put("JH_014", "SF");
		DELIVERY_COMPANY_MAP.put("JH_012", "HTKY");
		DELIVERY_COMPANY_MAP.put("JH_006", "ZTO");
		DELIVERY_COMPANY_MAP.put("JH_005", "STO");
		DELIVERY_COMPANY_MAP.put("JH_002", "YTO");
		DELIVERY_COMPANY_MAP.put("JH_003", "YD");
		DELIVERY_COMPANY_MAP.put("JH_037", "YZPY");
		DELIVERY_COMPANY_MAP.put("JH_001", "EMS");
		DELIVERY_COMPANY_MAP.put("JH_004", "HHTT");
		DELIVERY_COMPANY_MAP.put("JH_046", "JD");
		DELIVERY_COMPANY_MAP.put("JH_009", "QFKD");
		DELIVERY_COMPANY_MAP.put("JH_010", "GTO");
		DELIVERY_COMPANY_MAP.put("JH_013", "UC");
		DELIVERY_COMPANY_MAP.put("JH_011", "DBL");
		DELIVERY_COMPANY_MAP.put("JH_008", "FAST");
		DELIVERY_COMPANY_MAP.put("JH_007", "ZJS");
	}
	
	public Map<String, Object> queryProduct(CommodityProductUnitDTO dto, Pagination page) {
		Map<String, Object> resultMap = new HashMap<>();
		PageResult<CommodityProductUnitDTO> result = commodityProductUnitReadService.findMerchantPUOfPage(new CommodityProductUnitPageDTO(dto, page));
		List<Map<String, Object>> goodslist = new ArrayList<>();
		for (CommodityProductUnitDTO commodityProductUnitDTO : result.getList()) {
			Map<String, Object> goodsMap = new HashMap<>();
			goodsMap.put("platproductid", commodityProductUnitDTO.getId());
			goodsMap.put("name", commodityProductUnitDTO.getName());
//			goodsMap.put("outerid", "");
//			goodsMap.put("price", 0);
//			goodsMap.put("num", 0);
//			goodsMap.put("pictureurl", 0);
			goodsMap.put("status", convertPUStatusToPolymallStatus(commodityProductUnitDTO.getStatus()));
			List<Map<String, Object>> skus = new ArrayList<>();
			Map<String, Object> sku = new HashMap<>();
			sku.put("skuid", commodityProductUnitDTO.getSkuId());
			sku.put("skuouterid", commodityProductUnitDTO.getExternalSkuId());
			sku.put("skuname", commodityProductUnitDTO.getSkuName());
//			sku.put("skuprice", "");
//			sku.put("skuquantity", "");
			skus.add(sku);
			goodsMap.put("skus", skus);
			goodslist.add(goodsMap);
		}
		resultMap.put("totalcount", result.getTotalSize());
		resultMap.put("ishasnextpage", hasNextPage(result));
		resultMap.put("goodslist", goodslist);
		return resultMap;
	}

	public Map<String, Object> queryOrder(SoChildDTO dto, Pagination page) {
		PageResult<SoChildDTO> pageResult = soChildReadService.findMerchantChildOrderOfPage(new SoChildPageDTO(dto, page));
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> orders = new ArrayList<>();
		for (SoChildDTO sc : pageResult.getList()) {
			Map<String, Object> order = new HashMap<>();
			order.put("PlatOrderNo", sc.getChildCode());
			order.put("tradeStatus", convertOrderStatusToPolymallStatus(sc.getOrderStatus()));
//			order.put("tradeStatusdescription", "");
			order.put("tradetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sc.getCreateTime()));
//			order.put("payorderno", "");
//			order.put("country", "");
			if (sc.getReceiverAddressId() != null) {
				ReceiverAddressDTO  receiverAddress = receiverAddressReadService.findReceiverAddressById(sc.getReceiverAddressId());
				if (receiverAddress != null) {
					order.put("province", receiverAddress.getGoodReceiverProvince());
					order.put("city", receiverAddress.getGoodReceiverCity());
					order.put("area", receiverAddress.getGoodReceiverArea());
//					order.put("town", "");
					order.put("address", receiverAddress.getGoodReceiverAddress());
					order.put("zip", receiverAddress.getGoodReceiverPostcode());
					order.put("phone", receiverAddress.getGoodReceiverPhone());
					order.put("mobile", receiverAddress.getGoodReceiverMobile());
					order.put("receivername", receiverAddress.getGoodReceiverName());
					order.put("nick", System.currentTimeMillis() + "_" + RandomUtils.nextInt(100));
				}
			}
//			order.put("email", "");
			order.put("customerremark", sc.getRemark());
//			order.put("sellerremark", "");
//			order.put("postfee", "");
//			order.put("goodsfee", "");
			order.put("totalmoney", "");
			order.put("favourablemoney", "");
//			order.put("commissionvalue", "");
//			order.put("taxamount", "");
//			order.put("tariffamount", "");
//			order.put("addedvalueamount", "");
//			order.put("consumptiondutyamount", "");
//			order.put("sendstyle", "");
//			order.put("qq", "");
//			order.put("paytime", "");
//			order.put("invoicetitle", "");
//			order.put("taxpayerident", "");
//			order.put("invoicetype", "");
//			order.put("invoicecontent", "");
//			order.put("registeredaddress", "");
//			order.put("registeredphone", "");
//			order.put("depositbank", "");
//			order.put("bankaccount", "");
//			order.put("codservicefee", "");
//			order.put("currencycode", "");
//			order.put("cardtype", "");
//			order.put("idcard", "");
//			order.put("idcardtruename", "");
//			order.put("whsecode", "");
//			order.put("IsHwgFlag", "");
//			order.put("ShouldPayType", "");
			
			order.put("goodinfos", findOrderGoodsInfo(sc));
			orders.add(order);
		}
		resultMap.put("orders", orders);
		resultMap.put("numtotalorder", pageResult.getTotalSize());
		return resultMap;
	}
	
	private List<Map<String, Object>> findOrderGoodsInfo(SoChildDTO soChildDTO) {
		List<Map<String, Object>> goodinfos = new ArrayList<>();
		SoItemDTO findSoItem = new SoItemDTO();
		findSoItem.setSoChildId(soChildDTO.getId());
		List<SoItemDTO> soItemList = soItemReadService.findAll(findSoItem);
		if (soItemList != null) {
			for (SoItemDTO soItem : soItemList) {
				Map<String, Object> goodinfo = new HashMap<>();
				goodinfo.put("ProductId", soItem.getPuId());
				goodinfo.put("suborderno", soItem.getPuId());
				goodinfo.put("tradegoodsno", soItem.getExternalSkuId());
				goodinfo.put("tradegoodsname", soItem.getPuName());
//				goodinfo.put("tradegoodsname", "");
//				goodinfo.put("tradegoodsspec", "");
				goodinfo.put("goodscount", soItem.getPuCount());
//				goodinfo.put("price", "");
//				goodinfo.put("discountmoney", "");
//				goodinfo.put("taxamount", "");
//				goodinfo.put("refundStatus", "");
//				goodinfo.put("Status", "");
				goodinfo.put("remark", soChildDTO.getRemark());
				goodinfos.add(goodinfo);
			}
		}
		return goodinfos;
	}

	public Map<String, Object> checkOrderStatus(SoChildDTO dto) {
		List<SoChildDTO> soChildList = soChildReadService.findSoChildAll(dto);
		Map<String, Object> resultMap = new HashMap<>();
		if (soChildList != null && !soChildList.isEmpty()) {
			SoChildDTO dbDTO = soChildList.get(0);
			SoDTO so = soReadService.findSoById(dbDTO.getSoId());
			resultMap.put("refundStatus", convertOrderStatusToRefundStatus(so.getOrderConfirmStatus()));
		} else {
			throw new BusinessException("订单未找到");
		}
		return resultMap;
	}

	public Map<String, Object> deliverOrder(Map<String, String> param, Map<String, Integer> deliverMap, SoChildDTO dto) {
		String platOrderNo = param.get("PlatOrderNo");
		SoChildDTO findSoChild = new SoChildDTO();
		findSoChild.setChildCode(platOrderNo);
		List<SoChildDTO> soChildList = soChildReadService.findSoChildAll(dto);
		if (soChildList == null || soChildList.isEmpty()) {
			throw new BusinessException("订单未找到");
		}
		SoChildDTO soChild = soChildList.get(0);
		SoDTO so = soReadService.findSoById(soChild.getSoId());
		SoPackageDTO soPackage = constructSoPackage(soChild, so, param);
		soChildWriteService.deliverOrderWithTx(new DeliverOrderWithTxDTO(soChild, soPackage, deliverMap));
		return new HashMap<String, Object>();
	}

	private SoPackageDTO constructSoPackage(SoChildDTO soChild, SoDTO so, Map<String, String> param) {
		SoPackageDTO soPackage = new SoPackageDTO();
		soPackage.setUserId(so.getUserId());
		soPackage.setSoId(so.getId());
		soPackage.setSoChildId(soChild.getId());
		soPackage.setReceiverAddressId(soChild.getReceiverAddressId());
		soPackage.setOrderCode(soChild.getChildCode());
		soPackage.setSoChildDeliveryFee(soChild.getDeliveryFee());
		soPackage.setPackageType(1);
		soPackage.setDeliveryCode(param.get("LogisticNo"));
		soPackage.setDeliveryStatus(2);
		String sendType = param.get("SendType");
		//订单发货类别(自己联系物流=JH_01，在线下单=JH_02，无需物流=JH_03，自定义物流=JH_04，家装发货=JH_05，国际物流=JH_06)
		soPackage.setDeliveryMode("JH_03".equals(sendType) ? 2 : 1);
		
		String logisticType = param.get("LogisticType");
		soPackage.setDeliveryCompanyId(convertDeliveryCompany(logisticType));
		soPackage.setDeliveryCompanyName(param.get("LogisticName"));
		soPackage.setPlatformId(so.getPlatformId());
		soPackage.setMerchantId(soChild.getPerformingParty());
		return soPackage;
	}
	
	
	public Map<String, Object> syncStock(Map<String, String> param) {
		Long puId = Long.parseLong(param.get("PlatProductID"));
		Long quantity = Long.parseLong(param.get("Quantity"));
		CommodityProductUnitDTO puDTO = commodityProductUnitReadService.findSUSPUByPUId(puId);
		commodityProductUnitWarehouseStockWriteService.syncStockWithTx(puId, quantity, puDTO.getProductUnitSerialNumber(), puDTO.getName());
		return new HashMap<String, Object>();
	}
	
	private int hasNextPage(PageResult page) {
		if (page.getTotalSize() > page.getPageNo()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 内部商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 * 外部商品状态(已上架商品=JH_01，已下架商品=JH_02，已售罄商品=JH_03，未通过审核=JH_04，审核中商品=JH_05，即将上架商品=JH_06，其他商品=JH_98，所有商品=JH_99)
	 * @return
	 */
	private String convertPUStatusToPolymallStatus(Integer status) {
		if (status == 1) {
			return "JH_06";
		}
		if (status == 2) {
			return "JH_05";
		}
		if (status == 3) {
			return "JH_01";
		}
		if (status == 4) {
			return "JH_02";
		}
		if (status == 5) {
			return "JH_04";
		}
		return "JH_98";
	}
	
	/**
	 * 内部订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 * 外部订单状态(等待买家付款=JH_01，等待卖家发货=JH_02，等待买家确认收货=JH_03，交易成功=JH_04，交易关闭=JH_05)
	 * @param status
	 * @return
	 */
	private String convertOrderStatusToPolymallStatus(Integer status) {
		if (status == 0) {
			return "JH_01";
		}
		if (status == 1 || status == 7 || status == 8) {
			return "JH_02";
		}
		if (status == 2) {
			return "JH_03";
		}
		if (status == 3 || status == 4) {
			return "JH_04";
		}
		if (status == 10 || status == 5 || status == 6) {
			return "JH_05";
		}
		return null;
	}
	
	/**
	 * 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
	 * 退款状态(没有退款=JH_07，买家已经申请退款等待卖家同意=JH_01，卖家已经同意退款等待买家退货=JH_02，
	 * 		  买家已经退货等待卖家确认收货=JH_03，卖家拒绝退款=JH_04，退款关闭=JH_05，退款成功=JH_06，其他=JH_99)
	 * 内部订单已取消对应外部退款成功，其他对应没有退款
	 * @param status
	 * @return
	 */
	private String convertOrderStatusToRefundStatus(Integer status) {
		if (status == 2) {
			return "JH_06";
		}
		return "JH_07";
	}
	
	/**
	 * 外部物流公司代码转换为内部物流公司ID
	 * @param deliveryCompanyCode
	 * @return
	 */
	private Long convertDeliveryCompany(String deliveryCompanyCode) {
		String internalCode = DELIVERY_COMPANY_MAP.get(deliveryCompanyCode);
		DeliveryCompanyDTO findDTO = new DeliveryCompanyDTO();
		findDTO.setCoding(internalCode);
		List<DeliveryCompanyDTO> dcList = deliveryCompanyReadService.findDeliveryCompanyAll(findDTO);
		if (dcList != null && !dcList.isEmpty()) {
			return dcList.get(0).getId();
		}
		return null;
	}
	
}
