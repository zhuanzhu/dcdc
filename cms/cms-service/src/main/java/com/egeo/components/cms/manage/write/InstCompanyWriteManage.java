package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.InstCompanyPO;


public interface InstCompanyWriteManage {

	Long insertInstCompanyWithTx(InstCompanyPO po);

	int updateInstCompanyWithTx(InstCompanyPO po);

	int deleteInstCompanyWithTx(InstCompanyPO po);
}
	