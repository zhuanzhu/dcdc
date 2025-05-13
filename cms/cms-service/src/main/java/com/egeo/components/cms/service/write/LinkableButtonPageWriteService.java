package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.LinkableButtonPageDTO;


public interface LinkableButtonPageWriteService {

	public Long insertLinkableButtonPageWithTx(LinkableButtonPageDTO dto);

	public int updateLinkableButtonPageWithTx(LinkableButtonPageDTO dto);

	public int deleteLinkableButtonPageWithTx(LinkableButtonPageDTO dto);
	
	public int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPageDTO> dtos);
	
	public int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPageDTO dto);
	
}
	