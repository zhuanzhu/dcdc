package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StoreProductUnitPO;


public interface StoreProductUnitWriteManage {

	Long insertStoreProductUnitWithTx(StoreProductUnitPO po);

	int updateStoreProductUnitWithTx(StoreProductUnitPO po);

	int deleteStoreProductUnitWithTx(StoreProductUnitPO po);

	int insertAllWithTx(List<StoreProductUnitPO> list);
}
	