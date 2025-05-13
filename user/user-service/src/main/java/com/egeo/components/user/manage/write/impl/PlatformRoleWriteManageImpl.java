package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.PlatformRoleWriteManage;
import com.egeo.components.user.dao.write.PlatformRoleWriteDAO;

@Service
public class PlatformRoleWriteManageImpl implements PlatformRoleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PlatformRoleWriteDAO platformRoleWriteDAO;
}
	