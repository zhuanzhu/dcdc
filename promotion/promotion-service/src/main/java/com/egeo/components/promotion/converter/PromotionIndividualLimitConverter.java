package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionIndividualLimitDTO;
import com.egeo.components.promotion.po.PromotionIndividualLimitPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionIndividualLimitConverter {
	
	public static PromotionIndividualLimitDTO toDTO(PromotionIndividualLimitPO src) {
		PromotionIndividualLimitDTO tar = new PromotionIndividualLimitDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleRef(src.getRuleRef());
		tar.setCustomerId(src.getCustomerId());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setSaleCount(src.getSaleCount());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionIndividualLimitPO toPO(PromotionIndividualLimitDTO src) {
		PromotionIndividualLimitPO tar = new PromotionIndividualLimitPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleRef(src.getRuleRef());
		tar.setCustomerId(src.getCustomerId());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setSaleCount(src.getSaleCount());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionIndividualLimitDTO> toDTO(List<PromotionIndividualLimitPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionIndividualLimitDTO> list = new ArrayList<PromotionIndividualLimitDTO>();
		for (PromotionIndividualLimitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionIndividualLimitPO> toPO(List<PromotionIndividualLimitDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionIndividualLimitPO> list = new ArrayList<PromotionIndividualLimitPO>();
		for (PromotionIndividualLimitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	