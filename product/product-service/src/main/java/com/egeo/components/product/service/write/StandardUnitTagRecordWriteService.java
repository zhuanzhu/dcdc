package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitTagRecordDTO;


public interface StandardUnitTagRecordWriteService {

	public Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);

	public int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);

	public int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);
}
	