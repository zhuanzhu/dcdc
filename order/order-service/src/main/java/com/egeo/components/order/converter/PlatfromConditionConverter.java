package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.PlatfromConditionDTO;
import com.egeo.components.order.po.PlatfromConditionPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class PlatfromConditionConverter {
	
	public static PlatfromConditionDTO toDTO(PlatfromConditionPO src) {
		PlatfromConditionDTO tar = new PlatfromConditionDTO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setConditionPoolId(src.getConditionPoolId());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionName(src.getConditionName());
		tar.setActive(src.getActive());
		tar.setIsDft(src.getIsDft());
		tar.setConditionType(src.getConditionType());
		tar.setLayoutSort(src.getLayoutSort());
		tar.setPlatformId(src.getPlatformId());
		tar.setDataType(src.getDataType());
		tar.setGroupName(src.getGroupName());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static PlatfromConditionPO toPO(PlatfromConditionDTO src) {
		PlatfromConditionPO tar = new PlatfromConditionPO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setConditionPoolId(src.getConditionPoolId());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionName(src.getConditionName());
		tar.setActive(src.getActive());
		tar.setIsDft(src.getIsDft());
		tar.setConditionType(src.getConditionType());
		tar.setLayoutSort(src.getLayoutSort());
		tar.setPlatformId(src.getPlatformId());
		tar.setDataType(src.getDataType());
		tar.setGroupName(src.getGroupName());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<PlatfromConditionDTO> toDTO(List<PlatfromConditionPO> srcs) {
		if (srcs == null)
			return null;
		List<PlatfromConditionDTO> list = new ArrayList<PlatfromConditionDTO>();
		for (PlatfromConditionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PlatfromConditionPO> toPO(List<PlatfromConditionDTO> srcs) {
		if (srcs == null)
			return null;
		List<PlatfromConditionPO> list = new ArrayList<PlatfromConditionPO>();
		for (PlatfromConditionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	