package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitRecordDTO;


public interface StandardProductUnitRecordWriteService {

	public Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);

	public int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);

	public int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);
}
	