package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionTreeNodeManage {

	public FunctionTreeNodeDTO findFunctionTreeNodeById(FunctionTreeNodeDTO dto);	

	public PageResult<FunctionTreeNodeDTO> findFunctionTreeNodeOfPage(FunctionTreeNodeDTO dto,Pagination page);

	public List<FunctionTreeNodeDTO> findFunctionTreeNodeAll(FunctionTreeNodeDTO dto);

	Long insertFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);

	int updateFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);

	int deleteFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);

	public List<Map<String, Object>> findFunctionTreeNodeUrlAll(FunctionTreeNodeDTO dto);
}
	