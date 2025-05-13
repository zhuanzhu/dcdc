package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserCookieReadManage;
import com.egeo.components.user.dao.read.UserCookieReadDAO;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.utils.StringUtils;

@Service
public class UserCookieReadManageImpl implements UserCookieReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserCookieReadDAO userCookieReadDAO;
	@Override
	public UserCookiePO getbyUserId(Long userId) {
		
		UserCookiePO po = new UserCookiePO();
		po.setUserId(userId);
		List<UserCookiePO> poList = userCookieReadDAO.findAll(po,null);
		if(StringUtils.isEmpty(poList)){
			return null;
		}else{
			return poList.get(0);
		}
	}
	/**
	 * 根据用户id集合查询用户cookie信息
	 * @param userIds
	 * @return
	 */
	@Override
	public List<UserCookiePO> findByUserIds(List<Long> userIds,Long clientId,Long platformId) {
		if(StringUtils.isNotEmpty(userIds))
			return userCookieReadDAO.findByUserIds(userIds,clientId,platformId);
		return null;
	}
	@Override
	public UserCookiePO getbyUserIdClientId(Long userId, Long clientId,Long platformId) {
		UserCookiePO po = new UserCookiePO();
		po.setUserId(userId);
		po.setClientId(clientId);
		po.setPlatformId(platformId);
		List<UserCookiePO> poList = userCookieReadDAO.findAll(po,null);
		if(StringUtils.isEmpty(poList)){
			return null;
		}else{
			return poList.get(0);
		}
	}
	
}
	