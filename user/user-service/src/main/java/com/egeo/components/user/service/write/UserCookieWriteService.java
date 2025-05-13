package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UserCookieDTO;


public interface UserCookieWriteService {

    void saveUserCookieWithTx(UserCookieDTO userCookieDTO);

    int updateWithTx(UserCookieDTO userCookieDTO);

	void deleteCookieUserbyUserIdWithTx(Long userId);
	/**
	 * 根据用户id集合删除用户cookie信息
	 * @param userIds
	 * @return
	 */
	int delByUsetIds(List<Long> userIds);

}
