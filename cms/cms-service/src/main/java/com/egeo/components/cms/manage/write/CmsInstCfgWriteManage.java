package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsInstCfgPO;


public interface CmsInstCfgWriteManage {

	Long insertCmsInstCfgWithTx(CmsInstCfgPO po);

	int updateCmsInstCfgWithTx(CmsInstCfgPO po);

	int deleteCmsInstCfgWithTx(CmsInstCfgPO po);
}
	