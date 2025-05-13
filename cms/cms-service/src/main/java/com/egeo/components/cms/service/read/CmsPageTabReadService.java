package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsPageTabReadService {

	public CmsPageTabDTO findCmsPageTabById(CmsPageTabDTO dto);

	public PageResult<CmsPageTabDTO> findCmsPageTabOfPage(CmsPageTabDTO dto,Pagination page);

	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto);

	public List<CmsPageTabDTO> findCmsPageTabCondition(CmsPageTabDTO dto);

    List<CmsPageTabDTO> findDefaultCmsPageTabFront(Long platformId);
}
	