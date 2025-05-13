package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserInformationReadPO;


public interface UserInformationReadWriteManage {

	Long insertUserInformationReadWithTx(UserInformationReadPO po);

	int updateUserInformationReadWithTx(UserInformationReadPO po);

	int deleteUserInformationReadWithTx(UserInformationReadPO po);
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	int deleteByUserInformationIdWithTx(Long userInformationId);
}
	