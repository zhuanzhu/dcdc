package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.RoleUrlPO;

public interface RoleUrlReadManage {

	List<String> getUrlListByUserId(Long userId, Long platformId);
	
	List<RoleUrlPO> findAll(RoleUrlPO po);

}
	