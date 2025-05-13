package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitDescribeRecordPO;


public interface StandardUnitDescribeRecordWriteManage {

	Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordPO po);

	int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordPO po);

	int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordPO po);
}
	