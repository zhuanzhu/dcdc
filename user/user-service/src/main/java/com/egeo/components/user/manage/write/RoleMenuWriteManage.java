package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.RoleMenuPO;

public interface RoleMenuWriteManage {

	String saveWithTx(RoleMenuPO po);
	
	int delete(RoleMenuPO po) ;

}
	