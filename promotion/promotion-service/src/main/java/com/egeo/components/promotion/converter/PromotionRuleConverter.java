package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionRuleDTO;
import com.egeo.components.promotion.po.PromotionRulePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:25
 */
public class PromotionRuleConverter {
	
	public static PromotionRuleDTO toDTO(PromotionRulePO src) {
		PromotionRuleDTO tar = new PromotionRuleDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setConditionType(src.getConditionType());
		tar.setConditionValue(src.getConditionValue());
		tar.setContentType(src.getContentType());
		tar.setContentValue(src.getContentValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionRulePO toPO(PromotionRuleDTO src) {
		PromotionRulePO tar = new PromotionRulePO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setConditionType(src.getConditionType());
		tar.setConditionValue(src.getConditionValue());
		tar.setContentType(src.getContentType());
		tar.setContentValue(src.getContentValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionRuleDTO> toDTO(List<PromotionRulePO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionRuleDTO> list = new ArrayList<PromotionRuleDTO>();
		for (PromotionRulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionRulePO> toPO(List<PromotionRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionRulePO> list = new ArrayList<PromotionRulePO>();
		for (PromotionRuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	