package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.ActivityPO;

public interface ActivityWriteManage {

	Long saveActivity(ActivityPO po);

	Integer deleteWithTx(ActivityPO po);

	Long updateActivity(ActivityPO po);
}
	