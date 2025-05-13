package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponGroupRelReadManage {

	public CouponGroupRelPO findCouponGroupRelById(CouponGroupRelPO po);

	public PageResult<CouponGroupRelPO> findCouponGroupRelOfPage(CouponGroupRelPO po,Pagination page);

	public List<CouponGroupRelPO> findCouponGroupRelAll(CouponGroupRelPO po);

    List<Long> findCouponIdListByGroupId(Long couponGroupId);
}
	