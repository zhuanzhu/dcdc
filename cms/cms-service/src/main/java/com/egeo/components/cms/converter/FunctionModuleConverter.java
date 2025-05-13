package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.components.cms.po.FunctionModulePO;
import com.egeo.components.cms.vo.FunctionModuleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-26 14:56:07
 */
public class FunctionModuleConverter {

	public static FunctionModuleDTO toDTO(FunctionModuleVO src) {
		if (src == null)
		return null;	
		FunctionModuleDTO tar = new FunctionModuleDTO();
		tar.setId(src.getId());
		tar.setFunctionName(src.getFunctionName());	
		tar.setType(src.getType());	
		tar.setPath(src.getPath());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static FunctionModuleVO toVO(FunctionModuleDTO src) {
		if (src == null)
		return null;	
		FunctionModuleVO tar = new FunctionModuleVO();
		tar.setId(src.getId());
		tar.setFunctionName(src.getFunctionName());	
		tar.setType(src.getType());	
		tar.setPath(src.getPath());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<FunctionModuleVO> toVO(List<FunctionModuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleVO> list = new ArrayList<FunctionModuleVO>();
		for (FunctionModuleDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FunctionModuleDTO toDTO(FunctionModulePO src) {
		if (src == null)
		return null;	
		FunctionModuleDTO tar = new FunctionModuleDTO();
		tar.setId(src.getId());
		tar.setFunctionName(src.getFunctionName());
		tar.setType(src.getType());
		tar.setPath(src.getPath());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setSortValue(src.getSortValue());
		tar.setIsShow(src.getIsShow());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static FunctionModulePO toPO(FunctionModuleDTO src) {
		if (src == null)
		return null;	
		FunctionModulePO tar = new FunctionModulePO();
		tar.setId(src.getId());
		tar.setFunctionName(src.getFunctionName());
		tar.setType(src.getType());
		tar.setPath(src.getPath());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setSortValue(src.getSortValue());
		tar.setIsShow(src.getIsShow());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
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
	