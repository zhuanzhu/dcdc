package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitRecordStorePO;


public interface StandardUnitRecordStoreWriteManage {

	Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStorePO po);

	int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStorePO po);

	int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStorePO po);
}
	