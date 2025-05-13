package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.FunctionModulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleReadManage {

	public FunctionModulePO findFunctionModuleById(FunctionModulePO po);

	public PageResult<FunctionModulePO> findFunctionModuleOfPage(FunctionModulePO po,Pagination page);

	public List<FunctionModulePO> findFunctionModuleAll(FunctionModulePO po);
}
	