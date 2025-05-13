package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionLimitRuleDTO;
import com.egeo.components.promotion.po.PromotionLimitRulePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionLimitRuleConverter {
	
	public static PromotionLimitRuleDTO toDTO(PromotionLimitRulePO src) {
		PromotionLimitRuleDTO tar = new PromotionLimitRuleDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setLimitType(src.getLimitType());
		tar.setLimitRef(src.getLimitRef());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setIndividualLimit(src.getIndividualLimit());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionLimitRulePO toPO(PromotionLimitRuleDTO src) {
		PromotionLimitRulePO tar = new PromotionLimitRulePO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setLimitType(src.getLimitType());
		tar.setLimitRef(src.getLimitRef());
		tar.setTotalLimit(src.getTotalLimit());
		tar.setIndividualLimit(src.getIndividualLimit());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setRuleType(src.getRuleType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionLimitRuleDTO> toDTO(List<PromotionLimitRulePO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionLimitRuleDTO> list = new ArrayList<PromotionLimitRuleDTO>();
		for (PromotionLimitRulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionLimitRulePO> toPO(List<PromotionLimitRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionLimitRulePO> list = new ArrayList<PromotionLimitRulePO>();
		for (PromotionLimitRuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	