package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionTreeNodeUrlManage {

	public FunctionTreeNodeUrlDTO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlDTO dto);	

	public PageResult<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlDTO dto,Pagination page);

	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlDTO dto);

	Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);

	int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);

	int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);
}
	