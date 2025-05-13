package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsTemplateElementDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsTemplateElementReadService {

	public CmsTemplateElementDTO findCmsTemplateElementById(CmsTemplateElementDTO dto);

	public PageResult<CmsTemplateElementDTO> findCmsTemplateElementOfPage(CmsTemplateElementDTO dto,Pagination page);

	public List<CmsTemplateElementDTO> findCmsTemplateElementAll(CmsTemplateElementDTO dto);
}
	