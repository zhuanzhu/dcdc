package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.po.CmsPageCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageCfgReadManage {

	public CmsPageCfgPO findCmsPageCfgById(CmsPageCfgPO po);

	public PageResult<CmsPageCfgPO> findCmsPageCfgOfPage(CmsPageCfgPO po,Pagination page);

	public List<CmsPageCfgPO> findCmsPageCfgAll(CmsPageCfgPO po);
	
	List<CmsPageCfgCondition> findPageCfgByPageId(Long pageId);
}
	