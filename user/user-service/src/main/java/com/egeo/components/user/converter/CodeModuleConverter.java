package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.components.user.po.CodeModulePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-31 17:20:15
 */
public class CodeModuleConverter {
	
	public static CodeModuleDTO toDTO(CodeModulePO src) {
		if (src == null)
		return null;	
		CodeModuleDTO tar = new CodeModuleDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCodeModuleName(src.getCodeModuleName());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CodeModulePO toPO(CodeModuleDTO src) {
		if (src == null)
		return null;	
		CodeModulePO tar = new CodeModulePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCodeModuleName(src.getCodeModuleName());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CodeModuleDTO> toDTO(List<CodeModulePO> srcs) {
		if (srcs == null)
			return null;
		List<CodeModuleDTO> list = new ArrayList<CodeModuleDTO>();
		for (CodeModulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CodeModulePO> toPO(List<CodeModuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<CodeModulePO> list = new ArrayList<CodeModulePO>();
		for (CodeModuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	