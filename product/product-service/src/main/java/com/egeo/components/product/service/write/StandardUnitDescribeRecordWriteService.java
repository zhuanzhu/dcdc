package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;


public interface StandardUnitDescribeRecordWriteService {

	public Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);

	public int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);

	public int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);
}
	