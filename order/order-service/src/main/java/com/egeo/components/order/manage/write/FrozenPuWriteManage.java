package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.FrozenPuPO;


public interface FrozenPuWriteManage {

	Long insertFrozenPuWithTx(FrozenPuPO po);

	int updateFrozenPuWithTx(FrozenPuPO po);

	int deleteFrozenPuWithTx(FrozenPuPO po);
}
	