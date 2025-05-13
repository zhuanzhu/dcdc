package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitRecordMembershipPO;


public interface StandardUnitRecordMembershipWriteManage {

	Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po);

	int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po);

	int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipPO po);
}
	