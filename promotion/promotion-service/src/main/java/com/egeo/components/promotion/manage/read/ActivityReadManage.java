package com.egeo.components.promotion.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.po.ActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityReadManage {

	PageResult<ActivityPO> findPage(Pagination page, ActivityPO po);

	com.egeo.components.promotion.po.ActivityPO findById(ActivityPO po);

	List<ActivityPO> findAll(ActivityPO po);

	boolean activityByMerchantProdIdAndDate(Date date, Long merchantProdId);
}
	