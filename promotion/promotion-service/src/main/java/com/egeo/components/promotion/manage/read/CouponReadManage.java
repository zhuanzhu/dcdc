package com.egeo.components.promotion.manage.read;

import java.util.List;

import com.egeo.components.promotion.po.CouponPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponReadManage {

	public CouponPO findCouponById(CouponPO po);

	public PageResult<CouponPO> findCouponOfPage(CouponPO po,Pagination page);

	public List<CouponPO> findCouponAll(CouponPO po);

	/**
	 * 模糊查询优惠卷列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<CouponPO> findCouponOfPageByBlurry(CouponPO po, List<Long> couponIdList, Pagination page);
	/**
	 * 根据优惠卷组id查询优惠卷信息
	 * @param couponRelId
	 * @return
	 */
	public List<CouponPO> findCouponByCouponGroupId(Long couponRelId);
}
	