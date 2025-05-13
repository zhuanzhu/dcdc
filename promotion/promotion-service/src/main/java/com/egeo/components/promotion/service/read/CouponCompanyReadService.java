package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponCompanyReadService {

	public CouponCompanyDTO findCouponCompanyById(CouponCompanyDTO dto);

	public PageResult<CouponCompanyDTO> findCouponCompanyOfPage(CouponCompanyDTO dto,Pagination page);

	public List<CouponCompanyDTO> findCouponCompanyAll(CouponCompanyDTO dto);

	/**
	 * 通过优惠卷的id或优惠卷分组的id查询公司id
	 * @param couponId
	 * @param couponGroupId
	 * @return
	 */
	public List<Long> findCompanyByCouponIdOrCouponGroupId(Long couponId, Long couponGroupId, List<Long> officialList,
			List<Long> competitiveList, List<Long> testList);
}
	