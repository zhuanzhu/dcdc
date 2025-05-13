package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserRoleConverter;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.manage.write.UserRoleWriteManage;
import com.egeo.components.user.service.write.UserRoleWriteService;


@Service("userRoleWriteService")
public class UserRoleWriteServiceImpl implements UserRoleWriteService {
	@Autowired
	private UserRoleWriteManage userRoleWriteManage;
	
    @Override
    public String saveWithTx(UserRoleDTO userRoleDTO) {
        
        return userRoleWriteManage.saveWithTx(UserRoleConverter.toPO(userRoleDTO));
    }

    @Override
    public String deleteWithTx(UserRoleDTO userRoleDTO) {
        return userRoleWriteManage.deleteWithTx(UserRoleConverter.toPO(userRoleDTO));
    }
	
}
	