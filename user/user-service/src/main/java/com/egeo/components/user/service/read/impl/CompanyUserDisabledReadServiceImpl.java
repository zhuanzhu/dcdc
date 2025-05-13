package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CompanyUserDisabledReadService;
import com.egeo.components.user.converter.CompanyUserDisabledConverter;
import com.egeo.components.user.dao.read.CompanyUserDisabledReadDAO;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.po.CompanyUserDisabledPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyUserDisabledReadService")
public class CompanyUserDisabledReadServiceImpl implements CompanyUserDisabledReadService {
	@Autowired
	private CompanyUserDisabledReadDAO companyUserDisabledReadDAO;

	@Override
	public CompanyUserDisabledDTO findCompanyUserDisabledById(CompanyUserDisabledDTO dto) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		CompanyUserDisabledPO companyUserDisabledpo = new CompanyUserDisabledPO();
		companyUserDisabledpo.setId(po.getId());
		CompanyUserDisabledPO list = companyUserDisabledReadDAO.findById(companyUserDisabledpo);	
		return CompanyUserDisabledConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyUserDisabledDTO> findCompanyUserDisabledOfPage(CompanyUserDisabledDTO dto, Pagination page) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		
		PageResult<CompanyUserDisabledPO> pageResult = new PageResult<CompanyUserDisabledPO>();
		List<CompanyUserDisabledPO> listT = null;

		int cnt = companyUserDisabledReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = companyUserDisabledReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<CompanyUserDisabledPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<CompanyUserDisabledDTO> list = CompanyUserDisabledConverter.toDTO(pageResult.getList());
        PageResult<CompanyUserDisabledDTO> result = new PageResult<CompanyUserDisabledDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CompanyUserDisabledDTO> findCompanyUserDisabledAll(CompanyUserDisabledDTO dto) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		List<CompanyUserDisabledPO> list = companyUserDisabledReadDAO.findAll(po,null);		
		return CompanyUserDisabledConverter.toDTO(list);
	}

	@Override
	public Integer findRevalidationByCompanyId(Long companyId){
		Integer revalidation = companyUserDisabledReadDAO.findRevalidationByCompanyId(companyId);
		return revalidation;
	}

	@Override
	public List<Long> findUsersByCompanyId(Long companyId) {
		List<Long> list = companyUserDisabledReadDAO.findUsersByCompanyId(companyId);
		return list;
	}
}
	