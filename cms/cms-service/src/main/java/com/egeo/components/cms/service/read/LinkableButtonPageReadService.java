package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LinkableButtonPageReadService {

	public LinkableButtonPageDTO findLinkableButtonPageById(LinkableButtonPageDTO dto);

	public PageResult<LinkableButtonPageDTO> findLinkableButtonPageOfPage(LinkableButtonPageDTO dto,Pagination page);

	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(LinkableButtonPageDTO dto);

	public List<LinkableButtonPageDTO> findCmsPageByLinkableId(Long linkableId);
}
	