package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.po.CompanyEditRecordPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-12 15:44:59
 */
public class CompanyEditRecordConverter {
	
	public static CompanyEditRecordDTO toDTO(CompanyEditRecordPO src) {
		if (src == null)
		return null;	
		CompanyEditRecordDTO tar = new CompanyEditRecordDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setEditTime(src.getEditTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CompanyEditRecordPO toPO(CompanyEditRecordDTO src) {
		if (src == null)
		return null;	
		CompanyEditRecordPO tar = new CompanyEditRecordPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setEditTime(src.getEditTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CompanyEditRecordDTO> toDTO(List<CompanyEditRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyEditRecordDTO> list = new ArrayList<CompanyEditRecordDTO>();
		for (CompanyEditRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyEditRecordPO> toPO(List<CompanyEditRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyEditRecordPO> list = new ArrayList<CompanyEditRecordPO>();
		for (CompanyEditRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	