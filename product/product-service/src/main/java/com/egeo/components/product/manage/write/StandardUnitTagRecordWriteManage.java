package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitTagRecordPO;


public interface StandardUnitTagRecordWriteManage {

	Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordPO po);

	int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordPO po);

	int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordPO po);
}
	