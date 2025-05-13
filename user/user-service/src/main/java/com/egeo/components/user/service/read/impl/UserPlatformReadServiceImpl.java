package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserPlatformConverter;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.manage.read.UserPlatformReadManage;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.components.user.service.read.UserPlatformReadService;


@Service("userPlatformReadService")
public class UserPlatformReadServiceImpl implements UserPlatformReadService {
    @Autowired
    private UserPlatformReadManage userPlatformReadManage;

    @Override
    public boolean userIsExitPlatform(UserPlatformDTO userPlatformDTO) {
        
        return userPlatformReadManage.userIsExitPlatform(UserPlatformConverter.toPO(userPlatformDTO));
    }

    @Override
    public UserPlatformDTO findById(UserPlatformDTO userPlatformDTO) {
        UserPlatformPO list = userPlatformReadManage.findById(UserPlatformConverter.toPO(userPlatformDTO));
        return UserPlatformConverter.toDTO(list);
    }

    @Override
    public List<UserPlatformDTO> findAll(UserPlatformDTO userPlatformDTO) {
        List<UserPlatformPO> list = userPlatformReadManage.findAll(UserPlatformConverter.toPO(userPlatformDTO));
        return UserPlatformConverter.toDTO(list);
    }

	@Override
	public Integer findUserCountByPlatformId(Long platformId) {
		return userPlatformReadManage.findUserCountByPlatformId(platformId);
	}

}
