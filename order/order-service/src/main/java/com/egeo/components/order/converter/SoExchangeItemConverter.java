package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoExchangeItemDTO;
import com.egeo.components.order.po.SoExchangeItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoExchangeItemConverter {
	
	public static SoExchangeItemDTO toDTO(SoExchangeItemPO src) {
		SoExchangeItemDTO tar = new SoExchangeItemDTO();
		tar.setId(src.getId());
		tar.setSoExchangeId(src.getSoExchangeId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMpId(src.getMpId());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setPieceworkUnit(src.getPieceworkUnit());
		tar.setProductTotalAmount(src.getProductTotalAmount());
		tar.setReturnProductItemNum(src.getReturnProductItemNum());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setPurchaseAmount(src.getPurchaseAmount());
		tar.setProductCname(src.getProductCname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setSoItemId(src.getSoItemId());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static SoExchangeItemPO toPO(SoExchangeItemDTO src) {
		SoExchangeItemPO tar = new SoExchangeItemPO();
		tar.setId(src.getId());
		tar.setSoExchangeId(src.getSoExchangeId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setMpId(src.getMpId());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setPieceworkUnit(src.getPieceworkUnit());
		tar.setProductTotalAmount(src.getProductTotalAmount());
		tar.setReturnProductItemNum(src.getReturnProductItemNum());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setPurchaseAmount(src.getPurchaseAmount());
		tar.setProductCname(src.getProductCname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setSoItemId(src.getSoItemId());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoExchangeItemDTO> toDTO(List<SoExchangeItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoExchangeItemDTO> list = new ArrayList<SoExchangeItemDTO>();
		for (SoExchangeItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoExchangeItemPO> toPO(List<SoExchangeItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoExchangeItemPO> list = new ArrayList<SoExchangeItemPO>();
		for (SoExchangeItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	