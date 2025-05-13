package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.IconPO;


public interface IconWriteManage {

	Long insertIconWithTx(IconPO po);

	int updateIconWithTx(IconPO po);

	int deleteIconWithTx(IconPO po);
}
	