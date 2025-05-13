package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponGroupStoreReadService {

	public CouponGroupStoreDTO findCouponGroupStoreById(CouponGroupStoreDTO dto);

	public PageResult<CouponGroupStoreDTO> findCouponGroupStoreOfPage(CouponGroupStoreDTO dto,Pagination page);

	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto);
}
	