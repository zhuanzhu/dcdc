package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsTemplateCfgDTO;
import com.egeo.components.cms.po.CmsTemplateCfgPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2019-01-04 09:54:20
 */
public class CmsTemplateCfgConverter {
	
	public static CmsTemplateCfgDTO toDTO(CmsTemplateCfgPO src) {
		if (src == null)
		return null;	
		CmsTemplateCfgDTO tar = new CmsTemplateCfgDTO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		return tar;
	}

	public static CmsTemplateCfgPO toPO(CmsTemplateCfgDTO src) {
		if (src == null)
		return null;	
		CmsTemplateCfgPO tar = new CmsTemplateCfgPO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		return tar;
	}

	public static List<CmsTemplateCfgDTO> toDTO(List<CmsTemplateCfgPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateCfgDTO> list = new ArrayList<CmsTemplateCfgDTO>();
		for (CmsTemplateCfgPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsTemplateCfgPO> toPO(List<CmsTemplateCfgDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateCfgPO> list = new ArrayList<CmsTemplateCfgPO>();
		for (CmsTemplateCfgDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	