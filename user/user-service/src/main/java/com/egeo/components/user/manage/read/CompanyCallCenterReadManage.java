package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.CompanyCallCenterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyCallCenterReadManage {

	public CompanyCallCenterPO findCompanyCallCenterById(CompanyCallCenterPO po);

	public PageResult<CompanyCallCenterPO> findCompanyCallCenterOfPage(CompanyCallCenterPO po,Pagination page);

	public List<CompanyCallCenterPO> findCompanyCallCenterAll(CompanyCallCenterPO po);
}
	