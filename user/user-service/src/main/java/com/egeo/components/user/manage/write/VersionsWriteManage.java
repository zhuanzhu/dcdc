package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.VersionsPO;


public interface VersionsWriteManage {

	Long insertVersionsWithTx(VersionsPO po);

	int updateVersionsWithTx(VersionsPO po);

	int deleteVersionsWithTx(VersionsPO po);

	int updateVersionsOfficialByTypeWithTx(Integer user);
}
	