package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsInstCfgReadService;
import com.egeo.components.cms.manage.read.CmsInstCfgReadManage;
import com.egeo.components.cms.condition.CmsInstCfgCondition;
import com.egeo.components.cms.converter.CmsInstCfgConverter;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.po.CmsInstCfgPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsInstCfgReadService")
public class CmsInstCfgReadServiceImpl  implements CmsInstCfgReadService {
	@Autowired
	private CmsInstCfgReadManage cmsInstCfgReadManage;

	@Override
	public CmsInstCfgDTO findCmsInstCfgById(CmsInstCfgDTO dto) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
		CmsInstCfgPO list = cmsInstCfgReadManage.findCmsInstCfgById(po);		
		return CmsInstCfgConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsInstCfgDTO> findCmsInstCfgOfPage(CmsInstCfgDTO dto, Pagination page) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
        PageResult<CmsInstCfgPO> pageResult = cmsInstCfgReadManage.findCmsInstCfgOfPage(po, page);
        
        List<CmsInstCfgDTO> list = CmsInstCfgConverter.toDTO(pageResult.getList());
        PageResult<CmsInstCfgDTO> result = new PageResult<CmsInstCfgDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsInstCfgDTO> findCmsInstCfgAll(CmsInstCfgDTO dto) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
		List<CmsInstCfgPO> list = cmsInstCfgReadManage.findCmsInstCfgAll(po);		
		return CmsInstCfgConverter.toDTO(list);
	}

	@Override
	public List<CmsInstCfgDTO> findPageInstCfgByPageId(Long pageId, List<Long> instIdList) {
		List<CmsInstCfgCondition> list = cmsInstCfgReadManage.findPageInstCfgByPageId(pageId, instIdList);
		return CmsInstCfgConverter.conditionToDTO(list);
	}
	
}
	