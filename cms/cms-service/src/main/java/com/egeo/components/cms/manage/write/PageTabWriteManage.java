package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.PageTabPO;


public interface PageTabWriteManage {

	Long insertPageTabWithTx(PageTabPO po);

	int updatePageTabWithTx(PageTabPO po);

	int deletePageTabWithTx(PageTabPO po);
}
	