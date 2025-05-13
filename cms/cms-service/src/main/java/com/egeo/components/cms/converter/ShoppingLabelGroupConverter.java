package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.po.ShoppingLabelGroupPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-08 18:06:59
 */
public class ShoppingLabelGroupConverter {
	
	public static ShoppingLabelGroupDTO toDTO(ShoppingLabelGroupPO src) {
		if (src == null)
		return null;	
		ShoppingLabelGroupDTO tar = new ShoppingLabelGroupDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setImgUrl(src.getImgUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setInstId(src.getInstId());
		return tar;
	}

	public static ShoppingLabelGroupPO toPO(ShoppingLabelGroupDTO src) {
		if (src == null)
		return null;	
		ShoppingLabelGroupPO tar = new ShoppingLabelGroupPO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setImgUrl(src.getImgUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setInstId(src.getInstId());
		return tar;
	}

	public static List<ShoppingLabelGroupDTO> toDTO(List<ShoppingLabelGroupPO> srcs) {
		if (srcs == null)
			return null;
		List<ShoppingLabelGroupDTO> list = new ArrayList<ShoppingLabelGroupDTO>();
		for (ShoppingLabelGroupPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ShoppingLabelGroupPO> toPO(List<ShoppingLabelGroupDTO> srcs) {
		if (srcs == null)
			return null;
		List<ShoppingLabelGroupPO> list = new ArrayList<ShoppingLabelGroupPO>();
		for (ShoppingLabelGroupDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	