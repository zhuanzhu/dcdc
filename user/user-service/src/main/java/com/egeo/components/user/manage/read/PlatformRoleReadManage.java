package com.egeo.components.user.manage.read;

import java.util.List;
import com.egeo.components.user.po.PlatformRolePO;

public interface PlatformRoleReadManage {

	public List<PlatformRolePO> findAll(PlatformRolePO platformRolePO);
	
}
	