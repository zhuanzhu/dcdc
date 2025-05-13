package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsPageTabReadService;
import com.egeo.components.cms.manage.read.CmsPageTabReadManage;
import com.egeo.components.cms.condition.CmsPageTabCondition;
import com.egeo.components.cms.converter.CmsPageTabConverter;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.po.CmsPageTabPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageTabReadService")
public class CmsPageTabReadServiceImpl  implements CmsPageTabReadService {
	@Autowired
	private CmsPageTabReadManage cmsPageTabReadManage;

	@Override
	public CmsPageTabDTO findCmsPageTabById(CmsPageTabDTO dto) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
		CmsPageTabPO list = cmsPageTabReadManage.findCmsPageTabById(po);		
		return CmsPageTabConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsPageTabDTO> findCmsPageTabOfPage(CmsPageTabDTO dto, Pagination page) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
        PageResult<CmsPageTabPO> pageResult = cmsPageTabReadManage.findCmsPageTabOfPage(po, page);
        
        List<CmsPageTabDTO> list = CmsPageTabConverter.toDTO(pageResult.getList());
        PageResult<CmsPageTabDTO> result = new PageResult<CmsPageTabDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
		List<CmsPageTabPO> list = cmsPageTabReadManage.findCmsPageTabAll(po);		
		return CmsPageTabConverter.toDTO(list);
	}

	@Override
	public List<CmsPageTabDTO> findCmsPageTabCondition(CmsPageTabDTO dto) {
		CmsPageTabCondition condition = CmsPageTabConverter.dtoToCondition(dto);
		List<CmsPageTabCondition> list = cmsPageTabReadManage.findCmsPageTabCondition(condition);
		return CmsPageTabConverter.conditionToDTO(list);
	}

	@Override
	public List<CmsPageTabDTO> findDefaultCmsPageTabFront(Long platformId) {
		return CmsPageTabConverter.conditionToDTO(cmsPageTabReadManage.findDefaultCmsPageTabFront(platformId));
	}
}
	