package com.egeo.components.user.service.write;

public interface RoleUrlWriteService {

    int upDateWithTx(String urls, Long roleId);

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
	