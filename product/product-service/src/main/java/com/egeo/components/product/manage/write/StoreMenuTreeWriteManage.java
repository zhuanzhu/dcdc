package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StoreMenuTreePO;


public interface StoreMenuTreeWriteManage {

	Long insertStoreMenuTreeWithTx(StoreMenuTreePO po);

	int updateStoreMenuTreeWithTx(StoreMenuTreePO po);

	int deleteStoreMenuTreeWithTx(StoreMenuTreePO po);
}
	