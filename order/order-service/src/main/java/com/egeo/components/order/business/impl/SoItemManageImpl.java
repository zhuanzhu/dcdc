package com.egeo.components.order.business.impl;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import lombok.val;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.business.SoItemManage;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.facade.PromotionFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoItemFacade;
import com.egeo.components.order.facade.StoreFacade;
import com.egeo.components.order.vo.SoItem;
import com.egeo.components.order.vo.SoItemVIEW;
import com.egeo.components.order.vo.SoItemVO;
import com.egeo.components.order.vo.UnitDetailVIEW;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("soItem")
public class SoItemManageImpl implements SoItemManage {

	@Resource(name = "soItemFacade")
	private SoItemFacade soItemFacade;
	@Resource(name = "soFacade")
	private SoFacade soFacade;
	@Resource(name = "promotionFacade")
	private PromotionFacade promotionFacade;
	@Resource(name = "productFacade")
	private ProductFacade productFacade;
	@Resource(name = "storeFacade")
	private StoreFacade storeFacade;

	@Override
	public Long updateSoItem(SoItemVO soItemVO) {

		return soItemFacade.updateSoItem(SoItemConverter.toDTO(soItemVO));
	}

	@Override
	public String updateSoItemAll(Long packId, List<SoItem> lists, Long platformId) {
		for (SoItem soItem : lists) {
			if (soItem.isChecked()) {
				SoItemVO soItemVO = new SoItemVO();
				soItemVO.setId(soItem.getId());
				soItemVO.setPlatformId(platformId);
				soItemFacade.updateSoItem(SoItemConverter.toDTO(soItemVO));
			}

		}
		return "批量修改订单项信息成功";
	}

	/**
	 * 根据storeId查询门店优惠方法
	 * @param storeId
	 * @return
	 */
	private Long getStoreDiscount(Long storeId) {
		if (EmptyUtil.isEmpty(storeId)) {
			throw new BusinessException("storeId不能为空");
		}
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(storeId);

		StoreDTO storeById = storeFacade.findStoreById(storeId);
		if(EmptyUtil.isEmpty(storeById)){
			throw new BusinessException(storeById + "门店id无效");
		}
		//没有优惠
		if (EmptyUtil.isEmpty(storeById.getDiscount())) {
			StoreTreeNodeDTO dto = new StoreTreeNodeDTO();
			dto.setStoreId(storeId);
			List<StoreTreeNodeDTO> storeTreeNodeAll = storeFacade.findStoreTreeNodeAll(dto);
			if (storeTreeNodeAll.size() == 0 || storeTreeNodeAll == null) {
				throw new BusinessException(storeId + "是总门店,但没有折扣率,请联系管理员");
			} else if (storeTreeNodeAll.size() > 1) {
				throw new BusinessException(storeId + "同时属于多个门店,配置有误,联系管理元");
			} else {
				this.getStoreDiscount(storeTreeNodeAll.get(0).getParentId());
			}
		}
		return storeById.getDiscount();
	}

