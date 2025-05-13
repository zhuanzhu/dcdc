package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionDTO;
import com.egeo.components.promotion.po.PromotionPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionConverter {
	
	public static PromotionDTO toDTO(PromotionPO src) {
		PromotionDTO tar = new PromotionDTO();
		tar.setId(src.getId());
		tar.setThemeId(src.getThemeId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setPromTitle(src.getPromTitle());
		tar.setPromPlatform(src.getPromPlatform());
		tar.setPromType(src.getPromType());
		tar.setUserScope(src.getUserScope());
		tar.setJoinType(src.getJoinType());
		tar.setJoinTimesLimit(src.getJoinTimesLimit());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setPayType(src.getPayType());
		tar.setLimitType(src.getLimitType());
		tar.setContentType(src.getContentType());
		tar.setGiftRule(src.getGiftRule());
		tar.setPayTypeRule(src.getPayTypeRule());
		tar.setGiftLimit4Multy(src.getGiftLimit4Multy());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionPO toPO(PromotionDTO src) {
		PromotionPO tar = new PromotionPO();
		tar.setId(src.getId());
		tar.setThemeId(src.getThemeId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setPromTitle(src.getPromTitle());
		tar.setPromPlatform(src.getPromPlatform());
		tar.setPromType(src.getPromType());
		tar.setUserScope(src.getUserScope());
		tar.setJoinType(src.getJoinType());
		tar.setJoinTimesLimit(src.getJoinTimesLimit());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setPayType(src.getPayType());
		tar.setLimitType(src.getLimitType());
		tar.setContentType(src.getContentType());
		tar.setGiftRule(src.getGiftRule());
		tar.setPayTypeRule(src.getPayTypeRule());
		tar.setGiftLimit4Multy(src.getGiftLimit4Multy());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionDTO> toDTO(List<PromotionPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionDTO> list = new ArrayList<PromotionDTO>();
		for (PromotionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionPO> toPO(List<PromotionDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionPO> list = new ArrayList<PromotionPO>();
		for (PromotionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	