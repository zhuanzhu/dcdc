package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitClientRecordPO;


public interface StandardUnitClientRecordWriteManage {

	Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordPO po);

	int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordPO po);

	int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordPO po);
}
	