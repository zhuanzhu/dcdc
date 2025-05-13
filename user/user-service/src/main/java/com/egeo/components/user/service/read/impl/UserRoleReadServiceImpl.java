package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserRoleConverter;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.manage.read.UserRoleReadManage;
import com.egeo.components.user.service.read.UserRoleReadService;

@Service("userRoleReadService")
public class UserRoleReadServiceImpl implements UserRoleReadService {
	@Autowired
	private UserRoleReadManage userRoleReadManage;

	@Override
	public List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto) {
		
		return UserRoleConverter.toDTO(userRoleReadManage.findUserRoleAll(UserRoleConverter.toPO(dto)));
	}
}
	