package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;


public interface StandardProductUnitDescriptionRecordWriteService {

	public Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto);

	public int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto);

	public int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto);
}
	