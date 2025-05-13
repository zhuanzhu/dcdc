package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.components.cms.po.CategoryTreeTemplatePO;
import com.egeo.components.cms.vo.CategoryTreeTemplateVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-23 18:00:45
 */
public class CategoryTreeTemplateConverter {

	public static CategoryTreeTemplateDTO toDTO(CategoryTreeTemplateVO src) {
		if (src == null)
		return null;	
		CategoryTreeTemplateDTO tar = new CategoryTreeTemplateDTO();
		tar.setId(src.getId());
		tar.setTemplateName(src.getTemplateName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setVersionCodeA(src.getVersionCodeA());	
		tar.setVersionCodeI(src.getVersionCodeI());	
		tar.setShowTemplate(src.getShowTemplate());	
		tar.setImgUrl(src.getImgUrl());	
		tar.setContent(src.getContent());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}
	public static CategoryTreeTemplateVO toVO(CategoryTreeTemplateDTO src) {
		if (src == null)
		return null;	
		CategoryTreeTemplateVO tar = new CategoryTreeTemplateVO();
		tar.setId(src.getId());
		tar.setTemplateName(src.getTemplateName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setVersionCodeA(src.getVersionCodeA());	
		tar.setVersionCodeI(src.getVersionCodeI());	
		tar.setShowTemplate(src.getShowTemplate());	
		tar.setImgUrl(src.getImgUrl());	
		tar.setContent(src.getContent());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}
	public static List<CategoryTreeTemplateVO> toVO(List<CategoryTreeTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeTemplateVO> list = new ArrayList<CategoryTreeTemplateVO>();
		for (CategoryTreeTemplateDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeTemplateDTO toDTO(CategoryTreeTemplatePO src) {
		if (src == null)
		return null;	
		CategoryTreeTemplateDTO tar = new CategoryTreeTemplateDTO();
		tar.setId(src.getId());
		tar.setTemplateName(src.getTemplateName());
		tar.setTemplateType(src.getTemplateType());
		tar.setVersionCodeA(src.getVersionCodeA());
		tar.setVersionCodeI(src.getVersionCodeI());
		tar.setShowTemplate(src.getShowTemplate());
		tar.setImgUrl(src.getImgUrl());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CategoryTreeTemplatePO toPO(CategoryTreeTemplateDTO src) {
		if (src == null)
		return null;	
		CategoryTreeTemplatePO tar = new CategoryTreeTemplatePO();
		tar.setId(src.getId());
		tar.setTemplateName(src.getTemplateName());
		tar.setTemplateType(src.getTemplateType());
		tar.setVersionCodeA(src.getVersionCodeA());
		tar.setVersionCodeI(src.getVersionCodeI());
		tar.setShowTemplate(src.getShowTemplate());
		tar.setImgUrl(src.getImgUrl());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CategoryTreeTemplateDTO> toDTO(List<CategoryTreeTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeTemplateDTO> list = new ArrayList<CategoryTreeTemplateDTO>();
		for (CategoryTreeTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeTemplatePO> toPO(List<CategoryTreeTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeTemplatePO> list = new ArrayList<CategoryTreeTemplatePO>();
		for (CategoryTreeTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	