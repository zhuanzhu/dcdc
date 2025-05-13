package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitMembershipDTO;


public interface StandardUnitMembershipWriteService {

	public Long insertStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);

	public int updateStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);

	public int deleteStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);
}
	