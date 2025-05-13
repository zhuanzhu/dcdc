package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.CompanyEditRecordManage;
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.service.read.CompanyEditRecordReadService;
import com.egeo.components.user.service.write.CompanyEditRecordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyEditRecord")
public class CompanyEditRecordManageImpl implements CompanyEditRecordManage{

	
	@Resource
	private CompanyEditRecordReadService companyEditRecordReadService;
	
	@Resource
	private CompanyEditRecordWriteService companyEditRecordWriteService;

	@Override
	public CompanyEditRecordDTO findCompanyEditRecordById(CompanyEditRecordDTO dto) {
		return companyEditRecordReadService.findCompanyEditRecordById(dto);
	}

	@Override
	public PageResult<CompanyEditRecordDTO> findCompanyEditRecordOfPage(CompanyEditRecordDTO dto, Pagination page) {
		return companyEditRecordReadService.findCompanyEditRecordOfPage(dto, page);
	}

	@Override
	public List<CompanyEditRecordDTO> findCompanyEditRecordAll(CompanyEditRecordDTO dto) {
		return companyEditRecordReadService.findCompanyEditRecordAll(dto);
	}

	@Override
	public Long insertCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		return companyEditRecordWriteService.insertCompanyEditRecordWithTx(dto);
	}

	@Override
	public int updateCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		return companyEditRecordWriteService.updateCompanyEditRecordWithTx(dto);
	}

	@Override
	public int deleteCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		return companyEditRecordWriteService.deleteCompanyEditRecordWithTx(dto);
	}


}
	