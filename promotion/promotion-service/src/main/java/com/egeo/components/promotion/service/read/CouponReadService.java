package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponReadService {

	public CouponDTO findCouponById(CouponDTO dto);

	public PageResult<CouponDTO> findCouponOfPage(CouponDTO dto,Pagination page);

	public List<CouponDTO> findCouponAll(CouponDTO dto);

	/**
	 * 模糊查询优惠卷列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponDTO> findCouponOfPageByBlurry(CouponDTO dto, List<Long> couponIdList, Pagination page);
	/**
	 * 根据优惠卷组id查询优惠卷信息
	 * @param couponRelId
	 * @return
	 */
	public List<CouponDTO> findCouponByCouponGroupId(Long couponRelId);
}
	