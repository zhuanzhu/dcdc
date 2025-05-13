package com.egeo.components.user.service.read;


import java.util.List;

import com.egeo.components.user.dto.UserPraiseCountRankDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserWelfareReadService {

	public UserWelfareDTO findUserWelfareById(UserWelfareDTO dto);

	public PageResult<UserWelfareDTO> findUserWelfareOfPage(UserWelfareDTO dto,Pagination page);

	public List<UserWelfareDTO> findUserWelfareAll(UserWelfareDTO dto);

	/**
	 * 根据用户id批量查询uw
	 * @param userIdList
	 * @return
	 */
	public List<UserWelfareDTO> queryUserWelfaresByUserIds(List<Long> userIdList);

	/**
	 * 查询用户被点赞次数排名分页列表
	 * @param companyId
	 * @param page
	 * @return
	 */
	public PageResult<UserPraiseCountRankDTO> queryPraiseCountRankPage(Long companyId,Integer type, Pagination page);

	/**
	 * 查询单个用户的点赞数和排名
	 * @param userId
	 * @param type
	 * @return
	 */
	public UserPraiseCountRankDTO queryPraiseCountRankByUserId(Long userId, Integer type,Long companyId);

	/**
	 * 根据userId查询userWelfare对象
	 * @param userId
	 * @return
	 */
	public UserWelfareDTO queryUserWelfareByUserId(Long userId);
}
	