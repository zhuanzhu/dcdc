package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FunctionModuleCategoryReadService {

	public FunctionModuleCategoryDTO findFunctionModuleCategoryById(FunctionModuleCategoryDTO dto);

	public PageResult<FunctionModuleCategoryDTO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryDTO dto,Pagination page);

	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto);
}
	