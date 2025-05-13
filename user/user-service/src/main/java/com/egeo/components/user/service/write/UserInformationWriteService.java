package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserInformationDTO;


public interface UserInformationWriteService {

	public Long insertUserInformationWithTx(UserInformationDTO dto);

	public int updateUserInformationWithTx(UserInformationDTO dto);

	public int deleteUserInformationWithTx(UserInformationDTO dto);
}
	