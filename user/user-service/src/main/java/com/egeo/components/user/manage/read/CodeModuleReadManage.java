package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.CodeModulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CodeModuleReadManage {

	public CodeModulePO findCodeModuleById(CodeModulePO po);

	public PageResult<CodeModulePO> findCodeModuleOfPage(CodeModulePO po,Pagination page);

	public List<CodeModulePO> findCodeModuleAll(CodeModulePO po);
}
	