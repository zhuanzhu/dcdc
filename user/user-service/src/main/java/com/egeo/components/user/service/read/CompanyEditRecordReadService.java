package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyEditRecordReadService {

	public CompanyEditRecordDTO findCompanyEditRecordById(CompanyEditRecordDTO dto);

	public PageResult<CompanyEditRecordDTO> findCompanyEditRecordOfPage(CompanyEditRecordDTO dto,Pagination page);

	public List<CompanyEditRecordDTO> findCompanyEditRecordAll(CompanyEditRecordDTO dto);
}
	