package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponPO;


public interface CouponWriteManage {

	Long insertCouponWithTx(CouponPO po);

	int updateCouponWithTx(CouponPO po);

	int deleteCouponWithTx(CouponPO po);
}
	