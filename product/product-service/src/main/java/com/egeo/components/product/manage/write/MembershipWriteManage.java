package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MembershipPO;


public interface MembershipWriteManage {

	Long insertMembershipWithTx(MembershipPO po);

	int updateMembershipWithTx(MembershipPO po);

	int deleteMembershipWithTx(MembershipPO po);
}
	