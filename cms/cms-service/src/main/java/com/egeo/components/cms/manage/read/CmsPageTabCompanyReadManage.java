package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsPageTabCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageTabCompanyReadManage {

	public CmsPageTabCompanyPO findCmsPageTabCompanyById(CmsPageTabCompanyPO po);

	public PageResult<CmsPageTabCompanyPO> findCmsPageTabCompanyOfPage(CmsPageTabCompanyPO po,Pagination page);

	public List<CmsPageTabCompanyPO> findCmsPageTabCompanyAll(CmsPageTabCompanyPO po);
}
	