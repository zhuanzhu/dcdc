package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;


public interface StandardProductUnitAttValueRecordWriteManage {

	Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po);

	int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po);

	int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordPO po);
}
	