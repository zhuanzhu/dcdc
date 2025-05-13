package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ElementDictPO;


public interface ElementDictWriteManage {

	Long insertElementDictWithTx(ElementDictPO po);

	int updateElementDictWithTx(ElementDictPO po);

	int deleteElementDictWithTx(ElementDictPO po);
}
	