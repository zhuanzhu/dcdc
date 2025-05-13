package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-01 17:43:32
 */
public class NavigationBarCompanyConverter {
	
	public static NavigationBarCompanyDTO toDTO(NavigationBarCompanyPO src) {
		if (src == null)
		return null;	
		NavigationBarCompanyDTO tar = new NavigationBarCompanyDTO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static NavigationBarCompanyPO toPO(NavigationBarCompanyDTO src) {
		if (src == null)
		return null;	
		NavigationBarCompanyPO tar = new NavigationBarCompanyPO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<NavigationBarCompanyDTO> toDTO(List<NavigationBarCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarCompanyDTO> list = new ArrayList<NavigationBarCompanyDTO>();
		for (NavigationBarCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<NavigationBarCompanyPO> toPO(List<NavigationBarCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarCompanyPO> list = new ArrayList<NavigationBarCompanyPO>();
		for (NavigationBarCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	