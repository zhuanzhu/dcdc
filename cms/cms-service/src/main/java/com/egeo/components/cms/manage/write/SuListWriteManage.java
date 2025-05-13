package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.SuListPO;


public interface SuListWriteManage {

	Long insertSuListWithTx(SuListPO po);

	int updateSuListWithTx(SuListPO po);

	int deleteSuListWithTx(SuListPO po);
}
	