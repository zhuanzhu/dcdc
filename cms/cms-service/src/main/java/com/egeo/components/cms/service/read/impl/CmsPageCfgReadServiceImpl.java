package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsPageCfgReadService;
import com.egeo.components.cms.manage.read.CmsPageCfgReadManage;
import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.converter.CmsPageCfgConverter;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.po.CmsPageCfgPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageCfgReadService")
public class CmsPageCfgReadServiceImpl  implements CmsPageCfgReadService {
	@Autowired
	private CmsPageCfgReadManage cmsPageCfgReadManage;

	@Override
	public CmsPageCfgDTO findCmsPageCfgById(CmsPageCfgDTO dto) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
		CmsPageCfgPO list = cmsPageCfgReadManage.findCmsPageCfgById(po);		
		return CmsPageCfgConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsPageCfgDTO> findCmsPageCfgOfPage(CmsPageCfgDTO dto, Pagination page) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
        PageResult<CmsPageCfgPO> pageResult = cmsPageCfgReadManage.findCmsPageCfgOfPage(po, page);
        
        List<CmsPageCfgDTO> list = CmsPageCfgConverter.toDTO(pageResult.getList());
        PageResult<CmsPageCfgDTO> result = new PageResult<CmsPageCfgDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsPageCfgDTO> findCmsPageCfgAll(CmsPageCfgDTO dto) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
		List<CmsPageCfgPO> list = cmsPageCfgReadManage.findCmsPageCfgAll(po);		
		return CmsPageCfgConverter.toDTO(list);
	}

	@Override
	public List<CmsPageCfgDTO> findPageCfgByPageId(Long pageId) {
		List<CmsPageCfgCondition> list = cmsPageCfgReadManage.findPageCfgByPageId(pageId);
		return CmsPageCfgConverter.conditionToDTO(list);
	}
}
	