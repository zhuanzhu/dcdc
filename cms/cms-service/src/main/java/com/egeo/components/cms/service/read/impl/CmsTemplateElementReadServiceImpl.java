package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsTemplateElementReadService;
import com.egeo.components.cms.manage.read.CmsTemplateElementReadManage;
import com.egeo.components.cms.converter.CmsTemplateElementConverter;
import com.egeo.components.cms.dto.CmsTemplateElementDTO;
import com.egeo.components.cms.po.CmsTemplateElementPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsTemplateElementReadService")
public class CmsTemplateElementReadServiceImpl  implements CmsTemplateElementReadService {
	@Autowired
	private CmsTemplateElementReadManage cmsTemplateElementReadManage;

	@Override
	public CmsTemplateElementDTO findCmsTemplateElementById(CmsTemplateElementDTO dto) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
		CmsTemplateElementPO list = cmsTemplateElementReadManage.findCmsTemplateElementById(po);		
		return CmsTemplateElementConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsTemplateElementDTO> findCmsTemplateElementOfPage(CmsTemplateElementDTO dto, Pagination page) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
        PageResult<CmsTemplateElementPO> pageResult = cmsTemplateElementReadManage.findCmsTemplateElementOfPage(po, page);
        
        List<CmsTemplateElementDTO> list = CmsTemplateElementConverter.toDTO(pageResult.getList());
        PageResult<CmsTemplateElementDTO> result = new PageResult<CmsTemplateElementDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsTemplateElementDTO> findCmsTemplateElementAll(CmsTemplateElementDTO dto) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
		List<CmsTemplateElementPO> list = cmsTemplateElementReadManage.findCmsTemplateElementAll(po);		
		return CmsTemplateElementConverter.toDTO(list);
	}
}
	