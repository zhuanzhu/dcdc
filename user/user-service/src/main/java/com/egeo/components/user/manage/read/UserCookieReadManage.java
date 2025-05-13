package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.UserCookiePO;

public interface UserCookieReadManage {

	UserCookiePO getbyUserId(Long userId);
	/**
	 * 根据用户id集合查询用户cookie信息
	 * @param userIds
	 * @return
	 */
	List<UserCookiePO> findByUserIds(List<Long> userIds,Long clientId,Long platformId);
	/**
	 * 根据用户id客户端id查询用户cookie信息
	 * @param userId 用户id
	 * @param clientId 客户端id
	 * @return
	 */
	UserCookiePO getbyUserIdClientId(Long userId, Long clientId,Long platformId);
}
	