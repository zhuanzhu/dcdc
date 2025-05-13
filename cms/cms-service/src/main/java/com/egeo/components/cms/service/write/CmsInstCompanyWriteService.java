package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsInstCompanyDTO;


public interface CmsInstCompanyWriteService {

	public Long insertCmsInstCompanyWithTx(CmsInstCompanyDTO dto);

	public int updateCmsInstCompanyWithTx(CmsInstCompanyDTO dto);

	public int deleteCmsInstCompanyWithTx(CmsInstCompanyDTO dto);
}
	