package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionMerchantDTO;
import com.egeo.components.promotion.po.PromotionMerchantPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:25
 */
public class PromotionMerchantConverter {
	
	public static PromotionMerchantDTO toDTO(PromotionMerchantPO src) {
		PromotionMerchantDTO tar = new PromotionMerchantDTO();
		tar.setId(src.getId());
		tar.setThemeId(src.getThemeId());
		tar.setPromotionId(src.getPromotionId());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantIndicator(src.getMerchantIndicator());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionMerchantPO toPO(PromotionMerchantDTO src) {
		PromotionMerchantPO tar = new PromotionMerchantPO();
		tar.setId(src.getId());
		tar.setThemeId(src.getThemeId());
		tar.setPromotionId(src.getPromotionId());
		tar.setMerchantId(src.getMerchantId());
		tar.setMerchantIndicator(src.getMerchantIndicator());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionMerchantDTO> toDTO(List<PromotionMerchantPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionMerchantDTO> list = new ArrayList<PromotionMerchantDTO>();
		for (PromotionMerchantPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionMerchantPO> toPO(List<PromotionMerchantDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionMerchantPO> list = new ArrayList<PromotionMerchantPO>();
		for (PromotionMerchantDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	