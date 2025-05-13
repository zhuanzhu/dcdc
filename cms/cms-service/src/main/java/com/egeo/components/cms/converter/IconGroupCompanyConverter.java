package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.IconGroupCompanyDTO;
import com.egeo.components.cms.po.IconGroupCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:47
 */
public class IconGroupCompanyConverter {
	
	public static IconGroupCompanyDTO toDTO(IconGroupCompanyPO src) {
		if (src == null)
		return null;	
		IconGroupCompanyDTO tar = new IconGroupCompanyDTO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static IconGroupCompanyPO toPO(IconGroupCompanyDTO src) {
		if (src == null)
		return null;	
		IconGroupCompanyPO tar = new IconGroupCompanyPO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<IconGroupCompanyDTO> toDTO(List<IconGroupCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<IconGroupCompanyDTO> list = new ArrayList<IconGroupCompanyDTO>();
		for (IconGroupCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<IconGroupCompanyPO> toPO(List<IconGroupCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<IconGroupCompanyPO> list = new ArrayList<IconGroupCompanyPO>();
		for (IconGroupCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	