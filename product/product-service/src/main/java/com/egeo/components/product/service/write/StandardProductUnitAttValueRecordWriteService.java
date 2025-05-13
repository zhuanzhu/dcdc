package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;


public interface StandardProductUnitAttValueRecordWriteService {

	public Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);

	public int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);

	public int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);
}
	