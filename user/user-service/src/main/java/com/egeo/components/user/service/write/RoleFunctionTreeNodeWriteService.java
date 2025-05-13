package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;


public interface RoleFunctionTreeNodeWriteService {

	public Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);

	public int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);

	public int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto);
}
	