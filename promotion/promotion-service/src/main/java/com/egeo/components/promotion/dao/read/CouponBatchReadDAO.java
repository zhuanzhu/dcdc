package com.egeo.components.promotion.dao.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.condition.CouponBatchCondition;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CouponBatchReadDAO extends BaseReadDAO<CouponBatchPO>{

	/**
	 * 查询优惠卷批次列表
	 * @param po
	 * @param title
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")CouponBatchPO po, @Param("title")String title);

	List<CouponBatchCondition> findOfPageByBlurry(@Param("po")CouponBatchPO po, @Param("title")String title, @Param("page")Pagination page);

    List<CouponBatchPO> findCouponBatchByCouponIds(@Param("po")CouponBatchPO po, @Param("couponIdList")List<Long> couponIdList, @Param("key")Map<String, Object> key);
    
    List<CouponBatchCondition> findRegisterCouponBatchByCouponId(@Param("couponIdList")List<Long> couponIdList, @Param("platformId")Long platformId);
    
    List<CouponBatchCondition> findRegisterCouponBatchByCouponGroup(@Param("couponIdList")List<Long> couponIdList, @Param("platformId")Long platformId);

    Long findCouponBatchCount(@Param("po")CouponBatchPO couponBatchPO);

   int findCouponBatchCountByParam(@Param("batchIdList")List<Long> batchIdList, @Param("title") String title, @Param("couponBatchCode") String couponBatchCode,@Param("couponBatchName") String couponBatchName, @Param("type") Integer type, @Param("platformId") Long platformId);

	List<CouponBatchCondition> findCouponBatchByParam(@Param("batchIdList")List<Long> batchIdList, @Param("title") String title, @Param("couponBatchCode") String couponBatchCode, @Param("couponBatchName") String couponBatchName,@Param("type") Integer type, @Param("platformId") Long platformId, @Param("page") Pagination page);

    List<CouponBatchCondition> findCouponBatchByExchange(@Param("exchangeId")Long exchangeId);

	public List<CouponBatchPO> findAll(@Param("po") CouponBatchPO po, @Param("page") Pagination page ,@Param("couponIdList") List<Long> couponIdList);
   
}
	