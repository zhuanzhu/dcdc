package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.PlatformPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PlatformReadManage {

    List<PlatformPO> findAll(PlatformPO po);

    PlatformPO findRoleByRoleName(String name);

    List<PlatformPO> PlatformByUserId(Long useId);

    PlatformPO find(Long pid);

    List<PlatformPO> PlatformByUid(Long userId);

    PlatformPO findById(PlatformPO po);

    PageResult<PlatformPO> findPage(Pagination page, PlatformPO po);
    
    List<PlatformPO> findAllPlatform();
}
	