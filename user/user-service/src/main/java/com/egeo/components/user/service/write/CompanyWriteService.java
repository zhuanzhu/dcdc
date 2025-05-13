package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;


public interface CompanyWriteService {

	public Long insertCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList);

	public int updateCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList);

	public int deleteCompanyWithTx(CompanyDTO dto);

    void updateCompanyParamWithTX(CompanyDTO dto);
}
	