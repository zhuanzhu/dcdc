package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ExternalLinkDTO;


public interface ExternalLinkWriteService {

	public Long insertExternalLinkWithTx(ExternalLinkDTO dto);

	public int updateExternalLinkWithTx(ExternalLinkDTO dto);

	public int deleteExternalLinkWithTx(ExternalLinkDTO dto);
}
	