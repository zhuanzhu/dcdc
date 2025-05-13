package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.UserRoleDTO;

public interface UserRoleReadService {

	List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto);
}
	