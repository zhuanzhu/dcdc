package com.egeo.components.user.dao.write;

import com.egeo.components.user.po.RolePO;
import com.egeo.components.user.po.RoleUrl;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface RoleWriteDAO extends BaseWriteDAO<RolePO> {

    int delUrl(String delUrl, String roleId);

    int delMenu(RoleUrl roleUrl);

    void updateRoleStatus(@Param("po") RolePO po, @Param("useable") Integer useable);
}
	