package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsPageTabCompanyReadService {

	public CmsPageTabCompanyDTO findCmsPageTabCompanyById(CmsPageTabCompanyDTO dto);

	public PageResult<CmsPageTabCompanyDTO> findCmsPageTabCompanyOfPage(CmsPageTabCompanyDTO dto,Pagination page);

	public List<CmsPageTabCompanyDTO> findCmsPageTabCompanyAll(CmsPageTabCompanyDTO dto);
}
	