package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserRolePO;

public interface UserRoleWriteManage {

    String saveWithTx(UserRolePO po);

    String deleteWithTx(UserRolePO po);

}
	