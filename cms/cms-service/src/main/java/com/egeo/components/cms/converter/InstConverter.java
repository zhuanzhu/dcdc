package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.po.InstPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:48
 */
public class InstConverter {
	
	public static InstDTO toDTO(InstPO src) {
		if (src == null)
		return null;	
		InstDTO tar = new InstDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setInstMargin(src.getInstMargin());
		tar.setElementId(src.getElementId());
		tar.setConfigType(src.getConfigType());
		tar.setPageTabId(src.getPageTabId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static InstPO toPO(InstDTO src) {
		if (src == null)
		return null;	
		InstPO tar = new InstPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setInstMargin(src.getInstMargin());
		tar.setElementId(src.getElementId());
		tar.setConfigType(src.getConfigType());
		tar.setPageTabId(src.getPageTabId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<InstDTO> toDTO(List<InstPO> srcs) {
		if (srcs == null)
			return null;
		List<InstDTO> list = new ArrayList<InstDTO>();
		for (InstPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InstPO> toPO(List<InstDTO> srcs) {
		if (srcs == null)
			return null;
		List<InstPO> list = new ArrayList<InstPO>();
		for (InstDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	