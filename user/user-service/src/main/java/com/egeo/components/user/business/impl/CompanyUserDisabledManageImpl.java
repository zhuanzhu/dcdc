package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.CompanyUserDisabledManage;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.facade.CompanyUserDisabledFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyUserDisabled")
public class CompanyUserDisabledManageImpl implements CompanyUserDisabledManage{

	
	@Resource(name="companyUserDisabledFacade")
	private CompanyUserDisabledFacade companyUserDisabledFacade;

	@Override
	public CompanyUserDisabledDTO findCompanyUserDisabledById(CompanyUserDisabledDTO dto) {
		return companyUserDisabledFacade.findCompanyUserDisabledById(dto);
	}

	@Override
	public PageResult<CompanyUserDisabledDTO> findCompanyUserDisabledOfPage(CompanyUserDisabledDTO dto, Pagination page) {
		return companyUserDisabledFacade.findCompanyUserDisabledOfPage(dto, page);
	}

	@Override
	public List<CompanyUserDisabledDTO> findCompanyUserDisabledAll(CompanyUserDisabledDTO dto) {
		return companyUserDisabledFacade.findCompanyUserDisabledAll(dto);
	}

	@Override
	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		return companyUserDisabledFacade.insertCompanyUserDisabledWithTx(dto);
	}

	@Override
	public int updateCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		return companyUserDisabledFacade.updateCompanyUserDisabledWithTx(dto);
	}

	@Override
	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		return companyUserDisabledFacade.deleteCompanyUserDisabledWithTx(dto);
	}


}
	