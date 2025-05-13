package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsTemplateReadService;
import com.egeo.components.cms.manage.read.CmsTemplateReadManage;
import com.egeo.components.cms.converter.CmsTemplateConverter;
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.po.CmsTemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsTemplateReadService")
public class CmsTemplateReadServiceImpl  implements CmsTemplateReadService {
	@Autowired
	private CmsTemplateReadManage cmsTemplateReadManage;

	@Override
	public CmsTemplateDTO findCmsTemplateById(CmsTemplateDTO dto) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
		CmsTemplatePO list = cmsTemplateReadManage.findCmsTemplateById(po);		
		return CmsTemplateConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsTemplateDTO> findCmsTemplateOfPage(CmsTemplateDTO dto, Pagination page,Integer searchType) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
        PageResult<CmsTemplatePO> pageResult = cmsTemplateReadManage.findCmsTemplateOfPage(po, page,searchType);
        
        List<CmsTemplateDTO> list = CmsTemplateConverter.toDTO(pageResult.getList());
        PageResult<CmsTemplateDTO> result = new PageResult<CmsTemplateDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsTemplateDTO> findCmsTemplateAll(CmsTemplateDTO dto) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
		List<CmsTemplatePO> list = cmsTemplateReadManage.findCmsTemplateAll(po,dto.getSearchType());		
		return CmsTemplateConverter.toDTO(list);
	}
}
	