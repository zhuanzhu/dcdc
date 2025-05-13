package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserPlatformWriteDAO extends BaseWriteDAO<UserPlatformPO> {

    int deleteByUserIdAndPid(UserPlatformPO po);

    int delUserPlatform(@Param("po") UserPlatformPO platformPO);
    /**
     * 根据角色id集合和用户id删除用户平台信息
     * @param roleIds
     * @return
     */
	int delByRoleIds(@Param("ids")List<Long> roleIds,@Param("userId")Long userId);

    int updateAvailableByUserId(@Param("isAvailable") Integer isAvailable,@Param("userId") Long userId,@Param("platformId") Long platformId);
}
	