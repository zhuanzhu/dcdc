package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserQuitPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserQuitWriteDAO extends BaseWriteDAO<UserQuitPO> {
	/**
	 * 根据用户id集合删除用户离职信息表
	 * @param userIdInvalidSet
	 * @return
	 */
	int delByUserIdsWithTx(@Param("ids")List<Long> userIdInvalidSet);
}
	