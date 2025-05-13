package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.LocalParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LocalParamReadManage {

	public LocalParamPO findLocalParamById(LocalParamPO po);

	public PageResult<LocalParamPO> findLocalParamOfPage(LocalParamPO po,Pagination page);

	public List<LocalParamPO> findLocalParamAll(LocalParamPO po);
}
	