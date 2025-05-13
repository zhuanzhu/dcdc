package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.service.read.CompanyEditRecordReadService;
import com.egeo.components.user.service.write.CompanyEditRecordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CompanyEditRecordFacade {
	
	@Resource
	private CompanyEditRecordReadService companyEditRecordReadService;
	
	@Resource
	private CompanyEditRecordWriteService companyEditRecordWriteService;
	
	
	public CompanyEditRecordDTO findCompanyEditRecordById(CompanyEditRecordDTO dto){
		
		return companyEditRecordReadService.findCompanyEditRecordById(dto);
	}

	public PageResult<CompanyEditRecordDTO> findCompanyEditRecordOfPage(CompanyEditRecordDTO dto,Pagination page){
		
		return companyEditRecordReadService.findCompanyEditRecordOfPage(dto, page);
		
	}

	public List<CompanyEditRecordDTO> findCompanyEditRecordAll(CompanyEditRecordDTO dto){
		
		return companyEditRecordReadService.findCompanyEditRecordAll(dto);
		
	}

	public Long insertCompanyEditRecordWithTx(CompanyEditRecordDTO dto){
		
		return companyEditRecordWriteService.insertCompanyEditRecordWithTx(dto);
	}

	public int updateCompanyEditRecordWithTx(CompanyEditRecordDTO dto){
		
		return companyEditRecordWriteService.updateCompanyEditRecordWithTx(dto);
	}

	public int deleteCompanyEditRecordWithTx(CompanyEditRecordDTO dto){
		
		return companyEditRecordWriteService.deleteCompanyEditRecordWithTx(dto);
		
	}

}
	