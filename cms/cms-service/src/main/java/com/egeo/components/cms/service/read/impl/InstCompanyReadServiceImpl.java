package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.InstCompanyReadService;
import com.egeo.components.cms.manage.read.InstCompanyReadManage;
import com.egeo.components.cms.converter.InstCompanyConverter;
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.po.InstCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("instCompanyReadService")
public class InstCompanyReadServiceImpl  implements InstCompanyReadService {
	@Autowired
	private InstCompanyReadManage instCompanyReadManage;

	@Override
	public InstCompanyDTO findInstCompanyById(InstCompanyDTO dto) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
		InstCompanyPO list = instCompanyReadManage.findInstCompanyById(po);		
		return InstCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<InstCompanyDTO> findInstCompanyOfPage(InstCompanyDTO dto, Pagination page) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
        PageResult<InstCompanyPO> pageResult = instCompanyReadManage.findInstCompanyOfPage(po, page);
        
        List<InstCompanyDTO> list = InstCompanyConverter.toDTO(pageResult.getList());
        PageResult<InstCompanyDTO> result = new PageResult<InstCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InstCompanyDTO> findInstCompanyAll(InstCompanyDTO dto) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
		List<InstCompanyPO> list = instCompanyReadManage.findInstCompanyAll(po);		
		return InstCompanyConverter.toDTO(list);
	}

	@Override
	public List<InstCompanyDTO> queryInstCompanyListByInstIdAndCompanyId(Long id, Long companyId, Long companyIdByType) {
		return InstCompanyConverter.toDTO(instCompanyReadManage.queryInstCompanyListByInstIdAndCompanyId(id,companyId, companyIdByType));
	}

}
	