package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.po.ElementDictPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-09 09:55:34
 */
public class ElementDictConverter {
	
	public static ElementDictDTO toDTO(ElementDictPO src) {
		if (src == null)
		return null;	
		ElementDictDTO tar = new ElementDictDTO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setName(src.getName());
		tar.setConfigType(src.getConfigType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		return tar;
	}

	public static ElementDictPO toPO(ElementDictDTO src) {
		if (src == null)
		return null;	
		ElementDictPO tar = new ElementDictPO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setName(src.getName());
		tar.setConfigType(src.getConfigType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		return tar;
	}

	public static List<ElementDictDTO> toDTO(List<ElementDictPO> srcs) {
		if (srcs == null)
			return null;
		List<ElementDictDTO> list = new ArrayList<ElementDictDTO>();
		for (ElementDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ElementDictPO> toPO(List<ElementDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<ElementDictPO> list = new ArrayList<ElementDictPO>();
		for (ElementDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	