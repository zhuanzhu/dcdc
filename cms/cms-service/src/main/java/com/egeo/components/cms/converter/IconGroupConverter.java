package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.po.IconGroupPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:46
 */
public class IconGroupConverter {
	
	public static IconGroupDTO toDTO(IconGroupPO src) {
		if (src == null)
		return null;	
		IconGroupDTO tar = new IconGroupDTO();
		tar.setId(src.getId());
		tar.setCount(src.getCount());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setInstId(src.getInstId());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static IconGroupPO toPO(IconGroupDTO src) {
		if (src == null)
		return null;	
		IconGroupPO tar = new IconGroupPO();
		tar.setId(src.getId());
		tar.setCount(src.getCount());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setInstId(src.getInstId());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static List<IconGroupDTO> toDTO(List<IconGroupPO> srcs) {
		if (srcs == null)
			return null;
		List<IconGroupDTO> list = new ArrayList<IconGroupDTO>();
		for (IconGroupPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<IconGroupPO> toPO(List<IconGroupDTO> srcs) {
		if (srcs == null)
			return null;
		List<IconGroupPO> list = new ArrayList<IconGroupPO>();
		for (IconGroupDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	