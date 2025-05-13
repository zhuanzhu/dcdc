package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.orm.BaseWriteDAO;

public interface UserWelfareWriteDAO extends BaseWriteDAO<UserWelfarePO> {
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	int updateUserWelfareByUserIdWithTx(UserWelfarePO po);

	/**
	 * 刷新用户日被点赞数量
	 * 将praise_day_count变0
	 * @return
	 */
	int refreshUserDayPraiseCount();

	/**
	 * 刷新用户月被点赞数量
	 * 将praise_month_count变0
	 * @return
	 */
	int refreshUserMonthPraiseCount();

	/**
	 * 增加用户当日当月被点赞数
	 * @param userId
	 * @return
	 */
	int increasePraiseCount(@Param("userId")Long userId);
	/**
	 * 根据公司id删除公司下的所有员工部门信息
	 * @param companyId
	 * @return
	 */
	int updateDepartmentIsNullByUserId(@Param("userId")Long userId);
}
	