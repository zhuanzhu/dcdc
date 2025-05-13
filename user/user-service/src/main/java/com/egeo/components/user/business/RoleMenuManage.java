package com.egeo.components.user.business;

import com.egeo.components.user.dto.RoleMenuDTO;

import java.util.List;

public interface RoleMenuManage {

	List<RoleMenuDTO> getRoleMenuListByUserId(Long userId,Long platformId);

}
	