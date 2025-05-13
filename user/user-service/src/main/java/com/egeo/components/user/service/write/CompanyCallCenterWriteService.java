package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.CompanyCallCenterDTO;


public interface CompanyCallCenterWriteService {

	public Long insertCompanyCallCenterWithTx(CompanyCallCenterDTO dto);

	public int updateCompanyCallCenterWithTx(CompanyCallCenterDTO dto);

	public int deleteCompanyCallCenterWithTx(CompanyCallCenterDTO dto);
}
	