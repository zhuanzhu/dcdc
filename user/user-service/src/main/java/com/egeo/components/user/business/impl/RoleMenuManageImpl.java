package com.egeo.components.user.business.impl;


import com.egeo.components.user.business.RoleMenuManage;
import com.egeo.components.user.business.RoleMenuPlatformManage;
import com.egeo.components.user.facade.RoleMenuFacade;
import com.egeo.components.user.facade.RoleMenuPlatformFacade;
import com.egeo.components.user.dto.RoleMenuDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleMenu")
public class RoleMenuManageImpl implements RoleMenuManage {

	
	@Resource(name="roleMenuFacade")
	private RoleMenuFacade roleMenuFacade;

	@Override
	public List<RoleMenuDTO> getRoleMenuListByUserId(Long userId,Long platformId) {
		return roleMenuFacade.getRoleMenuListByUserId(userId,platformId);
	}
}
	