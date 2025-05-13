package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.FunctionModuleCompanyManage;
import com.egeo.components.cms.facade.FunctionModuleCompanyFacade;
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleCompany")
public class FunctionModuleCompanyManageImpl implements FunctionModuleCompanyManage{

	
	@Resource(name="functionModuleCompanyFacade")
	private FunctionModuleCompanyFacade functionModuleCompanyFacade;

	@Override
	public FunctionModuleCompanyDTO findFunctionModuleCompanyById(FunctionModuleCompanyDTO dto) {
		return functionModuleCompanyFacade.findFunctionModuleCompanyById(dto);
	}

	@Override
	public PageResult<FunctionModuleCompanyDTO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyDTO dto, Pagination page) {
		return functionModuleCompanyFacade.findFunctionModuleCompanyOfPage(dto, page);
	}

	@Override
	public List<FunctionModuleCompanyDTO> findFunctionModuleCompanyAll(FunctionModuleCompanyDTO dto) {
		return functionModuleCompanyFacade.findFunctionModuleCompanyAll(dto);
	}

	@Override
	public Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		return functionModuleCompanyFacade.insertFunctionModuleCompanyWithTx(dto);
	}

	@Override
	public int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		return functionModuleCompanyFacade.updateFunctionModuleCompanyWithTx(dto);
	}

	@Override
	public int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		return functionModuleCompanyFacade.deleteFunctionModuleCompanyWithTx(dto);
	}


}
	