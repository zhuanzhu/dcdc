package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.CmsInstCfgCondition;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.po.CmsInstCfgPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-14 17:57:13
 */
public class CmsInstCfgConverter {
	
	public static CmsInstCfgDTO toDTO(CmsInstCfgPO src) {
		if (src == null)
		return null;	
		CmsInstCfgDTO tar = new CmsInstCfgDTO();
		tar.setId(src.getId());
		tar.setCmsInstId(src.getCmsInstId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setInstTextValue(src.getInstTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setParentId(src.getParentId());
		return tar;
	}
	
	public static CmsInstCfgDTO conditionToDTO(CmsInstCfgCondition src) {
		if (src == null)
		return null;	
		CmsInstCfgDTO tar = new CmsInstCfgDTO();
		tar.setId(src.getId());
		tar.setCmsInstId(src.getCmsInstId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setInstTextValue(src.getInstTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCmsElementId(src.getCmsElementId());
		tar.setCkgKeyCode(src.getCkgKeyCode());
		tar.setCkgKeyType(src.getCkgKeyType());
		tar.setSort(src.getSort());
		tar.setParentId(src.getParentId());
		tar.setCkgKeyDescription(src.getCkgKeyDescription());
		tar.setInstId(src.getInstId());
		tar.setInstStatus(src.getInstStatus());
		return tar;
	}
	
	public static CmsInstCfgPO toPO(CmsInstCfgDTO src) {
		if (src == null)
		return null;	
		CmsInstCfgPO tar = new CmsInstCfgPO();
		tar.setId(src.getId());
		tar.setCmsInstId(src.getCmsInstId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setInstTextValue(src.getInstTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setParentId(src.getParentId());
		return tar;
	}
	public static CmsInstCfgCondition toCondition(CmsInstCfgDTO src) {
		if (src == null)
			return null;	
		CmsInstCfgCondition tar = new CmsInstCfgCondition();
		tar.setId(src.getId());
		tar.setCmsInstId(src.getCmsInstId());
		tar.setCmsCfgValueCode(src.getCmsCfgValueCode());
		tar.setInstTextValue(src.getInstTextValue());
		tar.setCmsCfgKeyId(src.getCmsCfgKeyId());
		tar.setBannerId(src.getBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCmsElementId(src.getCmsElementId());
		tar.setCkgKeyCode(src.getCkgKeyCode());
		tar.setCkgKeyType(src.getCkgKeyType());
		tar.setSort(src.getSort());
		tar.setParentId(src.getParentId());
		tar.setCkgKeyDescription(src.getCkgKeyDescription());
		tar.setInstId(src.getInstId());
		tar.setInstStatus(src.getInstStatus());
		return tar;
	}

	public static List<CmsInstCfgDTO> toDTO(List<CmsInstCfgPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstCfgDTO> list = new ArrayList<CmsInstCfgDTO>();
		for (CmsInstCfgPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	
	public static List<CmsInstCfgDTO> conditionToDTO(List<CmsInstCfgCondition> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstCfgDTO> list = new ArrayList<CmsInstCfgDTO>();
		for (CmsInstCfgCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static List<CmsInstCfgPO> toPO(List<CmsInstCfgDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsInstCfgPO> list = new ArrayList<CmsInstCfgPO>();
		for (CmsInstCfgDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	