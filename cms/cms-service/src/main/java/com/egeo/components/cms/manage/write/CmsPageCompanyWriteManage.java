package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsPageCompanyPO;


public interface CmsPageCompanyWriteManage {

	Long insertCmsPageCompanyWithTx(CmsPageCompanyPO po);

	int updateCmsPageCompanyWithTx(CmsPageCompanyPO po);

	int deleteCmsPageCompanyWithTx(CmsPageCompanyPO po);
}
	