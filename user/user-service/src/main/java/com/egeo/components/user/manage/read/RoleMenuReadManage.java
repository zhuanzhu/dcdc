package com.egeo.components.user.manage.read;

import java.util.List;
import com.egeo.components.user.po.RoleMenuPO;

public interface RoleMenuReadManage {

	public List<RoleMenuPO> findAll(RoleMenuPO roleMenuPO);

	List<RoleMenuPO> findRoleMenuListByUserId(Long userId,Long platformId);
}
	