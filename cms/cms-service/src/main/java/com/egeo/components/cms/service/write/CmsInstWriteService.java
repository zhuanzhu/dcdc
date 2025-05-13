package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.CmsInstDTO;


public interface CmsInstWriteService {

	public Long insertCmsInstWithTx(CmsInstDTO dto);

	public int updateCmsInstWithTx(CmsInstDTO dto);

	public int deleteCmsInstWithTx(CmsInstDTO dto);
	
	public Long insertCommonCmsInstWithTx(CmsInstDTO dto, List<Long> companyIdList, String configJson);
	
}
	