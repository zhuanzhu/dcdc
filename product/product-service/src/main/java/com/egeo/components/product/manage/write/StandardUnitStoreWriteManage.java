package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitStorePO;

import java.util.List;


public interface StandardUnitStoreWriteManage {

	Long insertStandardUnitStoreWithTx(StandardUnitStorePO po);

	int updateStandardUnitStoreWithTx(StandardUnitStorePO po);

	int deleteStandardUnitStoreWithTx(StandardUnitStorePO po);

    void saveStandardUnitStore(List<StandardUnitStorePO> standardUnitStorePOList);
}
	