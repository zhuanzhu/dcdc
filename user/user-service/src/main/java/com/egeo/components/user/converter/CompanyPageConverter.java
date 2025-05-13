package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.po.CompanyPagePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-13 16:31:40
 */
public class CompanyPageConverter {
	
	public static CompanyPageDTO toDTO(CompanyPagePO src) {
		if (src == null)
		return null;	
		CompanyPageDTO tar = new CompanyPageDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCompanyId(src.getCompanyId());
		tar.setShowOrNot(src.getShowOrNot());
		return tar;
	}

	public static CompanyPagePO toPO(CompanyPageDTO src) {
		if (src == null)
		return null;	
		CompanyPagePO tar = new CompanyPagePO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCompanyId(src.getCompanyId());
		tar.setShowOrNot(src.getShowOrNot());
		return tar;
	}

	public static List<CompanyPageDTO> toDTO(List<CompanyPagePO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyPageDTO> list = new ArrayList<CompanyPageDTO>();
		for (CompanyPagePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyPagePO> toPO(List<CompanyPageDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyPagePO> list = new ArrayList<CompanyPagePO>();
		for (CompanyPageDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	