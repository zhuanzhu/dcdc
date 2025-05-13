package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.IconGroupPO;


public interface IconGroupWriteManage {

	Long insertIconGroupWithTx(IconGroupPO po);

	int updateIconGroupWithTx(IconGroupPO po);

	int deleteIconGroupWithTx(IconGroupPO po);
}
	