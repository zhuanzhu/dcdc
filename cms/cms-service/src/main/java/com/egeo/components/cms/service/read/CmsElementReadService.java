package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsElementReadService {

	public CmsElementDTO findCmsElementById(CmsElementDTO dto);

	public PageResult<CmsElementDTO> findCmsElementOfPage(CmsElementDTO dto,Pagination page);

	public List<CmsElementDTO> findCmsElementAll(CmsElementDTO dto);

	public List<CmsElementDTO> findCmsElementByTemplateId(Long id);

	public List<CmsElementDTO> findMaxVersionByElementIdList(List<Long> elementIdList);
}
	