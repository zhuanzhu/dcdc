package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.FunctionModuleCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleCompanyReadManage {

	public FunctionModuleCompanyPO findFunctionModuleCompanyById(FunctionModuleCompanyPO po);

	public PageResult<FunctionModuleCompanyPO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyPO po,Pagination page);

	public List<FunctionModuleCompanyPO> findFunctionModuleCompanyAll(FunctionModuleCompanyPO po);
}
	