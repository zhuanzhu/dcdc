package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsPageCompanyDTO;


public interface CmsPageCompanyWriteService {

	public Long insertCmsPageCompanyWithTx(CmsPageCompanyDTO dto);

	public int updateCmsPageCompanyWithTx(CmsPageCompanyDTO dto);

	public int deleteCmsPageCompanyWithTx(CmsPageCompanyDTO dto);
}
	