package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponBatchPO;


public interface CouponBatchWriteManage {

	Long insertCouponBatchWithTx(CouponBatchPO po);

	int updateCouponBatchWithTx(CouponBatchPO po);

	int deleteCouponBatchWithTx(CouponBatchPO po);

	/**
	 * 隐藏超过已领取日期的优惠卷
	 * @return
	 */
	int hideCouponBatch();

}
	