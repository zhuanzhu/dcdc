package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;


public interface StandardProductUnitAttNameRecordWriteManage {

	Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po);

	int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po);

	int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordPO po);
}
	