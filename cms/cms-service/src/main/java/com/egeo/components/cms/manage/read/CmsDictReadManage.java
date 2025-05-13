package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsDictReadManage {

	public CmsDictPO findCmsDictById(CmsDictPO po);

	public PageResult<CmsDictPO> findCmsDictOfPage(CmsDictPO po,Pagination page);

	public List<CmsDictPO> findCmsDictAll(CmsDictPO po);
}
	