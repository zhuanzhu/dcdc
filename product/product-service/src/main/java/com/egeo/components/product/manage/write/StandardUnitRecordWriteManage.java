package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitRecordPO;


public interface StandardUnitRecordWriteManage {

	Long insertStandardUnitRecordWithTx(StandardUnitRecordPO po);

	int updateStandardUnitRecordWithTx(StandardUnitRecordPO po);

	int deleteStandardUnitRecordWithTx(StandardUnitRecordPO po);
}
	