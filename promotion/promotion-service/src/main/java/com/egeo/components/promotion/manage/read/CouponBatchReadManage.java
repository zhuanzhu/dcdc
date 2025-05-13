package com.egeo.components.promotion.manage.read;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.condition.CouponBatchCondition;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponBatchReadManage {

	public CouponBatchPO findCouponBatchById(CouponBatchPO po);

	public PageResult<CouponBatchPO> findCouponBatchOfPage(CouponBatchPO po,Pagination page);

	public List<CouponBatchPO> findCouponBatchAll(CouponBatchPO po);

	/**
	 * 查询优惠卷批次列表
	 * @param po
	 * @param title
	 * @param page
	 * @return
	 */
	public PageResult<CouponBatchCondition> findCouponBatchOfPageByBlurry(CouponBatchPO po, String title,
			Pagination page);


	public List<CouponBatchPO> findCouponBatchByCouponIds(CouponBatchPO po, List<Long> couponIdList, Map<String, Object> key);
	
	/**
	 * 根据优惠券ID列表查询注册领取的优惠券批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
	public List<CouponBatchCondition> findRegisterCouponBatchByCouponId(List<Long> couponIdList, Long platformId);
	
	/**
	 * 根据优惠券组ID列表查询注册领取的优惠券批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
	public List<CouponBatchCondition> findRegisterCouponBatchByCouponGroup(List<Long> couponIdList, Long platformId);

	Long findCouponBatchCount(CouponBatchPO couponBatchPO);

	PageResult<CouponBatchCondition> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page);

	List<CouponBatchCondition> findCouponBatchByExchange(Long exchangeId);
}
	