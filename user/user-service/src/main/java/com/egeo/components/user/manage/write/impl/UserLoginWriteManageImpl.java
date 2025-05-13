package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UserLoginWriteManage;
import com.egeo.components.user.dao.write.UserLoginWriteDAO;
import com.egeo.components.user.po.UserLoginPO;

@Service
public class UserLoginWriteManageImpl implements UserLoginWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserLoginWriteDAO userLoginWriteDAO;
	@Override
	public void insertLoginLogWithTx(UserLoginPO po) {
		
		userLoginWriteDAO.insert(po);
		
	}
	@Override
	public int updatePasswordUser(UserLoginPO po) {
		
		return userLoginWriteDAO.update(po);
	}
}
	