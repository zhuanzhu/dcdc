package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.CmsPageTabCondition;
import com.egeo.components.cms.po.CmsPageTabPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageTabReadManage {

	public CmsPageTabPO findCmsPageTabById(CmsPageTabPO po);

	public PageResult<CmsPageTabPO> findCmsPageTabOfPage(CmsPageTabPO po,Pagination page);

	public List<CmsPageTabPO> findCmsPageTabAll(CmsPageTabPO po);

	public List<CmsPageTabCondition> findCmsPageTabCondition(CmsPageTabCondition condition);

	List<CmsPageTabCondition> findDefaultCmsPageTabFront(Long platformId);
}
	