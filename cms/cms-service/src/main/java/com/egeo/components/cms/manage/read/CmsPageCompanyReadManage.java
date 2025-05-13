package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsPageCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsPageCompanyReadManage {

	public CmsPageCompanyPO findCmsPageCompanyById(CmsPageCompanyPO po);

	public PageResult<CmsPageCompanyPO> findCmsPageCompanyOfPage(CmsPageCompanyPO po,Pagination page);

	public List<CmsPageCompanyPO> findCmsPageCompanyAll(CmsPageCompanyPO po);
}
	