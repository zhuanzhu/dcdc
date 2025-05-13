package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.FunctionModulePO;


public interface FunctionModuleWriteManage {

	Long insertFunctionModuleWithTx(FunctionModulePO po);

	int updateFunctionModuleWithTx(FunctionModulePO po);

	int deleteFunctionModuleWithTx(FunctionModulePO po);
}
	