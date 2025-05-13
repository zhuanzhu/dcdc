package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.service.read.UserCookieReadService;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.write.UserCookieWriteService;


@Component
public class UserCookieFacade {
	
	@Resource
	private UserCookieWriteService userCookieWriteService;
	
	@Resource
	private UserCookieReadService userCookieReadService;
	
	@Resource
	private UserExtendReadService userExtendReadService;

	public void saveUserCookieWithTx(UserCookieDTO userCookieDTO) {
		
		userCookieWriteService.saveUserCookieWithTx(userCookieDTO);
		
	}

	public UserCookieDTO getbyUserId(Long userId) {
		
		return userCookieReadService.getbyUserId(userId);
	}

	public void deleteCookieUserbyUserId(Long userId) {
		userCookieWriteService.deleteCookieUserbyUserIdWithTx(userId);
		
	}
	/**
	 * 根据用户id客户端id查询用户cookie信息
	 * @param userId 用户id
	 * @param clientId 客户端id
	 * @return
	 */
	public UserCookieDTO getbyUserIdClientId(Long userId, Long clientId,Long platformId) {
		// TODO Auto-generated method stub
		return userCookieReadService.getbyUserIdClientId(userId, clientId,platformId);
	}
	/**
	 * 微信端登录,根据手机号删除所有手机号关联的账户
	 * @param mobile
	 * @return
	 */
	public List<UserCookieDTO> findByUserIds(List<Long> userIds,Long clientId,Long platformId) {
		return userCookieReadService.findByUserIds(userIds,clientId,platformId);
	}

	/**
	 * 根据用户id查询ut
	 */

}
	