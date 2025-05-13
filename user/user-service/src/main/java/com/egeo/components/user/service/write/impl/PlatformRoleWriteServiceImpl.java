package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.PlatformRoleWriteManage;
import com.egeo.components.user.service.write.PlatformRoleWriteService;


@Service("platformRoleWriteService")
public class PlatformRoleWriteServiceImpl implements PlatformRoleWriteService {
	@Autowired
	private PlatformRoleWriteManage platformRoleWriteManage;
}
	