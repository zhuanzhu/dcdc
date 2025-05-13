package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MembershipUserPO;

import java.util.Date;
import java.util.List;


public interface MembershipUserWriteManage {

	Long insertMembershipUserWithTx(MembershipUserPO po);

	int updateMembershipUserWithTx(MembershipUserPO po);

	int deleteMembershipUserWithTx(MembershipUserPO po);

    Boolean updateMembershipUserWithTx(List<Long> membewrshipUserId);

    boolean updateMembershipUserWithTx(List<Long> membewrshipUserId, Date endTime);
}
	