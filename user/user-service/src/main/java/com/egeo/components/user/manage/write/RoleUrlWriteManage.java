package com.egeo.components.user.manage.write;

import java.util.List;

public interface RoleUrlWriteManage {

    int delUrl(StringBuffer delUrl, Long roleId);

    int setUrl(List<String> delUrl, Long roleId);

	String delRoleUrlByUrlId(Long urlId);
	/**
	 * 根据类型和角色id添加角色和URL的关系
	 * @param roleId
	 * @param type
	 * @param platformId
	 * @return
	 */
	Integer saveUrlByTypeWithTx(Long roleId, Integer type, Long platformId);
}
	