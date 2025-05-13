package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.RoleMenuDTO;

public interface RoleMenuWriteService {
	public String saveWithTx(RoleMenuDTO dto);
	
	public String refreshWithTx(List<RoleMenuDTO> dto,Long roleId);
}
	