package com.egeo.components.user.service.read;
import java.util.List;

import com.egeo.components.user.dto.RoleMenuDTO;

public interface RoleMenuReadService {

	public List<RoleMenuDTO> findAll(RoleMenuDTO roleMenuDTO);

	List<RoleMenuDTO> getRoleMenuListByUserId(Long userId,Long platformId);

}
	