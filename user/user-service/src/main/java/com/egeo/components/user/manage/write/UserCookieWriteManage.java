package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.UserCookiePO;

public interface UserCookieWriteManage {

    void saveUserCookieWithTx(UserCookiePO po);

    int update(UserCookiePO po);

	void deleteCookieUserbyUserIdWithTx(Long userId);
	/**
	 * 根据用户id集合删除用户cookie信息
	 * @param userIds
	 * @return
	 */
	int delByUsetIdsWithTx(List<Long> userIds);
}
