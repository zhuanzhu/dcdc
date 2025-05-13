package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LinkableButtonReadService {

	public LinkableButtonDTO findLinkableButtonById(Long id);

	public PageResult<LinkableButtonDTO> findLinkableButtonOfPage(LinkableButtonDTO dto,Pagination page);

	public List<LinkableButtonDTO> findLinkableButtonAll(LinkableButtonDTO dto);
}
	