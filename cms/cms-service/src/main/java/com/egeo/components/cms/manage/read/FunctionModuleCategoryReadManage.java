package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.FunctionModuleCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleCategoryReadManage {

	public FunctionModuleCategoryPO findFunctionModuleCategoryById(FunctionModuleCategoryPO po);

	public PageResult<FunctionModuleCategoryPO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryPO po,Pagination page);

	public List<FunctionModuleCategoryPO> findFunctionModuleCategoryAll(FunctionModuleCategoryPO po);
}
	