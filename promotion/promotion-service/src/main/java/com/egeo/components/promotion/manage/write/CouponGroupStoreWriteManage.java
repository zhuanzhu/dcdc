package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponGroupStorePO;


public interface CouponGroupStoreWriteManage {

	Long insertCouponGroupStoreWithTx(CouponGroupStorePO po);

	int updateCouponGroupStoreWithTx(CouponGroupStorePO po);

	int deleteCouponGroupStoreWithTx(CouponGroupStorePO po);
}
	