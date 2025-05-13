package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsPageTabCompanyPO;


public interface CmsPageTabCompanyWriteManage {

	Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po);

	int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po);

	int deleteCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po);
}
	