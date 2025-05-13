package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.components.cms.po.ImportTemplateDictPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-08 18:51:59
 */
public class ImportTemplateDictConverter {
	
	public static ImportTemplateDictDTO toDTO(ImportTemplateDictPO src) {
		if (src == null)
		return null;	
		ImportTemplateDictDTO tar = new ImportTemplateDictDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setUrl(src.getUrl());
		tar.setContent(src.getContent());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ImportTemplateDictPO toPO(ImportTemplateDictDTO src) {
		if (src == null)
		return null;	
		ImportTemplateDictPO tar = new ImportTemplateDictPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setUrl(src.getUrl());
		tar.setContent(src.getContent());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ImportTemplateDictDTO> toDTO(List<ImportTemplateDictPO> srcs) {
		if (srcs == null)
			return null;
		List<ImportTemplateDictDTO> list = new ArrayList<ImportTemplateDictDTO>();
		for (ImportTemplateDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ImportTemplateDictPO> toPO(List<ImportTemplateDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImportTemplateDictPO> list = new ArrayList<ImportTemplateDictPO>();
		for (ImportTemplateDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	