package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsTemplateReadService {

	public CmsTemplateDTO findCmsTemplateById(CmsTemplateDTO dto);

	public PageResult<CmsTemplateDTO> findCmsTemplateOfPage(CmsTemplateDTO dto,Pagination page,Integer searchType);

	public List<CmsTemplateDTO> findCmsTemplateAll(CmsTemplateDTO dto);
}
	