package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;


public interface SellPlatformStandardUnitRecordWriteService {

	public Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);

	public int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);

	public int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto);
}
	