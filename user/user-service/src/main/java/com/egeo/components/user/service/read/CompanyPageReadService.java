package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyPageReadService {

	public CompanyPageDTO findCompanyPageById(CompanyPageDTO dto);

	public PageResult<CompanyPageDTO> findCompanyPageOfPage(CompanyPageDTO dto,Pagination page);

	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto);
}
	