package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.condition.NewSoItemCondition;
import com.egeo.components.order.condition.SoItemCondition;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.vo.SoItemVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 *
 * @author jiang
 * @date 2018-01-29 09:59:04
 */
public class SoItemConverter {

	public static SoItemDTO toDTO(SoItemVO src) {
		if (src == null)
		return null;
		SoItemDTO tar = new SoItemDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setSupplierId(src.getSupplierId());
		tar.setPrice(src.getPrice());
		tar.setSource(src.getSource());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		return tar;
	}

	public static SoItemVO toVO(SoItemDTO src) {
		if (src == null)
		return null;
		SoItemVO tar = new SoItemVO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setSupplierId(src.getSupplierId());
		tar.setPrice(src.getPrice());
		tar.setSource(src.getSource());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		return tar;
	}

	public static List<SoItemDTO> toDTOs(List<SoItemVO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemDTO> list = new ArrayList<SoItemDTO>();
		for (SoItemVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoItemVO> toVO(List<SoItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemVO> list = new ArrayList<SoItemVO>();
		for (SoItemDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static NewSoItemCondition toNewCondition(SoItemDTO src){
		if (src == null)
			return null;
		NewSoItemCondition tar = new NewSoItemCondition();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setSupplierId(src.getSupplierId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setPrice(src.getPrice());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPuName(src.getPuName());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setSource(src.getSource());
		tar.setSnapshot(src.getSnapshot());
		tar.setUnitExist(src.getUnitExist());
		tar.setSoChildCode(src.getChildCode());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setTaxRate(src.getTaxRate());
		tar.setTaxCode(src.getTaxCode());
		tar.setTaxUnit(src.getTaxUnit());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setExternalProductId(src.getExternalProductId());
		tar.setSubBizId(src.getSubBizId());
		return tar;
	}

	public static List<NewSoItemCondition> toNewCondition(List<SoItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<NewSoItemCondition> list = new ArrayList<>();
		for(SoItemDTO src:srcs){
			list.add(toNewCondition(src));
		}
		return list;
	}

	public static SoItemDTO toDTO(SoItemPO src) {
		if (src == null)
		return null;
		SoItemDTO tar = new SoItemDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setPrice(src.getPrice());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setSource(src.getSource());
		tar.setSupplierId(src.getSupplierId());
		tar.setSnapshot(src.getSnapshot());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPuName(src.getPuName());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setUnitExist(src.getUnitExist());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setTaxRate(src.getTaxRate());
		tar.setTaxCode(src.getTaxCode());
		tar.setTaxUnit(src.getTaxUnit());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setSubBizId(src.getSubBizId());
		if(EmptyUtil.isNotEmpty(src.getExternalProductId())){
			tar.setExternalProductId(src.getExternalProductId());
		}
		return tar;
	}

	public static SoItemPO toPO(SoItemDTO src) {
		if (src == null)
		return null;
		SoItemPO tar = new SoItemPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setSupplierId(src.getSupplierId());
		tar.setPrice(src.getPrice());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setSource(src.getSource());
		tar.setSnapshot(src.getSnapshot());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPuName(src.getPuName());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setUnitExist(src.getUnitExist());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setTaxRate(src.getTaxRate());
		tar.setTaxCode(src.getTaxCode());
		tar.setTaxUnit(src.getTaxUnit());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		if(EmptyUtil.isNotEmpty(src.getExternalProductId())){
			tar.setExternalProductId(src.getExternalProductId());
		}
		return tar;
	}

	public static List<SoItemDTO> toDTO(List<SoItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemDTO> list = new ArrayList<SoItemDTO>();
		for (SoItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoItemPO> toPO(List<SoItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemPO> list = new ArrayList<SoItemPO>();
		for (SoItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static SoItemDTO conditionToDTO(SoItemCondition src) {
		if (src == null)
		return null;
		SoItemDTO tar = new SoItemDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setSupplierId(src.getSupplierId());
		tar.setSource(src.getSource());
		tar.setSnapshot(src.getSnapshot());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setPrice(src.getPrice());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPuName(src.getPuName());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setUnitExist(src.getUnitExist());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setAfterDiscountPriceAver(src.getAfterDiscountPriceAver());
		tar.setDeliveryFeeAver(src.getDeliveryFeeAver());
		tar.setStoreDiscountAver(src.getStoreDiscountAver());
		tar.setDeliveryFeeDiscountAver(src.getDeliveryFeeDiscountAver());
		tar.setOrderCode(src.getOrderCode());
		tar.setTaxRate(src.getTaxRate());
		tar.setTaxCode(src.getTaxCode());
		tar.setTaxUnit(src.getTaxUnit());
		tar.setRefundCount(src.getRefundCount());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		return tar;
	}

	public static List<SoItemDTO> conditionToDTO(List<SoItemCondition> srcs) {
		if (srcs == null)
			return null;
		List<SoItemDTO> list = new ArrayList<SoItemDTO>();
		for (SoItemCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
}
