package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.CmsPageCondition;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.components.cms.vo.CmsPageVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 16:58:48
 */
public class CmsPageConverter {

	public static CmsPageDTO toDTO(CmsPageVO src) {
		if (src == null)
		return null;	
		CmsPageDTO tar = new CmsPageDTO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());	
		tar.setPageName(src.getPageName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setClientType(src.getClientType());
		tar.setSearchType(src.getSearchType());
		tar.setTemplateName(src.getTemplateName());
		tar.setCompanyType(src.getCompanyType());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyId(src.getCompanyId());
		tar.setTemplateType(src.getTemplateType());
		tar.setIsSingle(src.getIsSingle());
		return tar;
	}

	public static CmsPageVO toVO(CmsPageDTO src) {
		if (src == null)
		return null;	
		CmsPageVO tar = new CmsPageVO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());	
		tar.setPageName(src.getPageName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setClientType(src.getClientType());
		tar.setTemplateName(src.getTemplateName());
		tar.setSearchType(src.getSearchType());
		tar.setTemplateName(src.getTemplateName());
		tar.setCompanyType(src.getCompanyType());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyId(src.getCompanyId());
		tar.setTemplateType(src.getTemplateType());
		return tar;
	}

	

	public static List<CmsPageVO> toVO(List<CmsPageDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageVO> list = new ArrayList<CmsPageVO>();
		for (CmsPageDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CmsPageDTO toDTO(CmsPagePO src) {
		if (src == null)
		return null;	
		CmsPageDTO tar = new CmsPageDTO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setPageName(src.getPageName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static CmsPagePO toPO(CmsPageDTO src) {
		if (src == null)
		return null;	
		CmsPagePO tar = new CmsPagePO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setPageName(src.getPageName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setCompanyType(src.getCompanyType());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static List<CmsPageDTO> toDTO(List<CmsPagePO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageDTO> list = new ArrayList<CmsPageDTO>();
		for (CmsPagePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsPagePO> toPO(List<CmsPageDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsPagePO> list = new ArrayList<CmsPagePO>();
		for (CmsPageDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static CmsPageDTO conditionToDTO(CmsPageCondition src) {
		if (src == null)
		return null;	
		CmsPageDTO tar = new CmsPageDTO();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setPageName(src.getPageName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setTemplateName(src.getTemplateName());
		tar.setClientType(src.getClientType());
		tar.setCompanyType(src.getCompanyType());
		tar.setTemplateType(src.getTemplateType());
		tar.setCompanyIds(src.getCompanyIds());
		tar.setSearchType(src.getSearchType());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setCompanyId(src.getCompanyId());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		return tar;
	}
	
	public static CmsPageCondition dtoToCondition(CmsPageDTO src) {
		if (src == null)
		return null;	
		CmsPageCondition tar = new CmsPageCondition();
		tar.setId(src.getId());
		tar.setTemplateId(src.getTemplateId());
		tar.setPageName(src.getPageName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setTemplateName(src.getTemplateName());
		tar.setClientType(src.getClientType());
		tar.setTemplateType(src.getTemplateType());
		tar.setCompanyIds(src.getCompanyIds());
		tar.setSearchType(src.getSearchType());
		tar.setCompanyType(src.getCompanyType());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setPageStatus(src.getPageStatus());
		tar.setCfgStatus(src.getCfgStatus());
		tar.setCompanyId(src.getCompanyId());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		tar.setIsSingle(src.getIsSingle());
		return tar;
	}
	
	public static List<CmsPageDTO> conditionToDTO(List<CmsPageCondition> srcs) {
		if (srcs == null)
			return null;
		List<CmsPageDTO> list = new ArrayList<CmsPageDTO>();
		for (CmsPageCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
}
	