package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.UserPlatformPO;

public interface UserPlatformReadManage {

    boolean userIsExitPlatform(UserPlatformPO po);

    UserPlatformPO findById(UserPlatformPO po);

    List<UserPlatformPO> findAll(UserPlatformPO po);
    /**
     * 根据平台id查询其下员工数量
     * @param platformId
     * @return
     */
	Integer findUserCountByPlatformId(Long platformId);
}
