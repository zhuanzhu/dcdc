package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.components.order.po.LimitRuleStorePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-02-14 10:40:32
 */
public class LimitRuleStoreConverter {
	
	public static LimitRuleStoreDTO toDTO(LimitRuleStorePO src) {
		if (src == null)
		return null;	
		LimitRuleStoreDTO tar = new LimitRuleStoreDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static LimitRuleStorePO toPO(LimitRuleStoreDTO src) {
		if (src == null)
		return null;	
		LimitRuleStorePO tar = new LimitRuleStorePO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<LimitRuleStoreDTO> toDTO(List<LimitRuleStorePO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleStoreDTO> list = new ArrayList<LimitRuleStoreDTO>();
		for (LimitRuleStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleStorePO> toPO(List<LimitRuleStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleStorePO> list = new ArrayList<LimitRuleStorePO>();
		for (LimitRuleStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	