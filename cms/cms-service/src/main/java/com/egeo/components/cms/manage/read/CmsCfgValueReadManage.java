package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CmsCfgValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsCfgValueReadManage {

	public CmsCfgValuePO findCmsCfgValueById(CmsCfgValuePO po);

	public PageResult<CmsCfgValuePO> findCmsCfgValueOfPage(CmsCfgValuePO po,Pagination page);

	public List<CmsCfgValuePO> findCmsCfgValueAll(CmsCfgValuePO po);
}
	