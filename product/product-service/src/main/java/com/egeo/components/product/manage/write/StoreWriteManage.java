package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StorePO;


public interface StoreWriteManage {

	Long insertStoreWithTx(StorePO po);

	int updateStoreWithTx(StorePO po);

	int deleteStoreWithTx(StorePO po);
}
	