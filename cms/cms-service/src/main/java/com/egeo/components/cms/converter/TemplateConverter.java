package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.po.TemplatePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:51
 */
public class TemplateConverter {
	
	public static TemplateDTO toDTO(TemplatePO src) {
		if (src == null)
		return null;	
		TemplateDTO tar = new TemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setStatus(src.getStatus());
		tar.setClientType(src.getClientType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setRemark(src.getRemark());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setShowFgj(src.getShowFgj());
		tar.setCompanyType(src.getCompanyType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static TemplatePO toPO(TemplateDTO src) {
		if (src == null)
		return null;	
		TemplatePO tar = new TemplatePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setStatus(src.getStatus());
		tar.setClientType(src.getClientType());
		tar.setClientVersionA(src.getClientVersionA());
		tar.setClientVersionI(src.getClientVersionI());
		tar.setRemark(src.getRemark());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setShowFgj(src.getShowFgj());
		tar.setCompanyType(src.getCompanyType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<TemplateDTO> toDTO(List<TemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<TemplateDTO> list = new ArrayList<TemplateDTO>();
		for (TemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TemplatePO> toPO(List<TemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<TemplatePO> list = new ArrayList<TemplatePO>();
		for (TemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	