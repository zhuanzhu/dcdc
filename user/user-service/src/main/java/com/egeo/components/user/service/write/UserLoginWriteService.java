package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserLoginDTO;

public interface UserLoginWriteService {

	void insertLoginLogWithTx(UserLoginDTO userLogin);

}
	