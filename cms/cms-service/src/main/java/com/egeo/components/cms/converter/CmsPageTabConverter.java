package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.CmsPageTabCondition;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.po.CmsPageTabPO;
import com.egeo.components.cms.vo.CmsPageTabVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2019-01-08 20:41:56
 */
public class CmsPageTabConverter {

	
	public static CmsPageTabDTO toDTO(CmsPageTabVO src) {
		if (src == null)
		return null;	
		CmsPageTabDTO tar = new CmsPageTabDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setValue(src.getValue());	
		tar.setDefaultValue(src.getDefaultValue());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setIconUrl(src.getIconUrl());	
		tar.setIconDefaultUrl(src.getIconDefaultUrl());	
		tar.setStatus(src.getStatus());	
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientType(src.getClientType());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static CmsPageTabVO toVO(CmsPageTabDTO src) {
		if (src == null)
		return null;	
		CmsPageTabVO tar = new CmsPageTabVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setValue(src.getValue());	
		tar.setDefaultValue(src.getDefaultValue());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setIconUrl(src.getIconUrl());	
		tar.setIconDefaultUrl(src.getIconDefaultUrl());	
		tar.setStatus(src.getStatus());	
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientType(src.getClientType());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	

	public static List<CmsPageTabVO> toVO(List<CmsPageTabDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabVO> list = new ArrayList<CmsPageTabVO>();
		for (CmsPageTabDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CmsPageTabDTO toDTO(CmsPageTabPO src) {
		if (src == null)
		return null;	
		CmsPageTabDTO tar = new CmsPageTabDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setIconUrl(src.getIconUrl());
		tar.setIconDefaultUrl(src.getIconDefaultUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CmsPageTabPO toPO(CmsPageTabDTO src) {
		if (src == null)
		return null;	
		CmsPageTabPO tar = new CmsPageTabPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setIconUrl(src.getIconUrl());
		tar.setIconDefaultUrl(src.getIconDefaultUrl());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CmsPageTabDTO> toDTO(List<CmsPageTabPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabDTO> list = new ArrayList<CmsPageTabDTO>();
		for (CmsPageTabPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsPageTabPO> toPO(List<CmsPageTabDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabPO> list = new ArrayList<CmsPageTabPO>();
		for (CmsPageTabDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static CmsPageTabDTO conditionToDTO(CmsPageTabCondition src) {
		if (src == null)
		return null;	
		CmsPageTabDTO tar = new CmsPageTabDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setIconUrl(src.getIconUrl());
		tar.setIconDefaultUrl(src.getIconDefaultUrl());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setStatus(src.getStatus());
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientType(src.getClientType());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setCompanyId(src.getCompanyId());
		tar.setPageTabCompanyId(src.getPageTabCompanyId());
		return tar;
	}

	public static CmsPageTabCondition dtoToCondition(CmsPageTabDTO src) {
		if (src == null)
		return null;	
		CmsPageTabCondition tar = new CmsPageTabCondition();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setDefaultValue(src.getDefaultValue());
		tar.setIconUrl(src.getIconUrl());
		tar.setIconDefaultUrl(src.getIconDefaultUrl());
		tar.setPageTabUrl(src.getPageTabUrl());
		tar.setStatus(src.getStatus());
		tar.setShowPlatformLogo(src.getShowPlatformLogo());
		tar.setShowClientLogo(src.getShowClientLogo());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientType(src.getClientType());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setSort(src.getSort());
		tar.setIconOnclickUrl(src.getIconOnclickUrl());
		tar.setIconOnclickDefaultUrl(src.getIconOnclickDefaultUrl());
		tar.setCompanyId(src.getCompanyId());
		tar.setPageTabCompanyId(src.getPageTabCompanyId());
		return tar;
	}
	
	public static List<CmsPageTabDTO> conditionToDTO(List<CmsPageTabCondition> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabDTO> list = new ArrayList<CmsPageTabDTO>();
		for (CmsPageTabCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static List<CmsPageTabCondition> dtoToCondition(List<CmsPageTabDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageTabCondition> list = new ArrayList<CmsPageTabCondition>();
		for (CmsPageTabDTO src : srcs) {
			list.add(dtoToCondition(src));
		}
		return list;
	}
}
	