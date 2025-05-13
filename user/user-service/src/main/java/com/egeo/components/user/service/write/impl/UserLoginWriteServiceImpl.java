package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserLoginConverter;
import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.components.user.manage.write.UserLoginWriteManage;
import com.egeo.components.user.service.write.UserLoginWriteService;


@Service("userLoginWriteService")
public class UserLoginWriteServiceImpl implements UserLoginWriteService {
	@Autowired
	private UserLoginWriteManage userLoginWriteManage;

	@Override
	public void insertLoginLogWithTx(UserLoginDTO userLogin) {
		
		userLoginWriteManage.insertLoginLogWithTx(UserLoginConverter.toPO(userLogin));
	}

	
}
	