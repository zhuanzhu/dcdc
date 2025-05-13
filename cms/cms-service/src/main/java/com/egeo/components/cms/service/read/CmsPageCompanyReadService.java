package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsPageCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsPageCompanyReadService {

	public CmsPageCompanyDTO findCmsPageCompanyById(CmsPageCompanyDTO dto);

	public PageResult<CmsPageCompanyDTO> findCmsPageCompanyOfPage(CmsPageCompanyDTO dto,Pagination page);

	public List<CmsPageCompanyDTO> findCmsPageCompanyAll(CmsPageCompanyDTO dto);
}
	