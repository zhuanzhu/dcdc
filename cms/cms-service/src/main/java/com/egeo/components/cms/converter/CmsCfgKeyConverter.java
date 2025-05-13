package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.CmsCfgKeyCondition;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.po.CmsCfgKeyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsCfgKeyConverter {
	
	public static CmsCfgKeyDTO toDTO(CmsCfgKeyPO src) {
		if (src == null)
		return null;	
		CmsCfgKeyDTO tar = new CmsCfgKeyDTO();
		tar.setId(src.getId());
		tar.setElementId(src.getElementId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUnits(src.getUnits());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setVerifyRegex(src.getVerifyRegex());
		tar.setCfgGroup(src.getCfgGroup());
		tar.setDescription(src.getDescription());
		tar.setCfgKind(src.getCfgKind());
		tar.setParentId(src.getParentId());
		tar.setLabel(src.getLabel());
		tar.setMandatory(src.getMandatory());
		tar.setMandatoryCondition(src.getMandatoryCondition());
		return tar;
	}

	public static CmsCfgKeyPO toPO(CmsCfgKeyDTO src) {
		if (src == null)
		return null;	
		CmsCfgKeyPO tar = new CmsCfgKeyPO();
		tar.setId(src.getId());
		tar.setElementId(src.getElementId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUnits(src.getUnits());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setVerifyRegex(src.getVerifyRegex());
		tar.setCfgGroup(src.getCfgGroup());
		tar.setDescription(src.getDescription());
		tar.setCfgKind(src.getCfgKind());
		tar.setParentId(src.getParentId());
		tar.setLabel(src.getLabel());
		tar.setMandatory(src.getMandatory());
		tar.setMandatoryCondition(src.getMandatoryCondition());
		return tar;
	}
	
	public static CmsCfgKeyDTO conditionToDTO(CmsCfgKeyCondition src) {
		if (src == null)
			return null;	
		CmsCfgKeyDTO tar = new CmsCfgKeyDTO();
		tar.setId(src.getId());
		tar.setElementId(src.getElementId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setElementSort(src.getElementSort());
		tar.setTemplateId(src.getTemplateId());
		tar.setUnits(src.getUnits());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setVerifyRegex(src.getVerifyRegex());
		tar.setTemplateId(src.getTemplateId());
		tar.setCfgGroup(src.getCfgGroup());
		tar.setDescription(src.getDescription());
		tar.setCfgKind(src.getCfgKind());
		tar.setParentId(src.getParentId());
		tar.setLabel(src.getLabel());
		tar.setMandatory(src.getMandatory());
		tar.setMandatoryCondition(src.getMandatoryCondition());
		return tar;
	}
	
	public static CmsCfgKeyCondition toCondition(CmsCfgKeyDTO src) {
		if (src == null)
			return null;	
		CmsCfgKeyCondition tar = new CmsCfgKeyCondition();
		tar.setId(src.getId());
		tar.setElementId(src.getElementId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setElementSort(src.getElementSort());
		tar.setTemplateId(src.getTemplateId());
		tar.setUnits(src.getUnits());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setVerifyRegex(src.getVerifyRegex());
		tar.setTemplateId(src.getTemplateId());
		tar.setCfgGroup(src.getCfgGroup());
		tar.setDescription(src.getDescription());
		tar.setCfgKind(src.getCfgKind());
		tar.setParentId(src.getParentId());
		tar.setLabel(src.getLabel());
		tar.setMandatory(src.getMandatory());
		tar.setMandatoryCondition(src.getMandatoryCondition());
		return tar;
	}

	public static List<CmsCfgKeyDTO> toDTO(List<CmsCfgKeyPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgKeyDTO> list = new ArrayList<CmsCfgKeyDTO>();
		for (CmsCfgKeyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsCfgKeyPO> toPO(List<CmsCfgKeyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgKeyPO> list = new ArrayList<CmsCfgKeyPO>();
		for (CmsCfgKeyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static List<CmsCfgKeyDTO> conditionToDTO(List<CmsCfgKeyCondition> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgKeyDTO> list = new ArrayList<CmsCfgKeyDTO>();
		for (CmsCfgKeyCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
	public static List<CmsCfgKeyCondition> toCondition(List<CmsCfgKeyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgKeyCondition> list = new ArrayList<CmsCfgKeyCondition>();
		for (CmsCfgKeyDTO src : srcs) {
			list.add(toCondition(src));
		}
		return list;
	}
}
	