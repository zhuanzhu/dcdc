package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.po.CmsInstPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsInstConverter {
	
	public static CmsInstDTO toDTO(CmsInstPO src) {
		if (src == null)
		return null;	
		CmsInstDTO tar = new CmsInstDTO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsElementId(src.getCmsElementId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		tar.setStatus(src.getStatus());
		return tar;
	}

	public static CmsInstPO toPO(CmsInstDTO src) {
		if (src == null)
		return null;	
		CmsInstPO tar = new CmsInstPO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsElementId(src.getCmsElementId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		tar.setStatus(src.getStatus());
		return tar;
	}

	public static List<CmsInstDTO> toDTO(List<CmsInstPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstDTO> list = new ArrayList<CmsInstDTO>();
		for (CmsInstPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsInstPO> toPO(List<CmsInstDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstPO> list = new ArrayList<CmsInstPO>();
		for (CmsInstDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	