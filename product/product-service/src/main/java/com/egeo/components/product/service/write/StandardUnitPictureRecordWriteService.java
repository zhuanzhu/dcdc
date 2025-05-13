package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;


public interface StandardUnitPictureRecordWriteService {

	public Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);

	public int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);

	public int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);
}
	