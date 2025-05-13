package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CouponCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponCompanyReadManage {

	public CouponCompanyPO findCouponCompanyById(CouponCompanyPO po);

	public PageResult<CouponCompanyPO> findCouponCompanyOfPage(CouponCompanyPO po,Pagination page);

	public List<CouponCompanyPO> findCouponCompanyAll(CouponCompanyPO po);

	/**
	 * 通过优惠卷id或优惠卷分组的id查询公司信息
	 * @param couponId
	 * @param couponGroupId
	 * @return
	 */
	public List<Long> findCompanyByCouponIdOrCouponGroupId(Long couponId, Long couponGroupId, List<Long> officialList,
			List<Long> competitiveList, List<Long> testList);
}
	