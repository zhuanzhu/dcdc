package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.FunctionModulePO;


public interface FunctionModuleWriteManage {

	Long insertFunctionModuleWithTx(FunctionModulePO po);

	int updateFunctionModuleWithTx(FunctionModulePO po);

	int deleteFunctionModuleWithTx(FunctionModulePO po);
}
	