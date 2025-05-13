package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.HeadImportRecordsDTO;


public interface HeadImportRecordsWriteService {

	public Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto);

	public int updateHeadImportRecordsWithTx(HeadImportRecordsDTO dto);

	public int deleteHeadImportRecordsWithTx(HeadImportRecordsDTO dto);
}
	