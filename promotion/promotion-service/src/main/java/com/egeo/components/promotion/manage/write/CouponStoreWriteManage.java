package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponStorePO;


public interface CouponStoreWriteManage {

	Long insertCouponStoreWithTx(CouponStorePO po);

	int updateCouponStoreWithTx(CouponStorePO po);

	int deleteCouponStoreWithTx(CouponStorePO po);
}
	