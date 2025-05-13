package com.egeo.components.user.service.read;


import java.util.List;

import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyUserDisabledReadService {

	public CompanyUserDisabledDTO findCompanyUserDisabledById(CompanyUserDisabledDTO dto);

	public PageResult<CompanyUserDisabledDTO> findCompanyUserDisabledOfPage(CompanyUserDisabledDTO dto,Pagination page);

	public List<CompanyUserDisabledDTO> findCompanyUserDisabledAll(CompanyUserDisabledDTO dto);

	public Integer findRevalidationByCompanyId(Long companyId);

	public List<Long> findUsersByCompanyId (Long companyId);

}
	