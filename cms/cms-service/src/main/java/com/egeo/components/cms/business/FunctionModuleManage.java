package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleManage {

	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto);	

	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto,Pagination page);

	public List<FunctionModuleDTO> findFunctionModuleAll(FunctionModuleDTO dto);

	Long insertFunctionModuleWithTx(FunctionModuleDTO dto);

	int updateFunctionModuleWithTx(FunctionModuleDTO dto);

	int deleteFunctionModuleWithTx(FunctionModuleDTO dto);
}
	