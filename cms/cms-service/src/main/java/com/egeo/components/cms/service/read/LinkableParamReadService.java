package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LinkableParamReadService {

	public LinkableParamDTO findLinkableParamById(LinkableParamDTO dto);

	public PageResult<LinkableParamDTO> findLinkableParamOfPage(LinkableParamDTO dto,Pagination page);

	public List<LinkableParamDTO> findLinkableParamAll(LinkableParamDTO dto);
}
	