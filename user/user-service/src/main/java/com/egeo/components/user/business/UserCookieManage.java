package com.egeo.components.user.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.components.user.dto.UserCookieDTO;

public interface UserCookieManage {

	void saveUserCookieWithTx(UserCookieVO userCookie, HttpServletRequest req);

	UserCookieDTO getbyUserIdClientId(Long userId,Long clientId,Long platformId);

	void deleteCookieUserbyUserId(Long id);
	/**
	 * 根据用户id集合和客户端id查询登录过的ut信息
	 * @param userIds
	 * @param clientId
	 * @return
	 */
	List<UserCookieDTO> findByUserIds(List<Long> userIds,Long clientId,Long platformId);
	

}
	