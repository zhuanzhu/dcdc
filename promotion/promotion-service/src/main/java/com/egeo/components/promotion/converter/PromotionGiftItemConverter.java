package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionGiftItemDTO;
import com.egeo.components.promotion.po.PromotionGiftItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionGiftItemConverter {
	
	public static PromotionGiftItemDTO toDTO(PromotionGiftItemPO src) {
		PromotionGiftItemDTO tar = new PromotionGiftItemDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setGiftRef(src.getGiftRef());
		tar.setGiftType(src.getGiftType());
		tar.setGiftLevel(src.getGiftLevel());
		tar.setLevelRef(src.getLevelRef());
		tar.setUpgradeAmount(src.getUpgradeAmount());
		tar.setExtendRef(src.getExtendRef());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionGiftItemPO toPO(PromotionGiftItemDTO src) {
		PromotionGiftItemPO tar = new PromotionGiftItemPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setGiftRef(src.getGiftRef());
		tar.setGiftType(src.getGiftType());
		tar.setGiftLevel(src.getGiftLevel());
		tar.setLevelRef(src.getLevelRef());
		tar.setUpgradeAmount(src.getUpgradeAmount());
		tar.setExtendRef(src.getExtendRef());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionGiftItemDTO> toDTO(List<PromotionGiftItemPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionGiftItemDTO> list = new ArrayList<PromotionGiftItemDTO>();
		for (PromotionGiftItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionGiftItemPO> toPO(List<PromotionGiftItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionGiftItemPO> list = new ArrayList<PromotionGiftItemPO>();
		for (PromotionGiftItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	