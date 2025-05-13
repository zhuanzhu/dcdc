package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DoItemDTO;
import com.egeo.components.order.po.DoItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:13
 */
public class DoItemConverter {
	
	public static DoItemDTO toDTO(DoItemPO src) {
		DoItemDTO tar = new DoItemDTO();
		tar.setId(src.getId());
		tar.setDoCode(src.getDoCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProductId(src.getProductId());
		tar.setMpId(src.getMpId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setProductVersionNo(src.getProductVersionNo());
		tar.setProductPriceOriginal(src.getProductPriceOriginal());
		tar.setProductPriceMarket(src.getProductPriceMarket());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setProductType(src.getProductType());
		tar.setVirtualStockStatus(src.getVirtualStockStatus());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static DoItemPO toPO(DoItemDTO src) {
		DoItemPO tar = new DoItemPO();
		tar.setId(src.getId());
		tar.setDoCode(src.getDoCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProductId(src.getProductId());
		tar.setMpId(src.getMpId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setProductVersionNo(src.getProductVersionNo());
		tar.setProductPriceOriginal(src.getProductPriceOriginal());
		tar.setProductPriceMarket(src.getProductPriceMarket());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setProductType(src.getProductType());
		tar.setVirtualStockStatus(src.getVirtualStockStatus());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<DoItemDTO> toDTO(List<DoItemPO> srcs) {
		if (srcs == null)
			return null;
		List<DoItemDTO> list = new ArrayList<DoItemDTO>();
		for (DoItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DoItemPO> toPO(List<DoItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<DoItemPO> list = new ArrayList<DoItemPO>();
		for (DoItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	