package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.po.ShoppingLabelPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-08 18:06:59
 */
public class ShoppingLabelConverter {
	
	public static ShoppingLabelDTO toDTO(ShoppingLabelPO src) {
		if (src == null)
		return null;	
		ShoppingLabelDTO tar = new ShoppingLabelDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setGroupId(src.getGroupId());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ShoppingLabelPO toPO(ShoppingLabelDTO src) {
		if (src == null)
		return null;	
		ShoppingLabelPO tar = new ShoppingLabelPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setGroupId(src.getGroupId());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ShoppingLabelDTO> toDTO(List<ShoppingLabelPO> srcs) {
		if (srcs == null)
			return null;
		List<ShoppingLabelDTO> list = new ArrayList<ShoppingLabelDTO>();
		for (ShoppingLabelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ShoppingLabelPO> toPO(List<ShoppingLabelDTO> srcs) {
		if (srcs == null)
			return null;
		List<ShoppingLabelPO> list = new ArrayList<ShoppingLabelPO>();
		for (ShoppingLabelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	