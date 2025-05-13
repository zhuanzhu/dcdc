package com.egeo.components.user.service.read;

import java.util.List;

public interface RoleMenuPlatformReadService {

	List<String> getMenuList(Long userId, Long platformId);

	List<String> getMenuByUserPlatform(Long userId, Long platformId);
}
	