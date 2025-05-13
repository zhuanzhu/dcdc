package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.vo.UserRoleVO;
import com.egeo.components.user.dto.UserRoleDTO;

public interface UserRoleManage {

    String save(UserRoleVO userRoleVO);

    String deleteWithTx(UserRoleVO userRoleVO);
    
    List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto);

}
