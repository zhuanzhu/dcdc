package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.VersionsChildPO;


public interface VersionsChildWriteManage {

	Long insertVersionsChildWithTx(VersionsChildPO po);

	int updateVersionsChildWithTx(VersionsChildPO po);

	int deleteVersionsChildWithTx(VersionsChildPO po);
}
	