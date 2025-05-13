package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.components.user.po.CompanyCallCenterPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-14 12:40:00
 */
public class CompanyCallCenterConverter {
	
	public static CompanyCallCenterDTO toDTO(CompanyCallCenterPO src) {
		if (src == null)
		return null;	
		CompanyCallCenterDTO tar = new CompanyCallCenterDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCallCenterPhone(src.getCallCenterPhone());
		tar.setCompanyQq(src.getCompanyQq());
		tar.setCompanyWeixin(src.getCompanyWeixin());
		return tar;
	}

	public static CompanyCallCenterPO toPO(CompanyCallCenterDTO src) {
		if (src == null)
		return null;	
		CompanyCallCenterPO tar = new CompanyCallCenterPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCallCenterPhone(src.getCallCenterPhone());
		tar.setCompanyQq(src.getCompanyQq());
		tar.setCompanyWeixin(src.getCompanyWeixin());
		return tar;
	}

	public static List<CompanyCallCenterDTO> toDTO(List<CompanyCallCenterPO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyCallCenterDTO> list = new ArrayList<CompanyCallCenterDTO>();
		for (CompanyCallCenterPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyCallCenterPO> toPO(List<CompanyCallCenterDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyCallCenterPO> list = new ArrayList<CompanyCallCenterPO>();
		for (CompanyCallCenterDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	