package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.components.cms.po.IconCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-24 17:30:37
 */
public class IconCompanyConverter {
	
	public static IconCompanyDTO toDTO(IconCompanyPO src) {
		if (src == null)
		return null;	
		IconCompanyDTO tar = new IconCompanyDTO();
		tar.setId(src.getId());
		tar.setIconId(src.getIconId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static IconCompanyPO toPO(IconCompanyDTO src) {
		if (src == null)
		return null;	
		IconCompanyPO tar = new IconCompanyPO();
		tar.setId(src.getId());
		tar.setIconId(src.getIconId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<IconCompanyDTO> toDTO(List<IconCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<IconCompanyDTO> list = new ArrayList<IconCompanyDTO>();
		for (IconCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<IconCompanyPO> toPO(List<IconCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<IconCompanyPO> list = new ArrayList<IconCompanyPO>();
		for (IconCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	