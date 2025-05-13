package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsInstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsInstReadManage {

	public CmsInstPO findCmsInstById(CmsInstPO po);

	public PageResult<CmsInstPO> findCmsInstOfPage(CmsInstPO po,Pagination page);

	public List<CmsInstPO> findCmsInstAll(CmsInstPO po);
	
	public PageResult<CmsInstPO> findByPageAndCompanyOfPage(CmsInstPO po, Long companyId, Long companyAllId, Pagination page);
}
	