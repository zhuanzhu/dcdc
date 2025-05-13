package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsInstReadService {

	public CmsInstDTO findCmsInstById(CmsInstDTO dto);

	public PageResult<CmsInstDTO> findCmsInstOfPage(CmsInstDTO dto,Pagination page);

	public List<CmsInstDTO> findCmsInstAll(CmsInstDTO dto);

	public PageResult<CmsInstDTO> findByPageAndCompanyOfPage(CmsInstDTO dto, Long companyId, Long companyAllId, Pagination page);
}
	