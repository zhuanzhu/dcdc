package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CodeModuleReadService {

	public CodeModuleDTO findCodeModuleById(CodeModuleDTO dto);

	public PageResult<CodeModuleDTO> findCodeModuleOfPage(CodeModuleDTO dto,Pagination page);

	public List<CodeModuleDTO> findCodeModuleAll(CodeModuleDTO dto);
}
	