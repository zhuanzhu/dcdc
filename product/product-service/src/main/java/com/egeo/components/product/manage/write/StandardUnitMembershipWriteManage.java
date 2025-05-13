package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitMembershipPO;


public interface StandardUnitMembershipWriteManage {

	Long insertStandardUnitMembershipWithTx(StandardUnitMembershipPO po);

	int updateStandardUnitMembershipWithTx(StandardUnitMembershipPO po);

	int deleteStandardUnitMembershipWithTx(StandardUnitMembershipPO po);
}
	