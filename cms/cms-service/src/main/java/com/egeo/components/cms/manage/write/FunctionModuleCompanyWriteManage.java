package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.FunctionModuleCompanyPO;


public interface FunctionModuleCompanyWriteManage {

	Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyPO po);

	int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyPO po);

	int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyPO po);
}
	