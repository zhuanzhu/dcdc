package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.components.product.po.MembershipPO;

import java.util.List;


public interface MembershipAuthorityWriteManage {

	Long insertMembershipAuthorityWithTx(MembershipAuthorityPO po);

	int updateMembershipAuthorityWithTx(MembershipAuthorityPO po);

	int deleteMembershipAuthorityWithTx(MembershipAuthorityPO po);

   void updateMembershipAuthorityWithTx(List<MembershipAuthorityPO> membershipAuthorityPOS, MembershipPO membershipPO);

    void insertMembershipAuthorityWithTx(List<MembershipAuthorityPO> membershipAuthorityPOS, MembershipPO membershipPO);
}
	