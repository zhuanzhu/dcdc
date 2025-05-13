package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsInstCompanyReadService;
import com.egeo.components.cms.manage.read.CmsInstCompanyReadManage;
import com.egeo.components.cms.converter.CmsInstCompanyConverter;
import com.egeo.components.cms.dto.CmsInstCompanyDTO;
import com.egeo.components.cms.po.CmsInstCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsInstCompanyReadService")
public class CmsInstCompanyReadServiceImpl  implements CmsInstCompanyReadService {
	@Autowired
	private CmsInstCompanyReadManage cmsInstCompanyReadManage;

	@Override
	public CmsInstCompanyDTO findCmsInstCompanyById(CmsInstCompanyDTO dto) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
		CmsInstCompanyPO list = cmsInstCompanyReadManage.findCmsInstCompanyById(po);		
		return CmsInstCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsInstCompanyDTO> findCmsInstCompanyOfPage(CmsInstCompanyDTO dto, Pagination page) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
        PageResult<CmsInstCompanyPO> pageResult = cmsInstCompanyReadManage.findCmsInstCompanyOfPage(po, page);
        
        List<CmsInstCompanyDTO> list = CmsInstCompanyConverter.toDTO(pageResult.getList());
        PageResult<CmsInstCompanyDTO> result = new PageResult<CmsInstCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsInstCompanyDTO> findCmsInstCompanyAll(CmsInstCompanyDTO dto) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
		List<CmsInstCompanyPO> list = cmsInstCompanyReadManage.findCmsInstCompanyAll(po);		
		return CmsInstCompanyConverter.toDTO(list);
	}
}
	