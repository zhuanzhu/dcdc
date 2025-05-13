package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.CmsTemplateElementPO;


public interface CmsTemplateElementWriteManage {

	Long insertCmsTemplateElementWithTx(CmsTemplateElementPO po);

	int updateCmsTemplateElementWithTx(CmsTemplateElementPO po);

	int deleteCmsTemplateElementWithTx(CmsTemplateElementPO po);

	void insertBatchWithTx(List<CmsTemplateElementPO> pos);
}
	