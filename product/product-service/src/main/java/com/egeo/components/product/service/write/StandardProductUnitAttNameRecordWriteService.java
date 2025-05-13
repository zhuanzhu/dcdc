package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;


public interface StandardProductUnitAttNameRecordWriteService {

	public Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);

	public int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);

	public int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);
}
	