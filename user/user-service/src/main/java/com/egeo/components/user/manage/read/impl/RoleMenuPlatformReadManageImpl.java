package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.RoleMenuPlatformReadManage;
import com.egeo.components.user.dao.read.RoleMenuPlatformReadDAO;
import com.egeo.components.user.po.UserPlatformPO;

@Service
public class RoleMenuPlatformReadManageImpl implements RoleMenuPlatformReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMenuPlatformReadDAO roleMenuPlatformReadDAO;
	@Override
	public List<String> getMenuList(UserPlatformPO po) {

		return roleMenuPlatformReadDAO.getMenuList(po);
	}

	@Override
	public List<String> getMenuByUserPlatform(UserPlatformPO po) {

		return roleMenuPlatformReadDAO.getMenuByUserPlatform(po);
	}
	
}
	