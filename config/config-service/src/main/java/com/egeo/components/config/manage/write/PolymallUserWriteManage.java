package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.PolymallUserPO;


public interface PolymallUserWriteManage {

	Long insertPolymallUserWithTx(PolymallUserPO po);

	int updatePolymallUserWithTx(PolymallUserPO po);

	int deletePolymallUserWithTx(PolymallUserPO po);
}
	