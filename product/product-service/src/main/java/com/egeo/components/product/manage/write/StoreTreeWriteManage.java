package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StoreTreePO;


public interface StoreTreeWriteManage {

	Long insertStoreTreeWithTx(StoreTreePO po);

	int updateStoreTreeWithTx(StoreTreePO po);

	int deleteStoreTreeWithTx(StoreTreePO po);
}
	