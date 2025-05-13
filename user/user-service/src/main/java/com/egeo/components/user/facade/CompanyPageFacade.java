package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.service.write.CompanyPageWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CompanyPageFacade {
	
	@Resource
	private CompanyPageReadService companyPageReadService;
	
	@Resource
	private CompanyPageWriteService companyPageWriteService;
	
	
	public CompanyPageDTO findCompanyPageById(CompanyPageDTO dto){
		
		return companyPageReadService.findCompanyPageById(dto);
	}

	public PageResult<CompanyPageDTO> findCompanyPageOfPage(CompanyPageDTO dto,Pagination page){
		
		return companyPageReadService.findCompanyPageOfPage(dto, page);
		
	}

	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto){
		
		return companyPageReadService.findCompanyPageAll(dto);
		
	}

	public Long insertCompanyPageWithTx(CompanyPageDTO dto){
		
		return companyPageWriteService.insertCompanyPageWithTx(dto);
	}

	public int updateCompanyPageWithTx(CompanyPageDTO dto){
		
		return companyPageWriteService.updateCompanyPageWithTx(dto);
	}

	public int deleteCompanyPageWithTx(CompanyPageDTO dto){
		
		return companyPageWriteService.deleteCompanyPageWithTx(dto);
		
	}

}
	