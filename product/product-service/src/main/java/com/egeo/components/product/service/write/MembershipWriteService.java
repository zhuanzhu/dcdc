package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MembershipDTO;


public interface MembershipWriteService {

	public Long insertMembershipWithTx(MembershipDTO dto);

	public int updateMembershipWithTx(MembershipDTO dto);

	public int deleteMembershipWithTx(MembershipDTO dto);
}
	