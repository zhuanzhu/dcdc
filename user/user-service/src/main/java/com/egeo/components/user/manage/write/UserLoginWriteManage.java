package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.UserLoginPO;

public interface UserLoginWriteManage {

	void insertLoginLogWithTx(UserLoginPO po);

	int updatePasswordUser(UserLoginPO po);

}
	