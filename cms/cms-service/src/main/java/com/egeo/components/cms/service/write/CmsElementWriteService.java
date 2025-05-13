package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsElementDTO;


public interface CmsElementWriteService {

	public Long insertCmsElementWithTx(CmsElementDTO dto);

	public int updateCmsElementWithTx(CmsElementDTO dto);

	public int deleteCmsElementWithTx(CmsElementDTO dto);
}
	