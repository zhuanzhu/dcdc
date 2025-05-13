package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsInstCfgDTO;


public interface CmsInstCfgWriteService {

	public Long insertCmsInstCfgWithTx(CmsInstCfgDTO dto);

	public int updateCmsInstCfgWithTx(CmsInstCfgDTO dto);

	public int deleteCmsInstCfgWithTx(CmsInstCfgDTO dto);
}
	