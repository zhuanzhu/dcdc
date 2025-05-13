package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponStoreReadService {

	public CouponStoreDTO findCouponStoreById(CouponStoreDTO dto);

	public PageResult<CouponStoreDTO> findCouponStoreOfPage(CouponStoreDTO dto, Pagination page);

	public List<CouponStoreDTO> findCouponStoreAll(CouponStoreDTO dto);
}
	