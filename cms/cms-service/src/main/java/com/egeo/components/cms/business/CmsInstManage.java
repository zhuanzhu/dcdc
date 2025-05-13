package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.CmsInstDTO;

public interface CmsInstManage {

	public Long insertCommonCmsInstWithTx(CmsInstDTO dto, List<Long> companyIdList, String configJson);

	public Map<String, Object> findCommonInstConfig(Long instId);
	
}
	