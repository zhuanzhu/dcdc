package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsLocalParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsLocalParamReadManage {

	public CmsLocalParamPO findCmsLocalParamById(CmsLocalParamPO po);

	public PageResult<CmsLocalParamPO> findCmsLocalParamOfPage(CmsLocalParamPO po,Pagination page);

	public List<CmsLocalParamPO> findCmsLocalParamAll(CmsLocalParamPO po);
}
	