package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FunctionTreeNodeUrlReadService {

	public FunctionTreeNodeUrlDTO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlDTO dto);

	public PageResult<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlDTO dto,Pagination page);

	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlDTO dto);

	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlByFunctionTreeNodeId(Long functionTreeNodeId);
}
	