package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserRoleReadManage;
import com.egeo.components.user.dao.read.UserRoleReadDAO;
import com.egeo.components.user.po.UserRolePO;

@Service
public class UserRoleReadManageImpl implements UserRoleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRoleReadDAO userRoleReadDAO;
	
	@Override
	public List<UserRolePO> findUserRoleAll(UserRolePO po) {
		
		return userRoleReadDAO.findAll(po,null);
	}
	
}
	