package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;


public interface CmsPageTabCompanyWriteService {

	public Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto);

	public int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto);

	public int deleteCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto);
}
	