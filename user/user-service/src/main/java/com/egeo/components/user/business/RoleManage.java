package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.vo.RoleVO;
import com.egeo.components.user.dto.PlatformRole;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface RoleManage {

    List<RoleDTO> findRoleListByUserId(Long id);

    /**
     * 新增角色
     * 
     * @param role
     * @return
     */

    Long saveRoleWithTx(RoleDTO role);

    /**
     * 删除角色
     * 
     * @param id
     * @return
     */
    int removeRoleWithTx(String name);

    /**
     * 根据Id查
     * 
     * @param id
     * @return
     */

    RoleDTO findRoleByRoleName(String name);

    RoleDTO findRoleByRoleNamePlatformId(String name,Long platformId);

    List<RoleDTO> findAll(RoleDTO dto);

    int upDateUserWithTx(RoleDTO roleDTO,String userName);

    List<PlatformRole> roleByPlatformId(String platformId,Long id);

    int upDateRoleWithTx(String menus, Long roleId);

    PageResult<RoleDTO> roleListPage(Pagination page, RoleVO roleVO);

    RoleDTO findById(RoleDTO roleDTO);

    int deleteByIdWithTx(RoleDTO roleDto);
    
    int updateRoleWithTx(RoleDTO role);

    void updateRoleStatus(RoleDTO roleDto, Integer useable);
}
