package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsTemplateDTO;


public interface CmsTemplateWriteService {

	public Long insertCmsTemplateWithTx(CmsTemplateDTO dto);

	public int updateCmsTemplateWithTx(CmsTemplateDTO dto);

	public int deleteCmsTemplateWithTx(CmsTemplateDTO dto);
}
	