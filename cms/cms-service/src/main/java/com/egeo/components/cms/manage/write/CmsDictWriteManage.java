package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsDictPO;


public interface CmsDictWriteManage {

	Long insertCmsDictWithTx(CmsDictPO po);

	int updateCmsDictWithTx(CmsDictPO po);

	int deleteCmsDictWithTx(CmsDictPO po);
}
	