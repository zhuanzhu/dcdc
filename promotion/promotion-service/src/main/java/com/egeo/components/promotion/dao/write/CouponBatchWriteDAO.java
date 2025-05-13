package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.orm.BaseWriteDAO;

public interface CouponBatchWriteDAO extends BaseWriteDAO<CouponBatchPO> {

	/**
	 * 隐藏超过已领取日期的优惠卷
	 * @return
	 */
	int hideCouponBatch();
}
	