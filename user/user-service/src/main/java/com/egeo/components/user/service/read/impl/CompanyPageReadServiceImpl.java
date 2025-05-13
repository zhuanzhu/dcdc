package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.converter.CompanyPageConverter;
import com.egeo.components.user.dao.read.CompanyPageReadDAO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.manage.read.CompanyPageReadManage;
import com.egeo.components.user.po.CompanyPagePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyPageReadService")
public class CompanyPageReadServiceImpl implements CompanyPageReadService {
	@Autowired
	private CompanyPageReadDAO companyPageReadDAO;

	@Override
	public CompanyPageDTO findCompanyPageById(CompanyPageDTO dto) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		CompanyPagePO companyPagepo = new CompanyPagePO();
		companyPagepo.setId(po.getId());
		CompanyPagePO list = companyPageReadDAO.findById(companyPagepo);	
		return CompanyPageConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyPageDTO> findCompanyPageOfPage(CompanyPageDTO dto, Pagination page) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		
		PageResult<CompanyPagePO> pageResult = new PageResult<CompanyPagePO>();
		List<CompanyPagePO> listT = null;

		int cnt = companyPageReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = companyPageReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<CompanyPagePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<CompanyPageDTO> list = CompanyPageConverter.toDTO(pageResult.getList());
        PageResult<CompanyPageDTO> result = new PageResult<CompanyPageDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		List<CompanyPagePO> list = companyPageReadDAO.findAll(po,null);		
		return CompanyPageConverter.toDTO(list);
	}
}
	