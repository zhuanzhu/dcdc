package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionLimitDTO;
import com.egeo.components.promotion.po.PromotionLimitPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionLimitConverter {
	
	public static PromotionLimitDTO toDTO(PromotionLimitPO src) {
		PromotionLimitDTO tar = new PromotionLimitDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleRef(src.getRuleRef());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setSaleCount(src.getSaleCount());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setLimitType(src.getLimitType());
		tar.setLimitRef(src.getLimitRef());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionLimitPO toPO(PromotionLimitDTO src) {
		PromotionLimitPO tar = new PromotionLimitPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleRef(src.getRuleRef());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setSaleCount(src.getSaleCount());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setLimitType(src.getLimitType());
		tar.setLimitRef(src.getLimitRef());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionLimitDTO> toDTO(List<PromotionLimitPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionLimitDTO> list = new ArrayList<PromotionLimitDTO>();
		for (PromotionLimitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionLimitPO> toPO(List<PromotionLimitDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionLimitPO> list = new ArrayList<PromotionLimitPO>();
		for (PromotionLimitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	