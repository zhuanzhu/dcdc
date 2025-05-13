package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.RoleMenuReadManage;
import com.egeo.components.user.dao.read.RoleMenuReadDAO;
import com.egeo.components.user.po.RoleMenuPO;

@Service
public class RoleMenuReadManageImpl implements RoleMenuReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleMenuReadDAO roleMenuReadDAO;
	@Override
	public List<RoleMenuPO> findAll(RoleMenuPO roleMenuPO) {

		return roleMenuReadDAO.findAll(roleMenuPO,null);
	}

	@Override
	public List<RoleMenuPO> findRoleMenuListByUserId(Long userId,Long platformId) {

		return roleMenuReadDAO.findRoleMenuListByUserId(userId,platformId);
	}
}
	