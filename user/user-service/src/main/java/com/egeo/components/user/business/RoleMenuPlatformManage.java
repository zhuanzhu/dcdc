package com.egeo.components.user.business;

import com.egeo.components.user.dto.RoleMenuDTO;

import java.util.List;

public interface RoleMenuPlatformManage {

	List<String> getMenuList(Long userId, Long platformId);

	List<String> getMenuByUserPlatform(Long userId, Long platformId);

//	List<RoleMenuDTO> getMenuListByRoleId(Long roleId);
}
	