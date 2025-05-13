package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsTemplateCfgDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsTemplateCfgReadService {

	public CmsTemplateCfgDTO findCmsTemplateCfgById(CmsTemplateCfgDTO dto);

	public PageResult<CmsTemplateCfgDTO> findCmsTemplateCfgOfPage(CmsTemplateCfgDTO dto,Pagination page);

	public List<CmsTemplateCfgDTO> findCmsTemplateCfgAll(CmsTemplateCfgDTO dto);
}
	