	@Override
	public JsonResult<Map<String, Object>> findAllSoChildItemBySoId(Long soId) {
		if(soId==null)
			return JsonResult.fail("请选择子订单");
		SoDTO dto = new SoDTO();
		dto.setId(soId);
		//SoDTO soById = soFacade.findSoById(dto);
		List<SoItemDTO> dtoList = soItemFacade.querySoItemsBySoChildId(soId);
		SoChildDTO child = soFacade.findSoChildById(soId);
		if(child==null) {
			return JsonResult.fail("子订单信息错误");
		}
		Map<Long, String> soChildMap = new HashMap<>();
		soChildMap.put(child.getId(), child.getChildCode());
		List<SoItemVIEW> viewList =new ArrayList<>();
		for (SoItemDTO it : dtoList) {
			SoItemVIEW view = new SoItemVIEW();
			view.setChildId(it.getSoChildId());
			view.setChildCode(soChildMap.get(it.getSoChildId()));
			view.setSoItemId(it.getId());
			String productUnitSerialNumber = null;
			if(it.isThirdParty()) {
	           	productUnitSerialNumber = it.thirdProductUnitSerialNumber();
			}else {
				CommodityProductUnitDTO pu=productFacade.queryPuById(it.getPuId());
				if(pu==null) {
					productUnitSerialNumber = "产品已失效";
				}else {
					productUnitSerialNumber = pu.getProductUnitSerialNumber();
				}

			}

			view.setProductUnitSerialNumber(productUnitSerialNumber);
			view.setPuNameAndStandard(it.getPuName());
			view.setUnitExist(it.getUnitExist());
			view.setCartType(it.getCartType());
			view.setPuCount(it.getPuCount());
			view.setPrice(it.getPrice());
			view.setPromotionAver(it.getPromotionAver());
			view.setStoreDiscountAver(it.getStoreDiscountAver());

			view.setDeliveryFeeAver(it.getDeliveryFeeAver());;
			view.setDeliveryFeeDiscountAver(it.getDeliveryFeeDiscountAver());
			view.setAfterDiscountPriceAver(it.getAfterDiscountPriceAver().add(it.getDeliveryFeeAver()));
			BigDecimal price = new BigDecimal(0);
			if (it.getPrice() != null) {
				price = it.getPrice();
			}
			BigDecimal puCount = new BigDecimal(0);
			if (it.getPuCount() != null) {
				puCount = BigDecimal.valueOf(it.getPuCount());
			}
			view.setProductAmount(price.multiply(puCount));
			BigDecimal deliveryFee = new BigDecimal(0);
			if (it.getDeliveryFeeAver() != null) {
				deliveryFee = it.getDeliveryFeeAver();
			}
			view.setAllAmount((price.multiply(puCount).add(deliveryFee)));
			BigDecimal storeDiscount = new BigDecimal(0);
			if (it.getStoreDiscountAver() != null) {
				storeDiscount = it.getStoreDiscountAver();
			}
			BigDecimal couponDiscount = new BigDecimal(0);
			if (it.getPromotionAver() != null) {
				couponDiscount = it.getPromotionAver();
			}
			BigDecimal deliveryFeeDiscount = new BigDecimal(0);
			if (it.getDeliveryFeeDiscountAver() != null) {
				deliveryFeeDiscount = it.getDeliveryFeeDiscountAver();
			}
			view.setDiscount((storeDiscount.add(couponDiscount).add(deliveryFeeDiscount)).multiply(puCount));
			BigDecimal amt = new BigDecimal(0);
			if (it.getAfterDiscountPriceAver() != null) {
				amt = it.getAfterDiscountPriceAver();
			}
			view.setAmount((amt.multiply(puCount).add(deliveryFee)));
			view.setRefundCount(it.getRefundCount());
			view.setRefundAmount(it.getRefundAmount().add(it.getRefundDeliveryFee() !=null?it.getRefundDeliveryFee():BigDecimal.ZERO));
			viewList.add(view);
		}
		return JsonResult.success("list", viewList);
	}

