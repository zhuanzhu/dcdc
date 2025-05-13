package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsElementPO;


public interface CmsElementWriteManage {

	Long insertCmsElementWithTx(CmsElementPO po);

	int updateCmsElementWithTx(CmsElementPO po);

	int deleteCmsElementWithTx(CmsElementPO po);
}
	