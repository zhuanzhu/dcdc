package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsInstCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsInstCompanyReadManage {

	public CmsInstCompanyPO findCmsInstCompanyById(CmsInstCompanyPO po);

	public PageResult<CmsInstCompanyPO> findCmsInstCompanyOfPage(CmsInstCompanyPO po,Pagination page);

	public List<CmsInstCompanyPO> findCmsInstCompanyAll(CmsInstCompanyPO po);
}
	