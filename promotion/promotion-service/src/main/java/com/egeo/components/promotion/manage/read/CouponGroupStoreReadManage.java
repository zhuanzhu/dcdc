package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponGroupStoreReadManage {

	public CouponGroupStorePO findCouponGroupStoreById(CouponGroupStorePO po);

	public PageResult<CouponGroupStorePO> findCouponGroupStoreOfPage(CouponGroupStorePO po,Pagination page);

	public List<CouponGroupStorePO> findCouponGroupStoreAll(CouponGroupStorePO po);
}
	