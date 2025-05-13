package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.CodeModulePO;


public interface CodeModuleWriteManage {

	Long insertCodeModuleWithTx(CodeModulePO po);

	int updateCodeModuleWithTx(CodeModulePO po);

	int deleteCodeModuleWithTx(CodeModulePO po);
}
	