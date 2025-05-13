package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.CmsTemplateElementDTO;


public interface CmsTemplateElementWriteService {

	public Long insertCmsTemplateElementWithTx(CmsTemplateElementDTO dto);

	public int updateCmsTemplateElementWithTx(CmsTemplateElementDTO dto);

	public int deleteCmsTemplateElementWithTx(CmsTemplateElementDTO dto);

	public void insertBatchWithTx(List<CmsTemplateElementDTO> cmsTemplateElementDTOs);
}
	