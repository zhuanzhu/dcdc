package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.components.order.po.LimitRuleUserPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-02-14 10:40:03
 */
public class LimitRuleUserConverter {
	
	public static LimitRuleUserDTO toDTO(LimitRuleUserPO src) {
		if (src == null)
		return null;	
		LimitRuleUserDTO tar = new LimitRuleUserDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static LimitRuleUserPO toPO(LimitRuleUserDTO src) {
		if (src == null)
		return null;	
		LimitRuleUserPO tar = new LimitRuleUserPO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static List<LimitRuleUserDTO> toDTO(List<LimitRuleUserPO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleUserDTO> list = new ArrayList<LimitRuleUserDTO>();
		for (LimitRuleUserPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleUserPO> toPO(List<LimitRuleUserDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleUserPO> list = new ArrayList<LimitRuleUserPO>();
		for (LimitRuleUserDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	