package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponCompanyPO;


public interface CouponCompanyWriteManage {

	Long insertCouponCompanyWithTx(CouponCompanyPO po);

	int updateCouponCompanyWithTx(CouponCompanyPO po);

	int deleteCouponCompanyWithTx(CouponCompanyPO po);
}
	