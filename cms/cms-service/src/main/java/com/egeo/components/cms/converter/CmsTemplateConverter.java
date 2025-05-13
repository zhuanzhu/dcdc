package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.components.cms.vo.CmsTemplateVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 16:58:47
 */
public class CmsTemplateConverter {

	public static CmsTemplateDTO toDTO(CmsTemplateVO src) {
		if (src == null)
		return null;	
		CmsTemplateDTO tar = new CmsTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setStatus(src.getStatus());	
		tar.setClientType(src.getClientType());	
		tar.setClientVersionAId(src.getClientVersionAId());	
		tar.setClientVersionIId(src.getClientVersionIId());	
		tar.setClientVersionARemark(src.getClientVersionARemark());	
		tar.setClientVersionIRemark(src.getClientVersionIRemark());	
		tar.setClientVersionACode(src.getClientVersionACode());	
		tar.setClientVersionICode(src.getClientVersionICode());	
		tar.setRemark(src.getRemark());	
		tar.setType(src.getType());	
		tar.setShowFgj(src.getShowFgj());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setElementIdList(src.getElementIdList());
		tar.setSearchType(src.getSearchType());
		tar.setTemplateElementList(src.getTemplateElementList());
		return tar;
	}

	public static CmsTemplateVO toVO(CmsTemplateDTO src) {
		if (src == null)
		return null;	
		CmsTemplateVO tar = new CmsTemplateVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setStatus(src.getStatus());	
		tar.setClientType(src.getClientType());	
		tar.setClientVersionAId(src.getClientVersionAId());	
		tar.setClientVersionIId(src.getClientVersionIId());	
		tar.setClientVersionARemark(src.getClientVersionARemark());	
		tar.setClientVersionIRemark(src.getClientVersionIRemark());	
		tar.setClientVersionACode(src.getClientVersionACode());	
		tar.setClientVersionICode(src.getClientVersionICode());	
		tar.setRemark(src.getRemark());	
		tar.setType(src.getType());	
		tar.setShowFgj(src.getShowFgj());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setElementIdList(src.getElementIdList());
		tar.setElementList(src.getElementList());
		return tar;
	}

	

	public static List<CmsTemplateVO> toVO(List<CmsTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateVO> list = new ArrayList<CmsTemplateVO>();
		for (CmsTemplateDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CmsTemplateDTO toDTO(CmsTemplatePO src) {
		if (src == null)
		return null;	
		CmsTemplateDTO tar = new CmsTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setStatus(src.getStatus());
		tar.setClientType(src.getClientType());
		tar.setClientVersionAId(src.getClientVersionAId());
		tar.setClientVersionIId(src.getClientVersionIId());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		tar.setRemark(src.getRemark());
		tar.setType(src.getType());
		tar.setShowFgj(src.getShowFgj());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsTemplatePO toPO(CmsTemplateDTO src) {
		if (src == null)
		return null;	
		CmsTemplatePO tar = new CmsTemplatePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setStatus(src.getStatus());
		tar.setClientType(src.getClientType());
		tar.setClientVersionAId(src.getClientVersionAId());
		tar.setClientVersionIId(src.getClientVersionIId());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		tar.setRemark(src.getRemark());
		tar.setType(src.getType());
		tar.setShowFgj(src.getShowFgj());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsTemplateDTO> toDTO(List<CmsTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplateDTO> list = new ArrayList<CmsTemplateDTO>();
		for (CmsTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsTemplatePO> toPO(List<CmsTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsTemplatePO> list = new ArrayList<CmsTemplatePO>();
		for (CmsTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	