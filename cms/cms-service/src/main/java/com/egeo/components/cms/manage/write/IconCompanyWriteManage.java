package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.IconCompanyPO;


public interface IconCompanyWriteManage {

	Long insertIconCompanyWithTx(IconCompanyPO po);

	int updateIconCompanyWithTx(IconCompanyPO po);

	int deleteIconCompanyWithTx(IconCompanyPO po);
}
	