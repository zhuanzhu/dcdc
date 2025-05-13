package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponGroupRelReadService {

	public CouponGroupRelDTO findCouponGroupRelById(CouponGroupRelDTO dto);

	public PageResult<CouponGroupRelDTO> findCouponGroupRelOfPage(CouponGroupRelDTO dto,Pagination page);

	public List<CouponGroupRelDTO> findCouponGroupRelAll(CouponGroupRelDTO dto);

    List<Long> findCouponIdListByGroupId(Long couponRelId);
}
	