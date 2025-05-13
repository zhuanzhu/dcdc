package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.components.user.po.DepartmentRelationPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-03 04:17:15
 */
public class DepartmentRelationConverter {
	
	public static DepartmentRelationDTO toDTO(DepartmentRelationPO src) {
		if (src == null)
		return null;	
		DepartmentRelationDTO tar = new DepartmentRelationDTO();
		tar.setId(src.getId());
		tar.setPid(src.getPid());
		tar.setDepartmentId(src.getDepartmentId());
		return tar;
	}

	public static DepartmentRelationPO toPO(DepartmentRelationDTO src) {
		if (src == null)
		return null;	
		DepartmentRelationPO tar = new DepartmentRelationPO();
		tar.setId(src.getId());
		tar.setPid(src.getPid());
		tar.setDepartmentId(src.getDepartmentId());
		return tar;
	}

	public static List<DepartmentRelationDTO> toDTO(List<DepartmentRelationPO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentRelationDTO> list = new ArrayList<DepartmentRelationDTO>();
		for (DepartmentRelationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DepartmentRelationPO> toPO(List<DepartmentRelationDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentRelationPO> list = new ArrayList<DepartmentRelationPO>();
		for (DepartmentRelationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	