package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponGroupReadService {

	public CouponGroupDTO findCouponGroupById(CouponGroupDTO dto);

	public PageResult<CouponGroupDTO> findCouponGroupOfPage(CouponGroupDTO dto,Pagination page);

	public List<CouponGroupDTO> findCouponGroupAll(CouponGroupDTO dto);

	/**
	 * 查询优惠卷列表(模糊查询)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponGroupDTO> findCouponGroupOfPageByBlurry(CouponGroupDTO dto, Pagination page);
}
	