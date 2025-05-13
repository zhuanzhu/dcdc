package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsTemplateCfgReadService;
import com.egeo.components.cms.manage.read.CmsTemplateCfgReadManage;
import com.egeo.components.cms.converter.CmsTemplateCfgConverter;
import com.egeo.components.cms.dto.CmsTemplateCfgDTO;
import com.egeo.components.cms.po.CmsTemplateCfgPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsTemplateCfgReadService")
public class CmsTemplateCfgReadServiceImpl  implements CmsTemplateCfgReadService {
	@Autowired
	private CmsTemplateCfgReadManage cmsTemplateCfgReadManage;

	@Override
	public CmsTemplateCfgDTO findCmsTemplateCfgById(CmsTemplateCfgDTO dto) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
		CmsTemplateCfgPO list = cmsTemplateCfgReadManage.findCmsTemplateCfgById(po);		
		return CmsTemplateCfgConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsTemplateCfgDTO> findCmsTemplateCfgOfPage(CmsTemplateCfgDTO dto, Pagination page) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
        PageResult<CmsTemplateCfgPO> pageResult = cmsTemplateCfgReadManage.findCmsTemplateCfgOfPage(po, page);
        
        List<CmsTemplateCfgDTO> list = CmsTemplateCfgConverter.toDTO(pageResult.getList());
        PageResult<CmsTemplateCfgDTO> result = new PageResult<CmsTemplateCfgDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsTemplateCfgDTO> findCmsTemplateCfgAll(CmsTemplateCfgDTO dto) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
		List<CmsTemplateCfgPO> list = cmsTemplateCfgReadManage.findCmsTemplateCfgAll(po);		
		return CmsTemplateCfgConverter.toDTO(list);
	}
}
	