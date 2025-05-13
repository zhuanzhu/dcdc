package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.FunctionTreeNodePO;


public interface FunctionTreeNodeWriteManage {

	Long insertFunctionTreeNodeWithTx(FunctionTreeNodePO po);

	int updateFunctionTreeNodeWithTx(FunctionTreeNodePO po);

	int deleteFunctionTreeNodeWithTx(FunctionTreeNodePO po);
}
	