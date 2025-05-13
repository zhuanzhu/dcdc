package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StoreMenuNodePO;


public interface StoreMenuNodeWriteManage {

	Long insertStoreMenuNodeWithTx(StoreMenuNodePO po);

	int updateStoreMenuNodeWithTx(StoreMenuNodePO po);

	int deleteStoreMenuNodeWithTx(StoreMenuNodePO po);
}
	