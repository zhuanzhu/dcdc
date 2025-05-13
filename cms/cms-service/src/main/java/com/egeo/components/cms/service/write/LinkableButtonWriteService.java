package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.LinkableButtonDTO;


public interface LinkableButtonWriteService {

	public Long insertLinkableButtonWithTx(LinkableButtonDTO dto);

	public int updateLinkableButtonWithTx(LinkableButtonDTO dto);

	public int deleteLinkableButtonWithTx(LinkableButtonDTO dto);
}
	