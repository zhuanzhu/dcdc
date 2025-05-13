package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.components.cms.po.ExternalLinkPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-17 16:46:43
 */
public class ExternalLinkConverter {
	
	public static ExternalLinkDTO toDTO(ExternalLinkPO src) {
		if (src == null)
		return null;	
		ExternalLinkDTO tar = new ExternalLinkDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setExternalLinkUrl(src.getExternalLinkUrl());
		tar.setLinkType(src.getLinkType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ExternalLinkPO toPO(ExternalLinkDTO src) {
		if (src == null)
		return null;	
		ExternalLinkPO tar = new ExternalLinkPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setExternalLinkUrl(src.getExternalLinkUrl());
		tar.setLinkType(src.getLinkType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ExternalLinkDTO> toDTO(List<ExternalLinkPO> srcs) {
		if (srcs == null)
			return null;
		List<ExternalLinkDTO> list = new ArrayList<ExternalLinkDTO>();
		for (ExternalLinkPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExternalLinkPO> toPO(List<ExternalLinkDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExternalLinkPO> list = new ArrayList<ExternalLinkPO>();
		for (ExternalLinkDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	