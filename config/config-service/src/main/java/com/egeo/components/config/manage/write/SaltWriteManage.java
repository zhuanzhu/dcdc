package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.SaltPO;


public interface SaltWriteManage {

	Long insertSaltWithTx(SaltPO po);

	int updateSaltWithTx(SaltPO po);

	int deleteSaltWithTx(SaltPO po);
}
	