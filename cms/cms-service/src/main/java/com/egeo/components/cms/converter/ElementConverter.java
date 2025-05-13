package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.po.ElementPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-09 09:55:33
 */
public class ElementConverter {
	
	public static ElementDTO toDTO(ElementPO src) {
		if (src == null)
		return null;	
		ElementDTO tar = new ElementDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setConfigType(src.getConfigType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTemplateId(src.getTemplateId());
		tar.setSort(src.getSort());
		return tar;
	}

	public static ElementPO toPO(ElementDTO src) {
		if (src == null)
		return null;	
		ElementPO tar = new ElementPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setConfigType(src.getConfigType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTemplateId(src.getTemplateId());
		tar.setSort(src.getSort());
		return tar;
	}

	public static List<ElementDTO> toDTO(List<ElementPO> srcs) {
		if (srcs == null)
			return null;
		List<ElementDTO> list = new ArrayList<ElementDTO>();
		for (ElementPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ElementPO> toPO(List<ElementDTO> srcs) {
		if (srcs == null)
			return null;
		List<ElementPO> list = new ArrayList<ElementPO>();
		for (ElementDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	