package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.CompanyPageDTO;


public interface CompanyPageWriteService {

	public Long insertCompanyPageWithTx(CompanyPageDTO dto);

	public int updateCompanyPageWithTx(CompanyPageDTO dto);

	public int deleteCompanyPageWithTx(CompanyPageDTO dto);
}
	