package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponStoreReadManage {

	public CouponStorePO findCouponStoreById(CouponStorePO po);

	public PageResult<CouponStorePO> findCouponStoreOfPage(CouponStorePO po,Pagination page);

	public List<CouponStorePO> findCouponStoreAll(CouponStorePO po);

    Long findCouponStoreCountByCouponIdAndStoreId(CouponStorePO couponStorePO);
}
	