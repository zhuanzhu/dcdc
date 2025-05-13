package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.UserPraiseCountRankPO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserWelfareReadManage {

	public UserWelfarePO findUserWelfareById(UserWelfarePO po);

	public PageResult<UserWelfarePO> findUserWelfareOfPage(UserWelfarePO po,Pagination page);

	public List<UserWelfarePO> findUserWelfareAll(UserWelfarePO po);

	/**
	 * 批量查询uw
	 * @param userIdList
	 * @return
	 */
	public List<UserWelfarePO> queryUserWelfaresByUserIds(List<Long> userIdList);

	/**
	 * 查询用户被点赞次数排名分页列表
	 * @param companyId
	 * @param type
	 * @param page
	 * @return
	 */
	public PageResult<UserPraiseCountRankPO> queryPraiseCountRankPage(Long companyId, Integer type, Pagination page);

	/**
	 * 查询单个用户的点赞数和排名
	 * @param userId
	 * @param type
	 * @return
	 */
	public UserPraiseCountRankPO queryPraiseCountRankByUserId(Long userId, Integer type,Long companyId);

	/**
	 * 根据用户id查询userWelfare对象
	 * @param userId
	 * @return
	 */
	public UserWelfarePO queryUserWelfareByUserId(Long userId);
}
	