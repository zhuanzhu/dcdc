package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserInfoPO;


public interface UserInfoWriteManage {

	Long insertUserInfoWithTx(UserInfoPO po);

	int updateUserInfoWithTx(UserInfoPO po);

	int deleteUserInfoWithTx(UserInfoPO po);
	/**
	 * 根据用户消息id更新用户消息是否已读状态
	 * @param userInfoId
	 * @return
	 */
	int updateIsReadByIdWithTx(Long userInfoId);
	/**
	 * 根据用户id和类型修改其消息已读
	 * @param userId
	 * @param type
	 * @param platformId
	 * @return
	 */
	int updateByUserIdType(Long userId, Long type, Long platformId);
}
	