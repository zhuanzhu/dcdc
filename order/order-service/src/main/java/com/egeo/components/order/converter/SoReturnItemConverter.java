package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoReturnItemDTO;
import com.egeo.components.order.po.SoReturnItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoReturnItemConverter {
	
	public static SoReturnItemDTO toDTO(SoReturnItemPO src) {
		SoReturnItemDTO tar = new SoReturnItemDTO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
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

	public static SoReturnItemPO toPO(SoReturnItemDTO src) {
		SoReturnItemPO tar = new SoReturnItemPO();
		tar.setId(src.getId());
		tar.setReturnId(src.getReturnId());
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

	public static List<SoReturnItemDTO> toDTO(List<SoReturnItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnItemDTO> list = new ArrayList<SoReturnItemDTO>();
		for (SoReturnItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoReturnItemPO> toPO(List<SoReturnItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnItemPO> list = new ArrayList<SoReturnItemPO>();
		for (SoReturnItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	