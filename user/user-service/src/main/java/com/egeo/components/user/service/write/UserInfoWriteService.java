package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserInfoDTO;


public interface UserInfoWriteService {

	public Long insertUserInfoWithTx(UserInfoDTO dto);

	public int updateUserInfoWithTx(UserInfoDTO dto);

	public int deleteUserInfoWithTx(UserInfoDTO dto);
	/**
	 * 根据用户消息id更新用户消息是否已读状态
	 * @param userInfoId
	 * @return
	 */
	public int updateIsReadByIdWithTx(Long userInfoId);
	/**
	 * 根据用户id和类型修改其消息已读
	 * @param userId
	 * @param type
	 * @param platformId
	 * @return
	 */
	public int updateByUserIdType(Long userId, Long type, Long platformId);
}
	