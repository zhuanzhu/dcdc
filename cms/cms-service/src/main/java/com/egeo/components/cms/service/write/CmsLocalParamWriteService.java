package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsLocalParamDTO;


public interface CmsLocalParamWriteService {

	public Long insertCmsLocalParamWithTx(CmsLocalParamDTO dto);

	public int updateCmsLocalParamWithTx(CmsLocalParamDTO dto);

	public int deleteCmsLocalParamWithTx(CmsLocalParamDTO dto);
}
	