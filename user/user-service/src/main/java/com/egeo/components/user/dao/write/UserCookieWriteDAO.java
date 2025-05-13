package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserCookiePO;
import com.egeo.orm.BaseWriteDAO;

public interface UserCookieWriteDAO extends BaseWriteDAO<UserCookiePO> {
	/**
	 * 根据用户id集合删除用户cookie信息
	 * @param userIds
	 * @return
	 */
	int delByUsetIds(@Param("ids")List<Long> userIds);
}
	