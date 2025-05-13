package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.service.read.RoleMenuPlatformReadService;
import com.egeo.components.user.service.read.RoleMenuReadService;


@Component
public class RoleMenuPlatformFacade {
	
	@Resource
	private RoleMenuPlatformReadService roleMenuPlatformReadService;

	@Resource
	private RoleMenuReadService roleMenuReadService;

	public List<String> getMenuList(Long userId, Long platformId) {
		
		return roleMenuPlatformReadService.getMenuList(userId,platformId);
	}

	/*public List<RoleMenuDTO> getMenuListByRoleId(Long roleId) {
		RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
		roleMenuDTO.setRoleId(roleId);
		return roleMenuReadService.findAll(roleMenuDTO);
	}*/

	public List<String> getMenuByUserPlatform(Long userId, Long platformId) {

		return roleMenuPlatformReadService.getMenuByUserPlatform(userId,platformId);
	}
}
	