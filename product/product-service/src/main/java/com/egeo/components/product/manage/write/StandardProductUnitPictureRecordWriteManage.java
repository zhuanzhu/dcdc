package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;


public interface StandardProductUnitPictureRecordWriteManage {

	Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po);

	int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po);

	int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordPO po);
}
	