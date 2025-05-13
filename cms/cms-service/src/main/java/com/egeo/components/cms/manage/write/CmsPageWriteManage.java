package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsPagePO;


public interface CmsPageWriteManage {

	Long insertCmsPageWithTx(CmsPagePO po, String configJson, Long companyId);

	int updateCmsPageWithTx(CmsPagePO po, String configJson, Long companyId);

	int deleteCmsPageWithTx(CmsPagePO po);

	int updateStatusWithTx(CmsPagePO po);
}
	