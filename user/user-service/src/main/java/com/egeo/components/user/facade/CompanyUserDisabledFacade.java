package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.service.read.CompanyUserDisabledReadService;
import com.egeo.components.user.service.write.CompanyUserDisabledWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CompanyUserDisabledFacade {
	
	@Resource
	private CompanyUserDisabledReadService companyUserDisabledReadService;
	
	@Resource
	private CompanyUserDisabledWriteService companyUserDisabledWriteService;
	
	
	public CompanyUserDisabledDTO findCompanyUserDisabledById(CompanyUserDisabledDTO dto){
		
		return companyUserDisabledReadService.findCompanyUserDisabledById(dto);
	}

	public PageResult<CompanyUserDisabledDTO> findCompanyUserDisabledOfPage(CompanyUserDisabledDTO dto,Pagination page){
		
		return companyUserDisabledReadService.findCompanyUserDisabledOfPage(dto, page);
		
	}

	public List<CompanyUserDisabledDTO> findCompanyUserDisabledAll(CompanyUserDisabledDTO dto){
		
		return companyUserDisabledReadService.findCompanyUserDisabledAll(dto);
		
	}

	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto){
		
		return companyUserDisabledWriteService.insertCompanyUserDisabledWithTx(dto);
	}

	public int updateCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto){
		
		return companyUserDisabledWriteService.updateCompanyUserDisabledWithTx(dto);
	}

	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto){
		
		return companyUserDisabledWriteService.deleteCompanyUserDisabledWithTx(dto);
		
	}

}
	