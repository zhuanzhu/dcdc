package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitPictureRecordPO;


public interface StandardUnitPictureRecordWriteManage {

	Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po);

	int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po);

	int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordPO po);
}
	