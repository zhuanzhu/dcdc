package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsTemplateCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsTemplateCfgReadManage {

	public CmsTemplateCfgPO findCmsTemplateCfgById(CmsTemplateCfgPO po);

	public PageResult<CmsTemplateCfgPO> findCmsTemplateCfgOfPage(CmsTemplateCfgPO po,Pagination page);

	public List<CmsTemplateCfgPO> findCmsTemplateCfgAll(CmsTemplateCfgPO po);
}
	