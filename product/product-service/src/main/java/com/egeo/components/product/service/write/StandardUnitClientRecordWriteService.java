package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitClientRecordDTO;


public interface StandardUnitClientRecordWriteService {

	public Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);

	public int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);

	public int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);
}
	