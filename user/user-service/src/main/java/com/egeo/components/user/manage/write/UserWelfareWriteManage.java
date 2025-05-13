package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserWelfarePO;


public interface UserWelfareWriteManage {

	Long insertUserWelfareWithTx(UserWelfarePO po);

	int updateUserWelfareWithTx(UserWelfarePO po);

	int deleteUserWelfareWithTx(UserWelfarePO po);
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	int updateUserWelfareByUserIdWithTx(UserWelfarePO po);

	/**
	 * 刷新用户日被赞数
	 * @return
	 */
	int refreshUserDayPraiseCountWithTx();

	/**
	 * 刷新用户月被赞数
	 * @return
	 */
	int refreshUserMonthPraiseCountWithTx();

	/**
	 * 增加用户当日当月被点赞数
	 * @param userId
	 * @return
	 */
	int increasePraiseCountWithTx(Long userId);
}
	