package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsInstCompanyPO;


public interface CmsInstCompanyWriteManage {

	Long insertCmsInstCompanyWithTx(CmsInstCompanyPO po);

	int updateCmsInstCompanyWithTx(CmsInstCompanyPO po);

	int deleteCmsInstCompanyWithTx(CmsInstCompanyPO po);
}
	