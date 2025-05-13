package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.RolePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface RoleReadManage {

    List<RolePO> findRoleListByUserId(Long id);

    RolePO findRoleByRoleName(String name);

    RolePO findRoleByRoleNamePlatformId(String name,Long platformId);

    List<RolePO> findAll(RolePO dto);

    List<RolePO> roleByPlatformId(Long platformId);

    List<RolePO> roleListByUserId(Long id);

    PageResult<RolePO> findPageUser(Pagination page, RolePO po);

    RolePO findById(RolePO po);
}