	@Override
	public JsonResult<Map<String, Object>> findAllSoItemBySoId(Long soId) {
		if(soId==null)
			return JsonResult.fail("请选择订单");
		List<SoItemDTO> dtoList = soItemFacade.querySoItemListBySoId(soId);
		List<SoChildDTO> childList = soFacade.findSoChildBySoId(soId);
		Map<Long, String> soChildMap = new HashMap<>();
		for (SoChildDTO soChild : childList) {
			soChildMap.put(soChild.getId(), soChild.getChildCode());
		}
		List<SoItemVIEW> viewList =new ArrayList<>();
		for (SoItemDTO it : dtoList) {
			SoItemVIEW view = new SoItemVIEW();
			view.setChildCode(soChildMap.get(it.getSoChildId()));
			String productUnitSerialNumber = null;
			if(it.isThirdParty()) {
	           	productUnitSerialNumber = it.thirdProductUnitSerialNumber();
			}else {
				CommodityProductUnitDTO pu=productFacade.queryPuById(it.getPuId());
				if(pu==null) {
					productUnitSerialNumber = "产品已失效";
				}else {
					productUnitSerialNumber = pu.getProductUnitSerialNumber();
				}

			}

			view.setProductUnitSerialNumber(productUnitSerialNumber);
			view.setPuNameAndStandard(it.getPuName());
			view.setUnitExist(it.getUnitExist());
			view.setCartType(it.getCartType());
			view.setPuCount(it.getPuCount());
			view.setPrice(it.getPrice());
			view.setPromotionAver(it.getPromotionAver());
			view.setStoreDiscountAver(it.getStoreDiscountAver());

			view.setDeliveryFeeAver(it.getDeliveryFeeAver());;
			view.setDeliveryFeeDiscountAver(it.getDeliveryFeeDiscountAver());
			view.setAfterDiscountPriceAver(it.getAfterDiscountPriceAver().add(it.getDeliveryFeeAver()));
			BigDecimal price = new BigDecimal(0);
			if (it.getPrice() != null) {
				price = it.getPrice();
			}
			BigDecimal puCount = new BigDecimal(0);
			if (it.getPuCount() != null) {
				puCount = BigDecimal.valueOf(it.getPuCount());
			}
			view.setProductAmount(price.multiply(puCount));
			BigDecimal deliveryFee = new BigDecimal(0);
			if (it.getDeliveryFeeAver() != null) {
				deliveryFee = it.getDeliveryFeeAver();
			}
			//view.setAllAmount((price.add(deliveryFee)).multiply(puCount));
			view.setAllAmount(price.multiply(puCount).add(deliveryFee));
			BigDecimal storeDiscount = new BigDecimal(0);
			if (it.getStoreDiscountAver() != null) {
				storeDiscount = it.getStoreDiscountAver();
			}
			BigDecimal couponDiscount = new BigDecimal(0);
			if (it.getPromotionAver() != null) {
				couponDiscount = it.getPromotionAver();
			}
			BigDecimal deliveryFeeDiscount = new BigDecimal(0);
			if (it.getDeliveryFeeDiscountAver() != null) {
				deliveryFeeDiscount = it.getDeliveryFeeDiscountAver();
			}
			view.setDiscount((storeDiscount.add(couponDiscount).add(deliveryFeeDiscount)).multiply(puCount));
			BigDecimal amt = new BigDecimal(0);
			if (it.getAfterDiscountPriceAver() != null) {
				amt = it.getAfterDiscountPriceAver();
			}
			if(EmptyUtil.isNotEmpty(it.getSupplierId()) && EmptyUtil.isNotEmpty(it.getSnapshot())) {
				CommodityProductUnitDTO snap = JSON.parseObject(it.getSnapshot(), CommodityProductUnitDTO.class);
				if(snap.getSupplierPrice()!=null) {
					view.setAfterDiscountPriceAver(snap.getSupplierPrice());
				}
			}else {
				if(EmptyUtil.isNotEmpty(it.getSnapshot()) && Objects.equals(3,it.getSource())) {
					JdProductDTO snap  = JSON.parseObject(it.getSnapshot(), JdProductDTO.class);
					if(EmptyUtil.isNotEmpty(snap) && EmptyUtil.isNotEmpty(snap.getLedger())) {
						JSONObject ledgerObj = JSON.parseObject(snap.getLedger());
						if(EmptyUtil.isNotEmpty(ledgerObj) && ledgerObj.containsKey("jdPrice")) {
							view.setAfterDiscountPriceAver(ledgerObj.getBigDecimal("jdPrice"));
						}
					}
				}
				if(EmptyUtil.isNotEmpty(it.getSnapshot()) && Objects.equals(ThirdConst.Source.CAKE,it.getSource())) {
					CakeProductDetailDTO snap = JSON.parseObject(it.getSnapshot(), CakeProductDetailDTO.class);
					CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(snap,String.valueOf(it.getPuId()));
					if(specsDTO !=null){
						view.setAfterDiscountPriceAver(new BigDecimal(specsDTO.getClearing_price()).setScale(2));
					}
				}

				if(EmptyUtil.isNotEmpty(it.getSnapshot()) && Objects.equals(ThirdConst.Source.WORLD,it.getSource())) {
					ChannelProductDetailVO snap = JSON.parseObject(it.getSnapshot(), ChannelProductDetailVO.class);
					List<ChannelProductBatchDTO> batchDTOList = snap.getBatchDTOList();
					ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(it.getPuId()+"",batchDTOList);
					if(batchDTO !=null){
						view.setAfterDiscountPriceAver(batchDTO.getPriceSettleMent());
					}
				}
			}
			if (Objects.equals(ThirdConst.Source.QM,it.getSource())){
				view.setAfterDiscountPriceAver(null);
			}
			//view.setAmount((amt.add(deliveryFee)).multiply(puCount));
			view.setAmount(amt.multiply(puCount).add(deliveryFee));
			if (EmptyUtil.isNotEmpty(it.getTaxRate())){
				view.setTaxRate(it.getTaxRate()+"%");
			}
			viewList.add(view);
		}
		return JsonResult.success("list", viewList);
	}

	@Override
	public JsonResult<Map<String, Object>> findSoItemBySoIdAndUnit(Long soId) {
		// 该逻辑基于一个订单只有一种虚拟卡类建立
		if(soId==null)
			return JsonResult.fail("请选择订单");
		SoDTO so = soFacade.querySoById(soId);
		if (so == null)
			return JsonResult.fail("订单不存在");
		// 查询订单项信息
		List<SoItemDTO> items = soItemFacade.querySoItemListBySoId(soId);
		if (items.size() == 0) {
			return JsonResult.fail("订单项不存在");
		}
		SoItemDTO item = items.get(0);
		// 查询子订单信息
		SoChildDTO sc = soFacade.findSoChildById(item.getSoChildId());
		Long soChildId = sc.getId();
		// 查询pu信息
		CommodityProductUnitDTO pu = productFacade.queryPuById(item.getPuId());
		// 查询卡类
		List<ECardDTO> cards = promotionFacade.queryECardListByOrderCode(so.getOrderCode());
		List<UnitDetailVIEW> res = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for (ECardDTO card : cards) {
			UnitDetailVIEW vo = new UnitDetailVIEW();
			vo.setAllocationTime(sdf.format(card.getAllocationTime()));
			vo.setCardNo(card.getCardNumber());
			vo.setChildCode(sc.getChildCode());
			vo.setEndTime(sdf2.format(card.getEndTime()));
			vo.setPuSn(pu.getProductUnitSerialNumber());
			vo.setStartTime(sdf2.format(card.getStartTime()));
			vo.setPassWord(card.getCardThick());
			vo.setShortUrl(card.getShortUrl());
			res.add(vo);
		}
		return JsonResult.success("list", res);
	}

}
