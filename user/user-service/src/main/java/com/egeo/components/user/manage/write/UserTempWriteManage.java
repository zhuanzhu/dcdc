package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.UserTempPO;


public interface UserTempWriteManage {

	Long insertUserTempWithTx(UserTempPO po);

	int updateUserTempWithTx(UserTempPO po);

	int deleteUserTempWithTx(UserTempPO po);
	/**
	 * 根据创建用户id清楚缓存数据
	 * @param createUserid
	 * @return
	 */
	int delByCreateUserId(Long createUserid);

	int insertUserTempAllWithTx(List<UserTempPO> list);

    int deleteUserTempByParamsWithTx(UserTempPO po);
}
	