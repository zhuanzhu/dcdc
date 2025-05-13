package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsInstCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsInstCompanyReadService {

	public CmsInstCompanyDTO findCmsInstCompanyById(CmsInstCompanyDTO dto);

	public PageResult<CmsInstCompanyDTO> findCmsInstCompanyOfPage(CmsInstCompanyDTO dto,Pagination page);

	public List<CmsInstCompanyDTO> findCmsInstCompanyAll(CmsInstCompanyDTO dto);
}
	