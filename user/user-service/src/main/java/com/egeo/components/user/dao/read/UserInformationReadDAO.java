package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UserInformationCondition;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UserInformationReadDAO extends BaseReadDAO<UserInformationPO>{
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	List<UserInformationCondition> findUserInformationOfByUserIdPage(@Param("po")UserInformationPO po, @Param("page")Pagination page);
	/**
	 * 根据用户id查询用户消息条数
	 * @param dto
	 * @param page
	 * @return
	 */
	int countUserInformationOfByUserIdPage(@Param("po")UserInformationPO po);
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	int findUnreadByUserId(@Param("po")UserInformationPO po);
}
	