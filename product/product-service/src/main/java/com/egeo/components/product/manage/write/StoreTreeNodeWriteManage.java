package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StorePO;
import com.egeo.components.product.po.StoreTreeNodePO;


public interface StoreTreeNodeWriteManage {

	Long insertStoreTreeNodeWithTx(StoreTreeNodePO storeTreeNodePO,StorePO storePO);

	int updateStoreTreeNodeWithTx(StoreTreeNodePO po);

	int deleteStoreTreeNodeWithTx(StoreTreeNodePO po);
}
	