package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CmsTemplatePO;


public interface CmsTemplateWriteManage {

	Long insertCmsTemplateWithTx(CmsTemplatePO po);

	int updateCmsTemplateWithTx(CmsTemplatePO po);

	int deleteCmsTemplateWithTx(CmsTemplatePO po);
}
	