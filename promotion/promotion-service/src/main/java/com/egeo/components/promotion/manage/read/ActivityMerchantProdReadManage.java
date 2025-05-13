package com.egeo.components.promotion.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.condition.ActivityMerchantProdCondition;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ActivityMerchantProdReadManage {

	PageResult<ActivityMerchantProdPO> findPage(Pagination page, ActivityMerchantProdPO po);

	List<ActivityMerchantProdPO> findAll(ActivityMerchantProdPO po);
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	List<ActivityMerchantProdCondition> activityMerchantProdByPages(Date date, Integer pages,Long platformId);
}
	