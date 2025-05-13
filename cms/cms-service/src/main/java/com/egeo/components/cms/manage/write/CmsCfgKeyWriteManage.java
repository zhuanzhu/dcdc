package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsCfgKeyPO;


public interface CmsCfgKeyWriteManage {

	Long insertCmsCfgKeyWithTx(CmsCfgKeyPO po);

	int updateCmsCfgKeyWithTx(CmsCfgKeyPO po);

	int deleteCmsCfgKeyWithTx(CmsCfgKeyPO po);
}
	