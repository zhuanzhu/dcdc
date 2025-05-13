package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;


public interface StandardUnitCompanyRecordWriteService {

	public Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);

	public int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);

	public int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);
}
	