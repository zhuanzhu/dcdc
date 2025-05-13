package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.UserRoleDTO;

public interface UserRoleWriteService {

    String saveWithTx(UserRoleDTO userRoleDTO);

    String deleteWithTx(UserRoleDTO userRoleDTO);

}
	