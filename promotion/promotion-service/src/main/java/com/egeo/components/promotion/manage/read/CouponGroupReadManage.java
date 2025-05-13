package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponGroupReadManage {

	public CouponGroupPO findCouponGroupById(CouponGroupPO po);

	public PageResult<CouponGroupPO> findCouponGroupOfPage(CouponGroupPO po,Pagination page);

	public List<CouponGroupPO> findCouponGroupAll(CouponGroupPO po);

	/**
	 * 查询优惠卷列表(模糊查询)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponGroupPO> findCouponGroupOfPageByBlurry(CouponGroupPO po, Pagination page);
}
	