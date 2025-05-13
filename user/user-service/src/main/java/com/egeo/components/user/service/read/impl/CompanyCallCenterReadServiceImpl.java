package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CompanyCallCenterReadService;
import com.egeo.components.user.converter.CompanyCallCenterConverter;
import com.egeo.components.user.dao.read.CompanyCallCenterReadDAO;
import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.components.user.po.CompanyCallCenterPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyCallCenterReadService")
public class CompanyCallCenterReadServiceImpl implements CompanyCallCenterReadService {
	@Autowired
	private CompanyCallCenterReadDAO companyCallCenterReadDAO;

	@Override
	public CompanyCallCenterDTO findCompanyCallCenterById(Long id) {
		
		CompanyCallCenterPO po = new CompanyCallCenterPO();
		po.setId(id);
		CompanyCallCenterPO list = companyCallCenterReadDAO.findById(po);		
		return CompanyCallCenterConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyCallCenterDTO> findCompanyCallCenterOfPage(CompanyCallCenterDTO dto, Pagination page) {
		CompanyCallCenterPO po = CompanyCallCenterConverter.toPO(dto);
		PageResult<CompanyCallCenterPO> pageResult = new PageResult<CompanyCallCenterPO>();
		List<CompanyCallCenterPO> listT = null;

		int cnt = companyCallCenterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = companyCallCenterReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<CompanyCallCenterPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<CompanyCallCenterDTO> list = CompanyCallCenterConverter.toDTO(pageResult.getList());
        PageResult<CompanyCallCenterDTO> result = new PageResult<CompanyCallCenterDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CompanyCallCenterDTO> findCompanyCallCenterAll(CompanyCallCenterDTO dto) {
		CompanyCallCenterPO po = CompanyCallCenterConverter.toPO(dto);
		List<CompanyCallCenterPO> list = companyCallCenterReadDAO.findAll(po,null);	
		return CompanyCallCenterConverter.toDTO(list);
	}
}
	