package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserInfoPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserInfoWriteDAO extends BaseWriteDAO<UserInfoPO> {
	/**
	 * 根据id集合修改其消息已读
	 * @param userInfoIds
	 * @return
	 */
	int updateByUserInfoIds(@Param("ids")List<Long> userInfoIds);
}
	