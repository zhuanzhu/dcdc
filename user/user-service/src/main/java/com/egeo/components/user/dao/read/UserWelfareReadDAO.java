package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserPraiseCountRankPO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UserWelfareReadDAO extends BaseReadDAO<UserWelfarePO>{

	/**
	 * 批量查询uw
	 * @param userIdList
	 * @return
	 */
	List<UserWelfarePO> queryUserWelfaresByUserIds(@Param("ids")List<Long> userIdList);

	/**
	 * 查询用户被点赞次数排名分页列表
	 * @param companyId
	 * @param type
	 * @param page
	 * @return
	 */
	List<UserPraiseCountRankPO> queryPraiseCountRankPage(@Param("companyId")Long companyId, @Param("type")Integer type, @Param("page")Pagination page);

	/**
	 * 查询用户被点赞次数排名分页列表总记录数
	 * @param companyId
	 * @param type
	 * @return
	 */
	Integer queryPraiseCountRankTotalCount(@Param("companyId")Long companyId, @Param("type")Integer type);

	/**
	 * 查询单个用户的点赞数和排名
	 * @param userId
	 * @param type
	 * @return
	 */
	UserPraiseCountRankPO queryPraiseCountRankByUserId(@Param("userId")Long userId, @Param("type")Integer type,@Param("companyId")Long companyId);

	/**
	 * 根据id查询userWelfare对象
	 * @param userId
	 * @return
	 */
	UserWelfarePO queryUserWelfareByUserId(@Param("userId")Long userId);
	/**
	 * 根据用户id查询是否员工部门信息不为空
	 * @param userIds
	 * @return
	 */
	int findDepartmentIsNullSumByUserIds(@Param("ids")List<Long> userIds);
}
	