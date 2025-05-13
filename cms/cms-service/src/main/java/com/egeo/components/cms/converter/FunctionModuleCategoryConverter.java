package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.cms.po.FunctionModuleCategoryPO;
import com.egeo.components.cms.vo.FunctionModuleCategoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-29 09:29:45
 */
public class FunctionModuleCategoryConverter {

	
	public static FunctionModuleCategoryDTO toDTO(FunctionModuleCategoryVO src) {
		if (src == null)
		return null;	
		FunctionModuleCategoryDTO tar = new FunctionModuleCategoryDTO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		return tar;
	}

	public static FunctionModuleCategoryVO toVO(FunctionModuleCategoryDTO src) {
		if (src == null)
		return null;	
		FunctionModuleCategoryVO tar = new FunctionModuleCategoryVO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		return tar;
	}


	public static List<FunctionModuleCategoryVO> toVO(List<FunctionModuleCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCategoryVO> list = new ArrayList<FunctionModuleCategoryVO>();
		for (FunctionModuleCategoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FunctionModuleCategoryDTO toDTO(FunctionModuleCategoryPO src) {
		if (src == null)
		return null;	
		FunctionModuleCategoryDTO tar = new FunctionModuleCategoryDTO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		return tar;
	}

	public static FunctionModuleCategoryPO toPO(FunctionModuleCategoryDTO src) {
		if (src == null)
		return null;	
		FunctionModuleCategoryPO tar = new FunctionModuleCategoryPO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		return tar;
	}

	public static List<FunctionModuleCategoryDTO> toDTO(List<FunctionModuleCategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCategoryDTO> list = new ArrayList<FunctionModuleCategoryDTO>();
		for (FunctionModuleCategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FunctionModuleCategoryPO> toPO(List<FunctionModuleCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCategoryPO> list = new ArrayList<FunctionModuleCategoryPO>();
		for (FunctionModuleCategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	