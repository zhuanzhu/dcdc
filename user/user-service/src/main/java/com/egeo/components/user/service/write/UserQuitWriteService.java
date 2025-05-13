package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.UserQuitDTO;


public interface UserQuitWriteService {

	public Long insertUserQuitWithTx(UserQuitDTO dto);

	public int updateUserQuitWithTx(UserQuitDTO dto);

	public int deleteUserQuitWithTx(UserQuitDTO dto);
	/**
	 * 根据用户id集合删除用户离职信息表
	 * @param userIdInvalidSet
	 * @return
	 */
	public int delByUserIdsWithTx(List<Long> userIdInvalidSet);
}
	