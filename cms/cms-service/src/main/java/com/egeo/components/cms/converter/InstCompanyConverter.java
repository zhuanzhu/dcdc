package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.po.InstCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:49
 */
public class InstCompanyConverter {
	
	public static InstCompanyDTO toDTO(InstCompanyPO src) {
		if (src == null)
		return null;	
		InstCompanyDTO tar = new InstCompanyDTO();
		tar.setId(src.getId());
		tar.setInstId(src.getInstId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static InstCompanyPO toPO(InstCompanyDTO src) {
		if (src == null)
		return null;	
		InstCompanyPO tar = new InstCompanyPO();
		tar.setId(src.getId());
		tar.setInstId(src.getInstId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<InstCompanyDTO> toDTO(List<InstCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<InstCompanyDTO> list = new ArrayList<InstCompanyDTO>();
		for (InstCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InstCompanyPO> toPO(List<InstCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<InstCompanyPO> list = new ArrayList<InstCompanyPO>();
		for (InstCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	