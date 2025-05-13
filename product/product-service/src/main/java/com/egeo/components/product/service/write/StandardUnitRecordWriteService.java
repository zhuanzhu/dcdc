package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitRecordDTO;


public interface StandardUnitRecordWriteService {

	public Long insertStandardUnitRecordWithTx(StandardUnitRecordDTO dto);

	public int updateStandardUnitRecordWithTx(StandardUnitRecordDTO dto);

	public int deleteStandardUnitRecordWithTx(StandardUnitRecordDTO dto);
}
	