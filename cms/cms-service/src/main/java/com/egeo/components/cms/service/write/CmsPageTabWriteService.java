package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsPageTabDTO;


public interface CmsPageTabWriteService {

	public Long insertCmsPageTabWithTx(CmsPageTabDTO dto);

	public int updateCmsPageTabWithTx(CmsPageTabDTO dto);

	public int deleteCmsPageTabWithTx(CmsPageTabDTO dto);
}
	