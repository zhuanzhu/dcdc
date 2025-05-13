package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.PlatformPO;

public interface PlatformWriteManage {

    int save(PlatformPO po);

    int upDate(PlatformPO po);

    Integer deleteById(PlatformPO po);
}
	