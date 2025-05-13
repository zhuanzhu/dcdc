package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.RoleDTO;

public interface RoleWriteService {

    Long saveRoleWithTx(RoleDTO roleDTO);

    int removeRoleWithTx(String name);

    int updateRoleWithTx(RoleDTO dto);

    int upDateUserWithTx(RoleDTO roleDTO,String userName);
    
    int upDateRoleWithTx(String urls, Long roleid);

    int deleteByIdWithTx(RoleDTO roleDto);

    void updateRoleStatus(RoleDTO roleDto, Integer useable);
}
