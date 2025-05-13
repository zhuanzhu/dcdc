package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UserLoginCondition;
import com.egeo.components.user.po.UserLoginPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserLoginReadManage {

	PageResult<UserLoginPO> findOfPage(UserLoginCondition condition, Pagination page);

	/**
	 * 根据用户Id集合 查询用户登陆记录
	 * @param userIds
	 * @param endTime 
	 * @param startTime 
	 * @return
	 */
	List<UserLoginPO> findByUserIds(List<Long> userIds, Long startTime, Long endTime);
	
}
	