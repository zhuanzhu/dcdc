package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.CompanyUserDisabledDTO;


public interface CompanyUserDisabledWriteService {

	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);

	public int updateCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);

	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);

	public int updateRevalidationByCompanyId(Integer revalidation,Long companyId);
}
	