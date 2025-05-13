package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.po.CompanyUserDisabledPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ziyi
 * @date 2019-01-03 19:50:28
 */
public class CompanyUserDisabledConverter {
	
	public static CompanyUserDisabledDTO toDTO(CompanyUserDisabledPO src) {
		if (src == null)
		return null;	
		CompanyUserDisabledDTO tar = new CompanyUserDisabledDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUserId(src.getUserId());
		tar.setDisabledTime(src.getDisabledTime());
		tar.setRevalidation(src.getRevalidation());
		return tar;
	}

	public static CompanyUserDisabledPO toPO(CompanyUserDisabledDTO src) {
		if (src == null)
		return null;	
		CompanyUserDisabledPO tar = new CompanyUserDisabledPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUserId(src.getUserId());
		tar.setDisabledTime(src.getDisabledTime());
		tar.setRevalidation(src.getRevalidation());
		return tar;
	}

	public static List<CompanyUserDisabledDTO> toDTO(List<CompanyUserDisabledPO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyUserDisabledDTO> list = new ArrayList<CompanyUserDisabledDTO>();
		for (CompanyUserDisabledPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyUserDisabledPO> toPO(List<CompanyUserDisabledDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyUserDisabledPO> list = new ArrayList<CompanyUserDisabledPO>();
		for (CompanyUserDisabledDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	