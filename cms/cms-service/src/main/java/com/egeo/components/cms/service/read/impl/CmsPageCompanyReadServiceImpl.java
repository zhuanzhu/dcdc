package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsPageCompanyReadService;
import com.egeo.components.cms.manage.read.CmsPageCompanyReadManage;
import com.egeo.components.cms.converter.CmsPageCompanyConverter;
import com.egeo.components.cms.dto.CmsPageCompanyDTO;
import com.egeo.components.cms.po.CmsPageCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageCompanyReadService")
public class CmsPageCompanyReadServiceImpl  implements CmsPageCompanyReadService {
	@Autowired
	private CmsPageCompanyReadManage cmsPageCompanyReadManage;

	@Override
	public CmsPageCompanyDTO findCmsPageCompanyById(CmsPageCompanyDTO dto) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
		CmsPageCompanyPO list = cmsPageCompanyReadManage.findCmsPageCompanyById(po);		
		return CmsPageCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsPageCompanyDTO> findCmsPageCompanyOfPage(CmsPageCompanyDTO dto, Pagination page) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
        PageResult<CmsPageCompanyPO> pageResult = cmsPageCompanyReadManage.findCmsPageCompanyOfPage(po, page);
        
        List<CmsPageCompanyDTO> list = CmsPageCompanyConverter.toDTO(pageResult.getList());
        PageResult<CmsPageCompanyDTO> result = new PageResult<CmsPageCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsPageCompanyDTO> findCmsPageCompanyAll(CmsPageCompanyDTO dto) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
		List<CmsPageCompanyPO> list = cmsPageCompanyReadManage.findCmsPageCompanyAll(po);		
		return CmsPageCompanyConverter.toDTO(list);
	}
}
	