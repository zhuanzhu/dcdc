package com.egeo.components.user.dao.write;

import com.egeo.components.user.po.RoleUrl;
import com.egeo.components.user.po.RoleUrlPO;
import com.egeo.orm.BaseWriteDAO;

public interface RoleUrlWriteDAO extends BaseWriteDAO<RoleUrlPO> {

    int delUrl(RoleUrl roleUrl);
}
	