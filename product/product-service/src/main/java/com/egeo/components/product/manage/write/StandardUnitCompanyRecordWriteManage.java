package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitCompanyRecordPO;


public interface StandardUnitCompanyRecordWriteManage {

	Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po);

	int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po);

	int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordPO po);
}
	