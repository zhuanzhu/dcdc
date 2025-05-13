package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponGroupPO;

import java.util.List;


public interface CouponGroupWriteManage {

	Long insertCouponGroupWithTx(List<Long> storeList, CouponGroupPO po);

	int updateCouponGroupWithTx(List<Long> storeList, CouponGroupPO po);

	int deleteCouponGroupWithTx(CouponGroupPO po);
}
	