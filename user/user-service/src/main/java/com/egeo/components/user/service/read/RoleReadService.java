package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.PlatformRole;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


public interface RoleReadService {

    List<RoleDTO> findRoleListByUserId(Long id);

    RoleDTO findRoleByRoleName(String name);

    RoleDTO findRoleByRoleNamePlatformId(String namem,Long platformId);

    List<RoleDTO> findAll(RoleDTO dto);

    List<PlatformRole> roleByPlatformIdWithTx(String platformId,Long id);
    
    List<RoleDTO> roleListByUserId(Long id);

    PageResult<RoleDTO> roleListPage(Pagination page, RoleDTO dto);

    RoleDTO findById(RoleDTO roleDTO);

}
