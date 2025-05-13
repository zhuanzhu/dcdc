package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.FunctionModuleManage;
import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.components.user.facade.FunctionModuleFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModule")
public class FunctionModuleManageImpl implements FunctionModuleManage{

	
	@Resource(name="functionModuleFacade")
	private FunctionModuleFacade functionModuleFacade;

	@Override
	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto) {
		return functionModuleFacade.findFunctionModuleById(dto);
	}

	@Override
	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto, Pagination page) {
		return functionModuleFacade.findFunctionModuleOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findFunctionModuleAll(FunctionModuleDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<FunctionModuleDTO> list = functionModuleFacade.findFunctionModuleAll(dto);
		for (FunctionModuleDTO functionModuleDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", functionModuleDTO.getId());
			map.put("functionModuleName", functionModuleDTO.getFunctionModuleName());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Long insertFunctionModuleWithTx(FunctionModuleDTO dto) {
		return functionModuleFacade.insertFunctionModuleWithTx(dto);
	}

	@Override
	public int updateFunctionModuleWithTx(FunctionModuleDTO dto) {
		return functionModuleFacade.updateFunctionModuleWithTx(dto);
	}

	@Override
	public int deleteFunctionModuleWithTx(FunctionModuleDTO dto) {
		return functionModuleFacade.deleteFunctionModuleWithTx(dto);
	}


}
	