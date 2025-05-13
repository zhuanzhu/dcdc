package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.user.dto.RoleMenuDTO;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.RoleMenuPlatformManage;
import com.egeo.components.user.facade.RoleMenuPlatformFacade;

@Service("roleMenuPlatform")
public class RoleMenuPlatformManageImpl implements RoleMenuPlatformManage{

	
	@Resource(name="roleMenuPlatformFacade")
	private RoleMenuPlatformFacade roleMenuPlatformFacade;

	@Override
	public List<String> getMenuList(Long userId, Long platformId) {

		return roleMenuPlatformFacade.getMenuList(userId,platformId);
	}

	/*@Override
	public List<RoleMenuDTO> getMenuListByRoleId(Long roleId) {

		return roleMenuPlatformFacade.getMenuListByRoleId(roleId);
	}*/

	@Override
	public List<String> getMenuByUserPlatform(Long userId, Long platformId) {

		return roleMenuPlatformFacade.getMenuByUserPlatform(userId,platformId);
	}
}
	