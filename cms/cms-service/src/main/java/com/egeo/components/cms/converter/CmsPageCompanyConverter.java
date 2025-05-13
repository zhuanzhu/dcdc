package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsPageCompanyDTO;
import com.egeo.components.cms.po.CmsPageCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-01-11 14:36:43
 */
public class CmsPageCompanyConverter {
	
	public static CmsPageCompanyDTO toDTO(CmsPageCompanyPO src) {
		if (src == null)
		return null;	
		CmsPageCompanyDTO tar = new CmsPageCompanyDTO();
		tar.setId(src.getId());
		tar.setPageId(src.getPageId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsPageCompanyPO toPO(CmsPageCompanyDTO src) {
		if (src == null)
		return null;	
		CmsPageCompanyPO tar = new CmsPageCompanyPO();
		tar.setId(src.getId());
		tar.setPageId(src.getPageId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsPageCompanyDTO> toDTO(List<CmsPageCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCompanyDTO> list = new ArrayList<CmsPageCompanyDTO>();
		for (CmsPageCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsPageCompanyPO> toPO(List<CmsPageCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCompanyPO> list = new ArrayList<CmsPageCompanyPO>();
		for (CmsPageCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	