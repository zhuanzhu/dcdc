package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.UserRolePO;


public interface UserRoleReadManage {

	List<UserRolePO> findUserRoleAll(UserRolePO po);
}
	