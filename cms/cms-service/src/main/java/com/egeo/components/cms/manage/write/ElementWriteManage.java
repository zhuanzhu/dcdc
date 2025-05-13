package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ElementPO;


public interface ElementWriteManage {

	Long insertElementWithTx(ElementPO po);

	int updateElementWithTx(ElementPO po);

	int deleteElementWithTx(ElementPO po);
}
	