package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.CmsInstPO;


public interface CmsInstWriteManage {

	Long insertCmsInstWithTx(CmsInstPO po);

	int updateCmsInstWithTx(CmsInstPO po);

	int deleteCmsInstWithTx(CmsInstPO po);
	
	Long insertCommonCmsInstWithTx(CmsInstPO po, List<Long> companyIdList, String configJson);
	
}
	