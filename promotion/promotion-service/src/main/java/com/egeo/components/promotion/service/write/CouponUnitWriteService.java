package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponUnitDTO;

import java.util.Date;
import java.util.List;


public interface CouponUnitWriteService {

	public Long insertCouponUnitWithTx(CouponUnitDTO dto);

	public int updateCouponUnitWithTx(CouponUnitDTO dto);

	public int deleteCouponUnitWithTx(CouponUnitDTO dto);

	/**
	 * 支付成功时,通过orderId,变更优惠卷unit状态
	 * @param id
	 */
	public int updateCouponByPaySuccessWithTx(Long orderId);

	/**
	 * 取消订单时,优惠卷变更
	 * @param id
	 */
	public int updateCouponByCancelOrderWithTx(Long orderId);

	/**
	 * 确认订单时,优惠卷变更
	 * @param orderId
	 * @return
	 */
	public int updateCouponUnitByAffirmOrderWithTx(Long orderId);

    Integer insertCouponUnitListWithTx(List<CouponUnitDTO> list);

    void updateCouponUnitByParamWithTx(CouponUnitDTO couponUnitDTO);

	void deleteCouponUnitByParamWithTx(CouponUnitDTO unitDTO);

	int updateCouponUnitStatusByParamsWithTx(Long couponBatchId, Long startNum, Long endNum, Date date);

	int updateCouponUnitLockedByCouponUnitId(Long couponUnitId);

    int updateCouponUnitRemoveLock(String oldUnitCode);
    
    void updateCouponUnitReadStatus(List<Long> ids);
}
	