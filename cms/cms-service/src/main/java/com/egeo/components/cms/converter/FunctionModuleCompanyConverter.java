package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.components.cms.po.FunctionModuleCompanyPO;
import com.egeo.components.cms.vo.FunctionModuleCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-26 14:56:07
 */
public class FunctionModuleCompanyConverter {

	
	public static FunctionModuleCompanyDTO toDTO(FunctionModuleCompanyVO src) {
		if (src == null)
		return null;	
		FunctionModuleCompanyDTO tar = new FunctionModuleCompanyDTO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());	
		tar.setCompanyId(src.getCompanyId());	
		return tar;
	}

	public static FunctionModuleCompanyVO toVO(FunctionModuleCompanyDTO src) {
		if (src == null)
		return null;	
		FunctionModuleCompanyVO tar = new FunctionModuleCompanyVO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());	
		tar.setCompanyId(src.getCompanyId());	
		return tar;
	}

	

	public static List<FunctionModuleCompanyVO> toVO(List<FunctionModuleCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCompanyVO> list = new ArrayList<FunctionModuleCompanyVO>();
		for (FunctionModuleCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FunctionModuleCompanyDTO toDTO(FunctionModuleCompanyPO src) {
		if (src == null)
		return null;	
		FunctionModuleCompanyDTO tar = new FunctionModuleCompanyDTO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static FunctionModuleCompanyPO toPO(FunctionModuleCompanyDTO src) {
		if (src == null)
		return null;	
		FunctionModuleCompanyPO tar = new FunctionModuleCompanyPO();
		tar.setId(src.getId());
		tar.setFunctionModuleId(src.getFunctionModuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static List<FunctionModuleCompanyDTO> toDTO(List<FunctionModuleCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCompanyDTO> list = new ArrayList<FunctionModuleCompanyDTO>();
		for (FunctionModuleCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FunctionModuleCompanyPO> toPO(List<FunctionModuleCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionModuleCompanyPO> list = new ArrayList<FunctionModuleCompanyPO>();
		for (FunctionModuleCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	