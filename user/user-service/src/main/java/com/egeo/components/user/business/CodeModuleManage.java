package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CodeModuleManage {

	public CodeModuleDTO findCodeModuleById(CodeModuleDTO dto);	

	public PageResult<CodeModuleDTO> findCodeModuleOfPage(CodeModuleDTO dto,Pagination page);

	public List<Map<String, Object>> findCodeModuleAll(CodeModuleDTO dto);

	Long insertCodeModuleWithTx(CodeModuleDTO dto);

	int updateCodeModuleWithTx(CodeModuleDTO dto);

	int deleteCodeModuleWithTx(CodeModuleDTO dto);
}
	