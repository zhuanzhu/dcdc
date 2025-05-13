package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponBatchDTO;


public interface CouponBatchWriteService {

	public Long insertCouponBatchWithTx(CouponBatchDTO dto);

	public int updateCouponBatchWithTx(CouponBatchDTO dto);

	public int deleteCouponBatchWithTx(CouponBatchDTO dto);

	/**
	 * 将优惠卷批次设置为失效
	 * @param dto
	 * @return
	 */
	public int invalidCouponBatchWithTx(CouponBatchDTO dto);

	/**
	 * 隐藏超过已领取日期的优惠卷
	 */
	public int hideCouponBatch();

    Long insertCouponBatchAndUnitWithTx(CouponBatchDTO dto);
}
	