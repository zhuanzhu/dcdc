package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.FunctionTreeNodeUrlPO;

import java.util.List;


public interface FunctionTreeNodeUrlWriteManage {

	Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po);

	int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po);

	int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlPO po);

}
	