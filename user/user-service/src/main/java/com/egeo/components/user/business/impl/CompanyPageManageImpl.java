package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.CompanyPageManage;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.facade.CompanyPageFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyPage")
public class CompanyPageManageImpl implements CompanyPageManage{

	
	@Resource(name="companyPageFacade")
	private CompanyPageFacade companyPageFacade;

	@Override
	public CompanyPageDTO findCompanyPageById(CompanyPageDTO dto) {
		return companyPageFacade.findCompanyPageById(dto);
	}

	@Override
	public PageResult<CompanyPageDTO> findCompanyPageOfPage(CompanyPageDTO dto, Pagination page) {
		return companyPageFacade.findCompanyPageOfPage(dto, page);
	}

	@Override
	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto) {
		return companyPageFacade.findCompanyPageAll(dto);
	}

	@Override
	public Long insertCompanyPageWithTx(CompanyPageDTO dto) {
		return companyPageFacade.insertCompanyPageWithTx(dto);
	}

	@Override
	public int updateCompanyPageWithTx(CompanyPageDTO dto) {
		return companyPageFacade.updateCompanyPageWithTx(dto);
	}

	@Override
	public int deleteCompanyPageWithTx(CompanyPageDTO dto) {
		return companyPageFacade.deleteCompanyPageWithTx(dto);
	}


}
	