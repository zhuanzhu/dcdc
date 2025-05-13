package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ConditionPoolDTO;
import com.egeo.components.order.po.ConditionPoolPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class ConditionPoolConverter {
	
	public static ConditionPoolDTO toDTO(ConditionPoolPO src) {
		ConditionPoolDTO tar = new ConditionPoolDTO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionName(src.getConditionName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionType(src.getConditionType());
		tar.setRule(src.getRule());
		tar.setPlatformId(src.getPlatformId());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setIsDft(src.getIsDft());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ConditionPoolPO toPO(ConditionPoolDTO src) {
		ConditionPoolPO tar = new ConditionPoolPO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionName(src.getConditionName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionType(src.getConditionType());
		tar.setRule(src.getRule());
		tar.setPlatformId(src.getPlatformId());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setIsDft(src.getIsDft());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ConditionPoolDTO> toDTO(List<ConditionPoolPO> srcs) {
		if (srcs == null)
			return null;
		List<ConditionPoolDTO> list = new ArrayList<ConditionPoolDTO>();
		for (ConditionPoolPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ConditionPoolPO> toPO(List<ConditionPoolDTO> srcs) {
		if (srcs == null)
			return null;
		List<ConditionPoolPO> list = new ArrayList<ConditionPoolPO>();
		for (ConditionPoolDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	