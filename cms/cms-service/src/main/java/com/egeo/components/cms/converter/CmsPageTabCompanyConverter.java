package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.po.CmsPageTabCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang
 * @date 2019-01-16 11:33:28
 */
public class CmsPageTabCompanyConverter {
	
	public static CmsPageTabCompanyDTO toDTO(CmsPageTabCompanyPO src) {
		if (src == null)
		return null;	
		CmsPageTabCompanyDTO tar = new CmsPageTabCompanyDTO();
		tar.setId(src.getId());
		tar.setPageTabId(src.getPageTabId());
		tar.setStatus(src.getStatus());
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());
		tar.setClientType(src.getClientType());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsPageTabCompanyPO toPO(CmsPageTabCompanyDTO src) {
		if (src == null)
		return null;	
		CmsPageTabCompanyPO tar = new CmsPageTabCompanyPO();
		tar.setId(src.getId());
		tar.setPageTabId(src.getPageTabId());
		tar.setStatus(src.getStatus());
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());
		tar.setClientType(src.getClientType());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsPageTabCompanyDTO> toDTO(List<CmsPageTabCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabCompanyDTO> list = new ArrayList<CmsPageTabCompanyDTO>();
		for (CmsPageTabCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsPageTabCompanyPO> toPO(List<CmsPageTabCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabCompanyPO> list = new ArrayList<CmsPageTabCompanyPO>();
		for (CmsPageTabCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	