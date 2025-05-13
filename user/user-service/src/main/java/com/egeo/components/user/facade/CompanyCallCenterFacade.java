package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.components.user.service.read.CompanyCallCenterReadService;
import com.egeo.components.user.service.write.CompanyCallCenterWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CompanyCallCenterFacade {
	
	@Resource
	private CompanyCallCenterReadService companyCallCenterReadService;
	
	@Resource
	private CompanyCallCenterWriteService companyCallCenterWriteService;
	
	
	public CompanyCallCenterDTO findCompanyCallCenterById(Long id){
		
		return companyCallCenterReadService.findCompanyCallCenterById(id);
	}

	public PageResult<CompanyCallCenterDTO> findCompanyCallCenterOfPage(CompanyCallCenterDTO dto,Pagination page){
		
		return companyCallCenterReadService.findCompanyCallCenterOfPage(dto, page);
		
	}

	public List<CompanyCallCenterDTO> findCompanyCallCenterAll(CompanyCallCenterDTO dto){
		
		return companyCallCenterReadService.findCompanyCallCenterAll(dto);
		
	}

	public Long insertCompanyCallCenterWithTx(CompanyCallCenterDTO dto){
		
		return companyCallCenterWriteService.insertCompanyCallCenterWithTx(dto);
	}

	public int updateCompanyCallCenterWithTx(CompanyCallCenterDTO dto){
		
		return companyCallCenterWriteService.updateCompanyCallCenterWithTx(dto);
	}

	public int deleteCompanyCallCenterWithTx(CompanyCallCenterDTO dto){
		
		return companyCallCenterWriteService.deleteCompanyCallCenterWithTx(dto);
		
	}

}
	