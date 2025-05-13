package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FunctionModuleReadService {

	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto);

	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto,Pagination page);

	public List<FunctionModuleDTO> findFunctionModuleAll(FunctionModuleDTO dto);
}
	