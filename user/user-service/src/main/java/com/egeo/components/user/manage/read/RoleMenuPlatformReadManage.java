package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.UserPlatformPO;

public interface RoleMenuPlatformReadManage {

	List<String> getMenuList(UserPlatformPO po);

	List<String> getMenuByUserPlatform(UserPlatformPO po);
}
	