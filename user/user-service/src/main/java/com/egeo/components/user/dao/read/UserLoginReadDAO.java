package com.egeo.components.user.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UserLoginCondition;
import com.egeo.components.user.po.UserLoginPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UserLoginReadDAO extends BaseReadDAO<UserLoginPO>{

	List<UserLoginPO> findOfPageByUserId(@Param("userId")Long id);

	List<UserLoginPO> findByUserIds(@Param("userIds") List<Long> userIds, @Param("startTime") Date start,@Param("endTime") Date end);

	int countConditionOfPage(@Param("po") UserLoginCondition po);

	List<UserLoginPO> findConditionOfPage(@Param("po") UserLoginCondition po, @Param("page") Pagination page);
}
	