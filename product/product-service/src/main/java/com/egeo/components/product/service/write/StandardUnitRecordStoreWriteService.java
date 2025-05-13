package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;


public interface StandardUnitRecordStoreWriteService {

	public Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);

	public int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);

	public int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);
}
	