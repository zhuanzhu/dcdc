package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsPageCfgDTO;


public interface CmsPageCfgWriteService {

	public Long insertCmsPageCfgWithTx(CmsPageCfgDTO dto);

	public int updateCmsPageCfgWithTx(CmsPageCfgDTO dto);

	public int deleteCmsPageCfgWithTx(CmsPageCfgDTO dto);
}
	