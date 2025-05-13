package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface RoleFunctionTreeNodeManage {

	public RoleFunctionTreeNodeDTO findRoleFunctionTreeNodeById(RoleFunctionTreeNodeDTO dto);	

	public PageResult<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodeDTO dto,Pagination page);

	public List<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodeDTO dto);

	Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);

	int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);

	int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);
}
	