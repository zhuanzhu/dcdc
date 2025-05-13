package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsLocalParamPO;


public interface CmsLocalParamWriteManage {

	Long insertCmsLocalParamWithTx(CmsLocalParamPO po);

	int updateCmsLocalParamWithTx(CmsLocalParamPO po);

	int deleteCmsLocalParamWithTx(CmsLocalParamPO po);
}
	