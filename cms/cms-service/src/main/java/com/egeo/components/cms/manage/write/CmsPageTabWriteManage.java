package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsPageTabPO;


public interface CmsPageTabWriteManage {

	Long insertCmsPageTabWithTx(CmsPageTabPO po);

	int updateCmsPageTabWithTx(CmsPageTabPO po);

	int deleteCmsPageTabWithTx(CmsPageTabPO po);
}
	