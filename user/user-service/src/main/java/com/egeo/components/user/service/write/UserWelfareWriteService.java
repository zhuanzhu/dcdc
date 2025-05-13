package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserWelfareDTO;


public interface UserWelfareWriteService {

	public Long insertUserWelfareWithTx(UserWelfareDTO dto);

	public int updateUserWelfareWithTx(UserWelfareDTO dto);

	public int deleteUserWelfareWithTx(UserWelfareDTO dto);
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	public int updateUserWelfareByUserIdWithTx(UserWelfareDTO dto);

	/**
	 * 刷新用户日点赞数
	 */
	public void refreshUserDayPraiseCount();

	/**
	 * 刷新用户月点赞数
	 */
	public void refreshUserMonthPraiseCount();

	/**
	 * 增加用户当日当月被点赞数
	 * @param userId
	 * @return
	 */
	public int increasePraiseCount(Long userId);
}
	