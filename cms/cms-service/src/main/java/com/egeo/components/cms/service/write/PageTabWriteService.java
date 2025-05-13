package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.PageTabDTO;


public interface PageTabWriteService {

	public Long insertPageTabWithTx(PageTabDTO dto);

	public int updatePageTabWithTx(PageTabDTO dto);

	public int deletePageTabWithTx(PageTabDTO dto);
}
	