package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.components.cms.po.CommodityTemplatePO;
import com.egeo.components.cms.vo.CommodityTemplateVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-23 18:28:30
 */
public class CommodityTemplateConverter {

	
	public static CommodityTemplateDTO toDTO(CommodityTemplateVO src) {
		if (src == null)
		return null;	
		CommodityTemplateDTO tar = new CommodityTemplateDTO();
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

	public static CommodityTemplateVO toVO(CommodityTemplateDTO src) {
		if (src == null)
		return null;	
		CommodityTemplateVO tar = new CommodityTemplateVO();
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


	public static List<CommodityTemplateVO> toVO(List<CommodityTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityTemplateVO> list = new ArrayList<CommodityTemplateVO>();
		for (CommodityTemplateDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CommodityTemplateDTO toDTO(CommodityTemplatePO src) {
		if (src == null)
		return null;	
		CommodityTemplateDTO tar = new CommodityTemplateDTO();
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

	public static CommodityTemplatePO toPO(CommodityTemplateDTO src) {
		if (src == null)
		return null;	
		CommodityTemplatePO tar = new CommodityTemplatePO();
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

	public static List<CommodityTemplateDTO> toDTO(List<CommodityTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityTemplateDTO> list = new ArrayList<CommodityTemplateDTO>();
		for (CommodityTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityTemplatePO> toPO(List<CommodityTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityTemplatePO> list = new ArrayList<CommodityTemplatePO>();
		for (CommodityTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	