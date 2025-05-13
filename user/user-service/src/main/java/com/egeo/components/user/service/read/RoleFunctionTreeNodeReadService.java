package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface RoleFunctionTreeNodeReadService {

	public RoleFunctionTreeNodeDTO findRoleFunctionTreeNodeById(RoleFunctionTreeNodeDTO dto);

	public PageResult<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodeDTO dto,Pagination page);

	public List<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodeDTO dto);
}
	