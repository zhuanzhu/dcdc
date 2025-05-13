package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserInfoReadManage {

	public UserInfoPO findUserInfoById(UserInfoPO po);

	public PageResult<UserInfoPO> findUserInfoOfPage(UserInfoPO po,Pagination page);

	public List<UserInfoPO> findUserInfoAll(UserInfoPO po);
	/**
	 * 根据消息id查询用户数量
	 * @param infoId
	 * @param platformId
	 * @return
	 */
	public int findUserSumByInfoId(Long infoId, Long platformId);
	/**
	 * 根据消息id分页查询用户信息
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<UserExtendCondition> findUserByInfoIdOfPage(UserInfoPO po, Pagination page);
	/**
	 * 根据当前用户id查询当前用户消息数量
	 * @param po
	 * @return
	 */
	public int findUserInfoSumByUserId(UserInfoPO po);
}
	