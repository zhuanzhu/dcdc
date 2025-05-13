package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionPriceDTO;
import com.egeo.components.promotion.po.PromotionPricePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 18:33:06
 */
public class PromotionPriceConverter {
	
	public static PromotionPriceDTO toDTO(PromotionPricePO src) {
		PromotionPriceDTO tar = new PromotionPriceDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setPromPrice(src.getPromPrice());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static PromotionPricePO toPO(PromotionPriceDTO src) {
		PromotionPricePO tar = new PromotionPricePO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setPromPrice(src.getPromPrice());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<PromotionPriceDTO> toDTO(List<PromotionPricePO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionPriceDTO> list = new ArrayList<PromotionPriceDTO>();
		for (PromotionPricePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionPricePO> toPO(List<PromotionPriceDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionPricePO> list = new ArrayList<PromotionPricePO>();
		for (PromotionPriceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	