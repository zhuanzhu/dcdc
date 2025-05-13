package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.po.IconPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:46
 */
public class IconConverter {
	
	public static IconDTO toDTO(IconPO src) {
		if (src == null)
		return null;	
		IconDTO tar = new IconDTO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());
		tar.setLinkableId(src.getLinkableId());
		tar.setSort(src.getSort());
		tar.setSummary(src.getSummary());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static IconPO toPO(IconDTO src) {
		if (src == null)
		return null;	
		IconPO tar = new IconPO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());
		tar.setLinkableId(src.getLinkableId());
		tar.setSort(src.getSort());
		tar.setSummary(src.getSummary());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<IconDTO> toDTO(List<IconPO> srcs) {
		if (srcs == null)
			return null;
		List<IconDTO> list = new ArrayList<IconDTO>();
		for (IconPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<IconPO> toPO(List<IconDTO> srcs) {
		if (srcs == null)
			return null;
		List<IconPO> list = new ArrayList<IconPO>();
		for (IconDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	