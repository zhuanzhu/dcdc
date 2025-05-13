package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsPageTabCompanyReadService;
import com.egeo.components.cms.manage.read.CmsPageTabCompanyReadManage;
import com.egeo.components.cms.converter.CmsPageTabCompanyConverter;
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.po.CmsPageTabCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageTabCompanyReadService")
public class CmsPageTabCompanyReadServiceImpl  implements CmsPageTabCompanyReadService {
	@Autowired
	private CmsPageTabCompanyReadManage cmsPageTabCompanyReadManage;

	@Override
	public CmsPageTabCompanyDTO findCmsPageTabCompanyById(CmsPageTabCompanyDTO dto) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
		CmsPageTabCompanyPO list = cmsPageTabCompanyReadManage.findCmsPageTabCompanyById(po);		
		return CmsPageTabCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsPageTabCompanyDTO> findCmsPageTabCompanyOfPage(CmsPageTabCompanyDTO dto, Pagination page) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
        PageResult<CmsPageTabCompanyPO> pageResult = cmsPageTabCompanyReadManage.findCmsPageTabCompanyOfPage(po, page);
        
        List<CmsPageTabCompanyDTO> list = CmsPageTabCompanyConverter.toDTO(pageResult.getList());
        PageResult<CmsPageTabCompanyDTO> result = new PageResult<CmsPageTabCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsPageTabCompanyDTO> findCmsPageTabCompanyAll(CmsPageTabCompanyDTO dto) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
		List<CmsPageTabCompanyPO> list = cmsPageTabCompanyReadManage.findCmsPageTabCompanyAll(po);		
		return CmsPageTabCompanyConverter.toDTO(list);
	}
}
	