package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;


public interface StandardProductUnitDescriptionRecordWriteManage {

	Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po);

	int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po);

	int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordPO po);
}
	