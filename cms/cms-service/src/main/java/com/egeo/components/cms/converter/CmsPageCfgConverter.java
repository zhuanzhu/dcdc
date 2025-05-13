package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.po.CmsPageCfgPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-18 12:27:55
 */
public class CmsPageCfgConverter {
	
	public static CmsPageCfgDTO toDTO(CmsPageCfgPO src) {
		if (src == null)
		return null;	
		CmsPageCfgDTO tar = new CmsPageCfgDTO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setTextValue(src.getTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setParentId(src.getParentId());
		return tar;
	}

	public static CmsPageCfgPO toPO(CmsPageCfgDTO src) {
		if (src == null)
		return null;	
		CmsPageCfgPO tar = new CmsPageCfgPO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setTextValue(src.getTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setParentId(src.getParentId());
		return tar;
	}
	public static CmsPageCfgDTO conditionToDTO(CmsPageCfgCondition src) {
		if (src == null)
			return null;	
		CmsPageCfgDTO tar = new CmsPageCfgDTO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setTextValue(src.getTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCkgKeyCode(src.getCkgKeyCode());
		tar.setCkgKeyType(src.getCkgKeyType());
		tar.setParentId(src.getParentId());
		tar.setCkgKeyDescription(src.getCkgKeyDescription());
		return tar;
	}
	
	public static CmsPageCfgCondition toCondition(CmsPageCfgDTO src) {
		if (src == null)
			return null;	
		CmsPageCfgCondition tar = new CmsPageCfgCondition();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setTextValue(src.getTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCkgKeyCode(src.getCkgKeyCode());
		tar.setCkgKeyType(src.getCkgKeyType());
		tar.setParentId(src.getParentId());
		tar.setCkgKeyDescription(src.getCkgKeyDescription());
		return tar;
	}

	public static List<CmsPageCfgDTO> toDTO(List<CmsPageCfgPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCfgDTO> list = new ArrayList<CmsPageCfgDTO>();
		for (CmsPageCfgPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsPageCfgPO> toPO(List<CmsPageCfgDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCfgPO> list = new ArrayList<CmsPageCfgPO>();
		for (CmsPageCfgDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static List<CmsPageCfgDTO> conditionToDTO(List<CmsPageCfgCondition> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCfgDTO> list = new ArrayList<CmsPageCfgDTO>();
		for (CmsPageCfgCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
	public static List<CmsPageCfgCondition> toCondition(List<CmsPageCfgDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageCfgCondition> list = new ArrayList<CmsPageCfgCondition>();
		for (CmsPageCfgDTO src : srcs) {
			list.add(toCondition(src));
		}
		return list;
	}
}
	