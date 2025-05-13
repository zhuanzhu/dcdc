package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.po.DepartmentTempPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-10 15:05:32
 */
public class DepartmentTempConverter {
	
	public static DepartmentTempDTO toDTO(DepartmentTempPO src) {
		if (src == null)
		return null;	
		DepartmentTempDTO tar = new DepartmentTempDTO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());
		tar.setPath(src.getPath());
		tar.setLevel(src.getLevel());
		tar.setDepartmentName(src.getDepartmentName());
		tar.setDepartmentContent(src.getDepartmentContent());
		tar.setSortValue(src.getSortValue());
		tar.setIsSubset(src.getIsSubset());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static DepartmentTempPO toPO(DepartmentTempDTO src) {
		if (src == null)
		return null;	
		DepartmentTempPO tar = new DepartmentTempPO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());
		tar.setPath(src.getPath());
		tar.setLevel(src.getLevel());
		tar.setDepartmentName(src.getDepartmentName());
		tar.setDepartmentContent(src.getDepartmentContent());
		tar.setSortValue(src.getSortValue());
		tar.setIsSubset(src.getIsSubset());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<DepartmentTempDTO> toDTO(List<DepartmentTempPO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentTempDTO> list = new ArrayList<DepartmentTempDTO>();
		for (DepartmentTempPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DepartmentTempPO> toPO(List<DepartmentTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentTempPO> list = new ArrayList<DepartmentTempPO>();
		for (DepartmentTempDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	