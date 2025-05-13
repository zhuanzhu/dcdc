package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsInstCompanyDTO;
import com.egeo.components.cms.po.CmsInstCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-01-11 16:12:55
 */
public class CmsInstCompanyConverter {
	
	public static CmsInstCompanyDTO toDTO(CmsInstCompanyPO src) {
		if (src == null)
		return null;	
		CmsInstCompanyDTO tar = new CmsInstCompanyDTO();
		tar.setId(src.getId());
		tar.setInstId(src.getInstId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsInstCompanyPO toPO(CmsInstCompanyDTO src) {
		if (src == null)
		return null;	
		CmsInstCompanyPO tar = new CmsInstCompanyPO();
		tar.setId(src.getId());
		tar.setInstId(src.getInstId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsInstCompanyDTO> toDTO(List<CmsInstCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstCompanyDTO> list = new ArrayList<CmsInstCompanyDTO>();
		for (CmsInstCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsInstCompanyPO> toPO(List<CmsInstCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstCompanyPO> list = new ArrayList<CmsInstCompanyPO>();
		for (CmsInstCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	