package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleManage {

	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto);	

	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto,Pagination page);

	public List<Map<String, Object>> findFunctionModuleAll(FunctionModuleDTO dto);

	Long insertFunctionModuleWithTx(FunctionModuleDTO dto);

	int updateFunctionModuleWithTx(FunctionModuleDTO dto);

	int deleteFunctionModuleWithTx(FunctionModuleDTO dto);
}
	