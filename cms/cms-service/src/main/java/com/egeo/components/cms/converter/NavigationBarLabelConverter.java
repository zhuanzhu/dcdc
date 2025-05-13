package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.NavigationBarLabelDTO;
import com.egeo.components.cms.po.NavigationBarLabelPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-01 17:43:32
 */
public class NavigationBarLabelConverter {
	
	public static NavigationBarLabelDTO toDTO(NavigationBarLabelPO src) {
		if (src == null)
		return null;	
		NavigationBarLabelDTO tar = new NavigationBarLabelDTO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setNavigationLabelId(src.getNavigationLabelId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static NavigationBarLabelPO toPO(NavigationBarLabelDTO src) {
		if (src == null)
		return null;	
		NavigationBarLabelPO tar = new NavigationBarLabelPO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setNavigationLabelId(src.getNavigationLabelId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<NavigationBarLabelDTO> toDTO(List<NavigationBarLabelPO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarLabelDTO> list = new ArrayList<NavigationBarLabelDTO>();
		for (NavigationBarLabelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<NavigationBarLabelPO> toPO(List<NavigationBarLabelDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarLabelPO> list = new ArrayList<NavigationBarLabelPO>();
		for (NavigationBarLabelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	