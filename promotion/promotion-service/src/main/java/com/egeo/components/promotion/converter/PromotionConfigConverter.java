package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionConfigDTO;
import com.egeo.components.promotion.po.PromotionConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionConfigConverter {
	
	public static PromotionConfigDTO toDTO(PromotionConfigPO src) {
		PromotionConfigDTO tar = new PromotionConfigDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleTimeConfig1(src.getRuleTimeConfig1());
		tar.setRuleTimeConfig2(src.getRuleTimeConfig2());
		tar.setRuleType(src.getRuleType());
		tar.setRuleAmount(src.getRuleAmount());
		tar.setRuleAmountExt1(src.getRuleAmountExt1());
		tar.setRuleVal(src.getRuleVal());
		tar.setRuleVal1(src.getRuleVal1());
		tar.setRuleValStr1(src.getRuleValStr1());
		tar.setRuleValStr2(src.getRuleValStr2());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionConfigPO toPO(PromotionConfigDTO src) {
		PromotionConfigPO tar = new PromotionConfigPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setRuleTimeConfig1(src.getRuleTimeConfig1());
		tar.setRuleTimeConfig2(src.getRuleTimeConfig2());
		tar.setRuleType(src.getRuleType());
		tar.setRuleAmount(src.getRuleAmount());
		tar.setRuleAmountExt1(src.getRuleAmountExt1());
		tar.setRuleVal(src.getRuleVal());
		tar.setRuleVal1(src.getRuleVal1());
		tar.setRuleValStr1(src.getRuleValStr1());
		tar.setRuleValStr2(src.getRuleValStr2());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionConfigDTO> toDTO(List<PromotionConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionConfigDTO> list = new ArrayList<PromotionConfigDTO>();
		for (PromotionConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionConfigPO> toPO(List<PromotionConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionConfigPO> list = new ArrayList<PromotionConfigPO>();
		for (PromotionConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	