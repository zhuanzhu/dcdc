package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleCompanyManage {

	public FunctionModuleCompanyDTO findFunctionModuleCompanyById(FunctionModuleCompanyDTO dto);	

	public PageResult<FunctionModuleCompanyDTO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyDTO dto,Pagination page);

	public List<FunctionModuleCompanyDTO> findFunctionModuleCompanyAll(FunctionModuleCompanyDTO dto);

	Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);

	int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);

	int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);
}
	