package com.egeo.components.user.dao.write;

import java.util.List;

import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.BaseWriteDAO;

public interface UrlWriteDAO extends BaseWriteDAO<UrlPO> {

	UrlPO findById(UrlPO po);

    List<UrlPO> getUrlByRoleId(String roleId);

}
	