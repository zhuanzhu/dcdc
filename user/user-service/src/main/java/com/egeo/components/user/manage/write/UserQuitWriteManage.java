package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.UserQuitPO;


public interface UserQuitWriteManage {

	Long insertUserQuitWithTx(UserQuitPO po);

	int updateUserQuitWithTx(UserQuitPO po);

	int deleteUserQuitWithTx(UserQuitPO po);
	/**
	 * 根据用户id集合删除用户离职信息表
	 * @param userIdInvalidSet
	 * @return
	 */
	int delByUserIdsWithTx(List<Long> userIdInvalidSet);
}
	