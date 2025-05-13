package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UserInformationCondition;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserInformationReadManage {

	public UserInformationPO findUserInformationById(UserInformationPO po);

	public PageResult<UserInformationPO> findUserInformationOfPage(UserInformationPO po,Pagination page);

	public List<UserInformationPO> findUserInformationAll(UserInformationPO po);
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<UserInformationCondition> findUserInformationOfByUserIdPage(UserInformationPO po, Pagination page);
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public int findUnreadByUserId(UserInformationPO po);
}
	