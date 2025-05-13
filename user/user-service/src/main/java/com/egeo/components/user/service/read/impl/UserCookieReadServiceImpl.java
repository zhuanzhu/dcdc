package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserCookieConverter;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.manage.read.UserCookieReadManage;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.service.read.UserCookieReadService;
import com.egeo.utils.StringUtils;

@Service("userCookieReadService")
public class UserCookieReadServiceImpl implements UserCookieReadService {
	@Autowired
	private UserCookieReadManage userCookieReadManage;

	@Override
	public UserCookieDTO getbyUserId(Long userId) {
		UserCookiePO userCookiePO = userCookieReadManage.getbyUserId(userId);
		if(StringUtils.isNotEmpty(userCookiePO)){
			return UserCookieConverter.toDTO(userCookiePO);
		}else{
			return null;
		}
	}
	/**
	 * 根据用户id集合查询用户cookie信息
	 * @param userIds
	 * @return
	 */
	@Override
	public List<UserCookieDTO> findByUserIds(List<Long> userIds,Long clientId,Long platformId) {
		List<UserCookiePO> userCookiePOs = userCookieReadManage.findByUserIds(userIds,clientId,platformId);
		return UserCookieConverter.toDTO(userCookiePOs);
	}
	
	@Override
	public UserCookieDTO getbyUserIdClientId(Long userId, Long clientId,Long platformId) {
		UserCookiePO userCookiePO = userCookieReadManage.getbyUserIdClientId(userId, clientId,platformId);
		return UserCookieConverter.toDTO(userCookiePO);
	}

	

}
	