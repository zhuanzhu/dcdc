package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FunctionTreeNodeReadService {

	public FunctionTreeNodeDTO findFunctionTreeNodeById(FunctionTreeNodeDTO dto);

	public PageResult<FunctionTreeNodeDTO> findFunctionTreeNodeOfPage(FunctionTreeNodeDTO dto,Pagination page);

	public List<FunctionTreeNodeDTO> findFunctionTreeNodeAll(FunctionTreeNodeDTO dto);

	public List<UrlDTO> findUrlList(Long id);

	public Boolean hasLeaf(Long id);
}
	