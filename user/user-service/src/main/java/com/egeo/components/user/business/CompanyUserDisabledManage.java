package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyUserDisabledManage {

	public CompanyUserDisabledDTO findCompanyUserDisabledById(CompanyUserDisabledDTO dto);	

	public PageResult<CompanyUserDisabledDTO> findCompanyUserDisabledOfPage(CompanyUserDisabledDTO dto,Pagination page);

	public List<CompanyUserDisabledDTO> findCompanyUserDisabledAll(CompanyUserDisabledDTO dto);

	Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);

	int updateCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);

	int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto);
}
	