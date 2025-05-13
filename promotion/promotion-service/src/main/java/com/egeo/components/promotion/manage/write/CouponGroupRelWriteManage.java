package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponGroupRelPO;


public interface CouponGroupRelWriteManage {

	Long insertCouponGroupRelWithTx(CouponGroupRelPO po);

	int updateCouponGroupRelWithTx(CouponGroupRelPO po);

	int deleteCouponGroupRelWithTx(CouponGroupRelPO po);
}
	