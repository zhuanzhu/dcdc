package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.components.user.po.FunctionModulePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-31 17:20:15
 */
public class FunctionModuleConverter {
	
	public static FunctionModuleDTO toDTO(FunctionModulePO src) {
		if (src == null)
		return null;	
		FunctionModuleDTO tar = new FunctionModuleDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setFunctionModuleName(src.getFunctionModuleName());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static FunctionModulePO toPO(FunctionModuleDTO src) {
		if (src == null)
		return null;	
		FunctionModulePO tar = new FunctionModulePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setFunctionModuleName(src.getFunctionModuleName());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<FunctionModuleDTO> toDTO(List<FunctionModulePO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleDTO> list = new ArrayList<FunctionModuleDTO>();
		for (FunctionModulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FunctionModulePO> toPO(List<FunctionModuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModulePO> list = new ArrayList<FunctionModulePO>();
		for (FunctionModuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	