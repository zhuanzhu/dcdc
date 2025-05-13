package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyCallCenterReadService {

	public CompanyCallCenterDTO findCompanyCallCenterById(Long id);

	public PageResult<CompanyCallCenterDTO> findCompanyCallCenterOfPage(CompanyCallCenterDTO dto,Pagination page);

	public List<CompanyCallCenterDTO> findCompanyCallCenterAll(CompanyCallCenterDTO dto);
}
	