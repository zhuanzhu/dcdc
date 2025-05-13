package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.CmsTemplateCfgPO;


public interface CmsTemplateCfgWriteManage {

	Long insertCmsTemplateCfgWithTx(CmsTemplateCfgPO po);

	int updateCmsTemplateCfgWithTx(CmsTemplateCfgPO po);

	int deleteCmsTemplateCfgWithTx(CmsTemplateCfgPO po);

	int insertBatchWithTx(List<CmsTemplateCfgPO> pos);
}
	