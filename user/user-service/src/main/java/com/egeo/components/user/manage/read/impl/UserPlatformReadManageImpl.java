package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserPlatformReadManage;
import com.egeo.components.user.dao.read.UserPlatformReadDAO;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.utils.StringUtils;

@Service
public class UserPlatformReadManageImpl implements UserPlatformReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserPlatformReadDAO userPlatformReadDAO;

    @Override
    public boolean userIsExitPlatform(UserPlatformPO po) {
        
        return StringUtils.isNotEmpty(userPlatformReadDAO.userIsExitPlatform(po));
    }

    @Override
    public UserPlatformPO findById(UserPlatformPO po) {
        return userPlatformReadDAO.findById(po);
    }

    @Override
    public List<UserPlatformPO> findAll(UserPlatformPO po) {
        
        return userPlatformReadDAO.findAll(po,null);
    }

	@Override
	public Integer findUserCountByPlatformId(Long platformId) {
		UserPlatformPO userPlatformPO = new UserPlatformPO();
		userPlatformPO.setPlatformId(platformId);
		userPlatformPO.setIsAvailable(1);
		return userPlatformReadDAO.countOfPage(userPlatformPO);
	}

}
