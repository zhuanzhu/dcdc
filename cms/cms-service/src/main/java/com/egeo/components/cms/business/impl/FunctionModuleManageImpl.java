package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.FunctionModuleManage;
import com.egeo.components.cms.facade.FunctionModuleFacade;
import com.egeo.components.cms.dto.FunctionModuleDTO;
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
	public List<FunctionModuleDTO> findFunctionModuleAll(FunctionModuleDTO dto) {
		return functionModuleFacade.findFunctionModuleAll(dto);
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
	