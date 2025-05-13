package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ExternalLinkReadService {

	public ExternalLinkDTO findExternalLinkById(Long id);

	public PageResult<ExternalLinkDTO> findExternalLinkOfPage(ExternalLinkDTO dto,Pagination page);

	public List<ExternalLinkDTO> findExternalLinkAll(ExternalLinkDTO dto);
}
	