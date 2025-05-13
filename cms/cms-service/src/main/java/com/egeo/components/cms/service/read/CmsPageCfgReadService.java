package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsPageCfgReadService {

	public CmsPageCfgDTO findCmsPageCfgById(CmsPageCfgDTO dto);

	public PageResult<CmsPageCfgDTO> findCmsPageCfgOfPage(CmsPageCfgDTO dto,Pagination page);

	public List<CmsPageCfgDTO> findCmsPageCfgAll(CmsPageCfgDTO dto);
	
	public List<CmsPageCfgDTO> findPageCfgByPageId(Long pageId);
}
	