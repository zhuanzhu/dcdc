package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserQuitTempPO;


public interface UserQuitTempWriteManage {

	Long insertUserQuitTempWithTx(UserQuitTempPO po);

	int updateUserQuitTempWithTx(UserQuitTempPO po);

	int deleteUserQuitTempWithTx(UserQuitTempPO po);

    int deleteUserQuitTempByParamsWithTx(UserQuitTempPO po);
}
	