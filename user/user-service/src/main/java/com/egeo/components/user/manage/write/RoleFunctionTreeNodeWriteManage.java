package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.RoleFunctionTreeNodePO;


public interface RoleFunctionTreeNodeWriteManage {

	Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po);

	int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po);

	int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodePO po);
}
	