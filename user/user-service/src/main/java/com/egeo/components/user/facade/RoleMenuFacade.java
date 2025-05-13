package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.service.read.RoleMenuReadService;


@Component
public class RoleMenuFacade {
	
	@Resource
	private RoleMenuReadService roleMenuReadService;


	public List<RoleMenuDTO> getRoleMenuListByUserId(Long userId,Long platformId) {
		
		return roleMenuReadService.getRoleMenuListByUserId(userId,platformId);
	}

}
	