package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.components.user.vo.DepartmentVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-20 11:06:04
 */
public class DepartmentConverter {
	
	public static DepartmentDTO toDTO(DepartmentPO src) {
		if (src == null)
		return null;	
		DepartmentDTO tar = new DepartmentDTO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());
		tar.setPath(src.getPath());
		tar.setDepartmentName(src.getDepartmentName());
		tar.setDepartmentContent(src.getDepartmentContent());
		tar.setLevel(src.getLevel());
		tar.setSortValue(src.getSortValue());
		tar.setIsSubset(src.getIsSubset());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static DepartmentPO toPO(DepartmentDTO src) {
		if (src == null)
		return null;	
		DepartmentPO tar = new DepartmentPO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());
		tar.setPath(src.getPath());
		tar.setDepartmentName(src.getDepartmentName());
		tar.setDepartmentContent(src.getDepartmentContent());
		tar.setLevel(src.getLevel());
		tar.setSortValue(src.getSortValue());
		tar.setIsSubset(src.getIsSubset());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<DepartmentDTO> toDTO(List<DepartmentPO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		for (DepartmentPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DepartmentPO> toPO(List<DepartmentDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentPO> list = new ArrayList<DepartmentPO>();
		for (DepartmentDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static DepartmentDTO toDTO(DepartmentVO src) {
		if (src == null)
		return null;	
		DepartmentDTO tar = new DepartmentDTO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());	
		tar.setPath(src.getPath());	
		tar.setDepartmentName(src.getDepartmentName());	
		tar.setDepartmentContent(src.getDepartmentContent());
		tar.setLevel(src.getLevel());
		tar.setSortValue(src.getSortValue());	
		tar.setIsSubset(src.getIsSubset());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static DepartmentVO toVO(DepartmentDTO src) {
		if (src == null)
		return null;	
		DepartmentVO tar = new DepartmentVO();
		tar.setId(src.getId());
		tar.setPId(src.getPId());	
		tar.setPath(src.getPath());	
		tar.setDepartmentName(src.getDepartmentName());	
		tar.setDepartmentContent(src.getDepartmentContent());	
		tar.setSortValue(src.getSortValue());
		tar.setLevel(src.getLevel());
		tar.setIsSubset(src.getIsSubset());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<DepartmentVO> toVO(List<DepartmentDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentVO> list = new ArrayList<DepartmentVO>();
		for (DepartmentDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	