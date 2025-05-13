package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.UserCookieDTO;

public interface UserCookieReadService {

	UserCookieDTO getbyUserId(Long userId);
	/**
	 * 根据用户id集合查询用户cookie信息
	 * @param userIds
	 * @return
	 */
	List<UserCookieDTO> findByUserIds(List<Long> userIds,Long clientId,Long platformId);
	/**
	 * 根据用户id客户端id查询用户cookie信息
	 * @param userId 用户id
	 * @param clientId 客户端id
	 * @return
	 */
	UserCookieDTO getbyUserIdClientId(Long userId, Long clientId,Long platformId);


}
	