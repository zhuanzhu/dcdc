package com.egeo.components.user.dao.write;

import java.util.List;

import com.egeo.components.user.bean.UniAuthUserRole;


public interface UniauthUserRoleMapper {
    int insertBatch(List<UniAuthUserRole> list);

    int deleteByProperty(UniAuthUserRole uniAuthUserRole);

    int findByCount(UniAuthUserRole uniAuthUserRole);

    int insertBatchAccountRole(List<UniAuthUserRole> list);

    int deleteByPropertyAccountRole(UniAuthUserRole uniAuthUserRole);
}
