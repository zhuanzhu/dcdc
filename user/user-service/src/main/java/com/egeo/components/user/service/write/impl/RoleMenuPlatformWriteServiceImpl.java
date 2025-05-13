package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.RoleMenuPlatformWriteManage;
import com.egeo.components.user.service.write.RoleMenuPlatformWriteService;

@Service("roleMenuPlatformWriteService")
public class RoleMenuPlatformWriteServiceImpl implements RoleMenuPlatformWriteService {
	@Autowired
	private RoleMenuPlatformWriteManage roleMenuPlatformWriteManage;
}
	