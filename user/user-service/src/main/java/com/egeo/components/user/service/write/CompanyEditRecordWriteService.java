package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.CompanyEditRecordDTO;


public interface CompanyEditRecordWriteService {

	public Long insertCompanyEditRecordWithTx(CompanyEditRecordDTO dto);

	public int updateCompanyEditRecordWithTx(CompanyEditRecordDTO dto);

	public int deleteCompanyEditRecordWithTx(CompanyEditRecordDTO dto);
}
	