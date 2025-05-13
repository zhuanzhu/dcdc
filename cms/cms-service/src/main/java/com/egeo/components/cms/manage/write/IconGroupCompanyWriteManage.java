package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.IconGroupCompanyPO;


public interface IconGroupCompanyWriteManage {

	Long insertIconGroupCompanyWithTx(IconGroupCompanyPO po);

	int updateIconGroupCompanyWithTx(IconGroupCompanyPO po);

	int deleteIconGroupCompanyWithTx(IconGroupCompanyPO po);
}
	