package com.egeo.components.promotion.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponBatchReadService {

	public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto);

	public PageResult<CouponBatchDTO> findCouponBatchOfPage(CouponBatchDTO dto,Pagination page);

	public List<CouponBatchDTO> findCouponBatchAll(CouponBatchDTO dto);

	/**
	 * 查询优惠卷批次列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponBatchDTO> findCouponBatchOfPageByBlurry(CouponBatchDTO dto, Pagination page);

	public List<CouponBatchDTO> findCouponBatchByCouponIds(CouponBatchDTO dto, List<Long> couponIdList, Map<String, Object> key);
	
	/**
	 * 根据优惠券ID列表查询注册领取的优惠券批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponId(List<Long> couponIdList, Long platformId);
	
	/**
	 * 根据优惠券组ID列表查询注册领取的优惠券批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponGroup(List<Long> couponIdList, Long platformId);

    Long findCouponBatchCount(CouponBatchDTO couponBatchDTO);

	PageResult<CouponBatchDTO> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page);

    List<CouponBatchDTO> findCouponBatchByExchange(Long exchangeId);

    List<CouponBatchDTO> findCouponBatchAllByBatchCode(String newBatchCode);
}
	