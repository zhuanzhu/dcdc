package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoModifyPriceItemDTO;
import com.egeo.components.order.po.SoModifyPriceItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoModifyPriceItemConverter {
	
	public static SoModifyPriceItemDTO toDTO(SoModifyPriceItemPO src) {
		SoModifyPriceItemDTO tar = new SoModifyPriceItemDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setMpId(src.getMpId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductItemAmountAfter(src.getProductItemAmountAfter());
		tar.setProductItemDiscountAmount(src.getProductItemDiscountAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductPriceDiscount(src.getProductPriceDiscount());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setCode(src.getCode());
		tar.setUnit(src.getUnit());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setProductSaleType(src.getProductSaleType());
		tar.setModifyId(src.getModifyId());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static SoModifyPriceItemPO toPO(SoModifyPriceItemDTO src) {
		SoModifyPriceItemPO tar = new SoModifyPriceItemPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setMpId(src.getMpId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductItemAmountAfter(src.getProductItemAmountAfter());
		tar.setProductItemDiscountAmount(src.getProductItemDiscountAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductPriceDiscount(src.getProductPriceDiscount());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setCode(src.getCode());
		tar.setUnit(src.getUnit());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setProductSaleType(src.getProductSaleType());
		tar.setModifyId(src.getModifyId());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoModifyPriceItemDTO> toDTO(List<SoModifyPriceItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoModifyPriceItemDTO> list = new ArrayList<SoModifyPriceItemDTO>();
		for (SoModifyPriceItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoModifyPriceItemPO> toPO(List<SoModifyPriceItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoModifyPriceItemPO> list = new ArrayList<SoModifyPriceItemPO>();
		for (SoModifyPriceItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	