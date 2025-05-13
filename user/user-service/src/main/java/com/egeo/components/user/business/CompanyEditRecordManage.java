package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyEditRecordManage {

	public CompanyEditRecordDTO findCompanyEditRecordById(CompanyEditRecordDTO dto);	

	public PageResult<CompanyEditRecordDTO> findCompanyEditRecordOfPage(CompanyEditRecordDTO dto,Pagination page);

	public List<CompanyEditRecordDTO> findCompanyEditRecordAll(CompanyEditRecordDTO dto);

	Long insertCompanyEditRecordWithTx(CompanyEditRecordDTO dto);

	int updateCompanyEditRecordWithTx(CompanyEditRecordDTO dto);

	int deleteCompanyEditRecordWithTx(CompanyEditRecordDTO dto);
}
	