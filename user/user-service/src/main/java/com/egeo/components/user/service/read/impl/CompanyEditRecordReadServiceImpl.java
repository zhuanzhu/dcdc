package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CompanyEditRecordReadService;
import com.egeo.components.user.converter.CompanyEditRecordConverter;
import com.egeo.components.user.dao.read.CompanyEditRecordReadDAO;
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.po.CompanyEditRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyEditRecordReadService")
public class CompanyEditRecordReadServiceImpl implements CompanyEditRecordReadService {
	@Autowired
	private CompanyEditRecordReadDAO companyEditRecordReadDAO;

	@Override
	public CompanyEditRecordDTO findCompanyEditRecordById(CompanyEditRecordDTO dto) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		CompanyEditRecordPO companyEditRecordpo = new CompanyEditRecordPO();
		companyEditRecordpo.setId(po.getId());
		CompanyEditRecordPO list = companyEditRecordReadDAO.findById(companyEditRecordpo);	
		return CompanyEditRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyEditRecordDTO> findCompanyEditRecordOfPage(CompanyEditRecordDTO dto, Pagination page) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		
		PageResult<CompanyEditRecordPO> pageResult = new PageResult<CompanyEditRecordPO>();
		List<CompanyEditRecordPO> listT = null;

		int cnt = companyEditRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = companyEditRecordReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<CompanyEditRecordPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<CompanyEditRecordDTO> list = CompanyEditRecordConverter.toDTO(pageResult.getList());
        PageResult<CompanyEditRecordDTO> result = new PageResult<CompanyEditRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CompanyEditRecordDTO> findCompanyEditRecordAll(CompanyEditRecordDTO dto) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		List<CompanyEditRecordPO> list = companyEditRecordReadDAO.findAll(po,null);	
		return CompanyEditRecordConverter.toDTO(list);
	}
}
	