package com.egeo.components.user.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.orm.BaseReadDAO;

public interface UserPlatformReadDAO extends BaseReadDAO<UserPlatformPO> {

    UserPlatformPO userIsExitPlatform(@Param("po") UserPlatformPO po);
}
