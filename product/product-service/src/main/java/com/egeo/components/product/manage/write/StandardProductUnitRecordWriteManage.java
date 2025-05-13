package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitRecordPO;


public interface StandardProductUnitRecordWriteManage {

	Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po);

	int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po);

	int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordPO po);
}
	