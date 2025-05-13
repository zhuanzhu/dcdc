package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponBatchCompanyPO;


public interface CouponBatchCompanyWriteManage {

	Long insertCouponBatchCompanyWithTx(CouponBatchCompanyPO po);

	int updateCouponBatchCompanyWithTx(CouponBatchCompanyPO po);

	int deleteCouponBatchCompanyWithTx(CouponBatchCompanyPO po);
}
	