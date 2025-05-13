package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsTemplateElementDTO;
import com.egeo.components.cms.po.CmsTemplateElementPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 20:36:02
 */
public class CmsTemplateElementConverter {
	
	public static CmsTemplateElementDTO toDTO(CmsTemplateElementPO src) {
		if (src == null)
		return null;	
		CmsTemplateElementDTO tar = new CmsTemplateElementDTO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setElementId(src.getElementId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		return tar;
	}

	public static CmsTemplateElementPO toPO(CmsTemplateElementDTO src) {
		if (src == null)
		return null;	
		CmsTemplateElementPO tar = new CmsTemplateElementPO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setElementId(src.getElementId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		return tar;
	}

	public static List<CmsTemplateElementDTO> toDTO(List<CmsTemplateElementPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateElementDTO> list = new ArrayList<CmsTemplateElementDTO>();
		for (CmsTemplateElementPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsTemplateElementPO> toPO(List<CmsTemplateElementDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateElementPO> list = new ArrayList<CmsTemplateElementPO>();
		for (CmsTemplateElementDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	