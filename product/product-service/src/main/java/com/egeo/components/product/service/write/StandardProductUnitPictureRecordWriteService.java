package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;


public interface StandardProductUnitPictureRecordWriteService {

	public Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);

	public int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);

	public int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);
}
	