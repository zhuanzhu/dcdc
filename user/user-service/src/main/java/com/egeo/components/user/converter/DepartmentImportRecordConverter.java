package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.components.user.po.DepartmentImportRecordPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-10 01:11:50
 */
public class DepartmentImportRecordConverter {
	
	public static DepartmentImportRecordDTO toDTO(DepartmentImportRecordPO src) {
		if (src == null)
		return null;	
		DepartmentImportRecordDTO tar = new DepartmentImportRecordDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setFileOrder(src.getFileOrder());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static DepartmentImportRecordPO toPO(DepartmentImportRecordDTO src) {
		if (src == null)
		return null;	
		DepartmentImportRecordPO tar = new DepartmentImportRecordPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setFileOrder(src.getFileOrder());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<DepartmentImportRecordDTO> toDTO(List<DepartmentImportRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentImportRecordDTO> list = new ArrayList<DepartmentImportRecordDTO>();
		for (DepartmentImportRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DepartmentImportRecordPO> toPO(List<DepartmentImportRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentImportRecordPO> list = new ArrayList<DepartmentImportRecordPO>();
		for (DepartmentImportRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	