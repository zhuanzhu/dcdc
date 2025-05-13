package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;


public interface StandardUnitRecordMembershipWriteService {

	public Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);

	public int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);

	public int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);
}
	