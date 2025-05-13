package com.egeo.components.user.business.impl;
	

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserCookieManage;
import com.egeo.components.user.business.util.CommonUtils;
import com.egeo.components.user.converter.UserCookieConverter;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.facade.UserCookieFacade;
import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;

@Service("userCookie")
public class UserCookieManageImpl implements UserCookieManage{

	
	@Resource(name="userCookieFacade")
	private UserCookieFacade userCookieFacade;

	@Override
	public void saveUserCookieWithTx(UserCookieVO userCookie,HttpServletRequest req) {
		

		CacheUser cacheUser = RuntimeContext.cacheUser();
        //设置有效时间
        // 生成失效时间
        Calendar timeout = Calendar.getInstance();
        timeout.setTime(new Date());
        timeout.add(Calendar.DAY_OF_YEAR, 30);
        userCookie.setExpirationTime(timeout.getTime());
        
        UserCookieDTO userCookieDTO = userCookieFacade.getbyUserIdClientId(userCookie.getUserId(),userCookie.getClientId(),userCookie.getPlatformId());
        if(userCookieDTO == null){
			userCookie.setCreateTime(new Date());		
			String ip = CommonUtils.getUserIp(req);
        	userCookieFacade.saveUserCookieWithTx(UserCookieConverter.toDTO(userCookie));
        }else{
        	userCookie.setUpdateTime(new Date());
        	String ip = CommonUtils.getUserIp(req);
        	userCookieFacade.saveUserCookieWithTx(UserCookieConverter.toDTO(userCookie));
        }

        
    
		
	}

	@Override
	public UserCookieDTO getbyUserIdClientId(Long userId,Long clientId,Long platformId) {
		
		return userCookieFacade.getbyUserIdClientId(userId,clientId,platformId);
	}

	@Override
	public void deleteCookieUserbyUserId(Long userId) {
		userCookieFacade.deleteCookieUserbyUserId(userId);
		
	}

	@Override
	public List<UserCookieDTO> findByUserIds(List<Long> userIds, Long clientId,Long platformId) {
		// TODO Auto-generated method stub
		return userCookieFacade.findByUserIds(userIds, clientId,platformId);
	}
	


}
	