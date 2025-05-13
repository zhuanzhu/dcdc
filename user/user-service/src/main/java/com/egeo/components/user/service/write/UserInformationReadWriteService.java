package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserInformationReadDTO;


public interface UserInformationReadWriteService {

	public Long insertUserInformationReadWithTx(UserInformationReadDTO dto);

	public int updateUserInformationReadWithTx(UserInformationReadDTO dto);

	public int deleteUserInformationReadWithTx(UserInformationReadDTO dto);
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	public int deleteByUserInformationIdWithTx(Long userInformationId);
}
	