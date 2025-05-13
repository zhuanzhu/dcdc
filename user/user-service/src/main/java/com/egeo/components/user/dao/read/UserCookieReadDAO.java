package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserCookiePO;
import com.egeo.orm.BaseReadDAO;

public interface UserCookieReadDAO extends BaseReadDAO<UserCookiePO>{
	/**
	 * 根据用户id集合查询用户cookie信息
	 * @param userIds
	 * @return
	 */
	List<UserCookiePO> findByUserIds(
			@Param("ids")List<Long> userIds,
			@Param("clientId")Long clientId,
			@Param("platformId")Long platformId);
}
	