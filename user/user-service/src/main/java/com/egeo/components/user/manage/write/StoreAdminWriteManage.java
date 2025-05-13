package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.StoreAdminPO;


public interface StoreAdminWriteManage {

	Long insertStoreAdminWithTx(StoreAdminPO po);

	int updateStoreAdminWithTx(StoreAdminPO po);

	int deleteStoreAdminWithTx(StoreAdminPO po);
}
	