package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsPageDTO;


public interface CmsPageWriteService {

	public Long insertCmsPageWithTx(CmsPageDTO dto, String configJson);

	public int updateCmsPageWithTx(CmsPageDTO dto, String configJson);

	public int deleteCmsPageWithTx(CmsPageDTO dto);

	public int updateStatusWithTx(CmsPageDTO dto);
}
	