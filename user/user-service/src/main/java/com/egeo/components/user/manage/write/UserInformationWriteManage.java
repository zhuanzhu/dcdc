package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserInformationPO;


public interface UserInformationWriteManage {

	Long insertUserInformationWithTx(UserInformationPO po);

	int updateUserInformationWithTx(UserInformationPO po);

	int deleteUserInformationWithTx(UserInformationPO po);
}
	