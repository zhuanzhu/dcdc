package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UserPlatformPO;

public interface UserPlatformWriteManage {

    String save(UserPlatformPO po);

    String update(UserPlatformPO po);

    String deleteByUserIdAndPid(UserPlatformPO po);

    int delUserPlatform(UserPlatformPO platformPO);
}
	