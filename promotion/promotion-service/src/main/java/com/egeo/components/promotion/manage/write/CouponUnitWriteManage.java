package com.egeo.components.promotion.manage.write;

import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.po.CouponUnitPO;


public interface CouponUnitWriteManage {

	Long insertCouponUnitWithTx(CouponUnitPO po);

	int updateCouponUnitWithTx(CouponUnitPO po);

	int deleteCouponUnitWithTx(CouponUnitPO po);

	/**
	 * 支付成功时,优惠卷变更
	 * @param orderId
	 * @return
	 */
	int updateCouponByPaySuccessWithTx(Long orderId);
	
	/**
	 * 取消订单时,优惠卷变更
	 * @param orderId
	 * @return
	 */
	int updateCouponByCancelOrderWithTx(Long orderId);

	/**
	 * 确认订单时,优惠卷变更
	 * @param orderId
	 * @return
	 */
	int updateCouponUnitByAffirmOrderWithTx(Long orderId);

    Integer insertCouponUnitListWithTx(List<CouponUnitPO> list);

    void updateCouponUnitByParamWithTx(CouponUnitPO couponUnitPO);

	void deleteCouponUnitByParam(CouponUnitPO po);

    int updateCouponUnitStatusByParamsWithTx(Long couponBatchId, Long startNum, Long endNum, Date date);

    int updateCouponUnitLockedByCouponUnitId(Long couponUnitId);

    int updateCouponUnitRemoveLock(String oldUnitCode);
    
    void updateCouponUnitReadStatus(List<Long> ids);
}
	