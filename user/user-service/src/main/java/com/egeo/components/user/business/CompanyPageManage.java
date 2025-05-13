package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyPageManage {

	public CompanyPageDTO findCompanyPageById(CompanyPageDTO dto);	

	public PageResult<CompanyPageDTO> findCompanyPageOfPage(CompanyPageDTO dto,Pagination page);

	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto);

	Long insertCompanyPageWithTx(CompanyPageDTO dto);

	int updateCompanyPageWithTx(CompanyPageDTO dto);

	int deleteCompanyPageWithTx(CompanyPageDTO dto);
}
	