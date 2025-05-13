package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsPageCfgPO;


public interface CmsPageCfgWriteManage {

	Long insertCmsPageCfgWithTx(CmsPageCfgPO po);

	int updateCmsPageCfgWithTx(CmsPageCfgPO po);

	int deleteCmsPageCfgWithTx(CmsPageCfgPO po);
}
	