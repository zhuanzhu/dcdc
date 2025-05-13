package com.egeo.components.promotion.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.condition.CouponUnitCondition;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CouponUnitReadDAO extends BaseReadDAO<CouponUnitPO> {

	/**
	 * 优惠卷unit列表
	 * 
	 * @param po
	 * @param userList
	 * @return
	 */
	int countOfPageByBlurry(@Param("po") CouponUnitCondition po, @Param("userList") List<Long> userList);

	List<CouponUnitCondition> findOfPageByBlurry(@Param("po") CouponUnitCondition po,
			@Param("userList") List<Long> userList, @Param("page") Pagination page);

	/**
	 * 优惠卷unit列表(客户端)
	 * 
	 * @param po
	 * @return
	 */
	int countOfPageByUser(@Param("po") CouponUnitCondition po);

	List<CouponUnitCondition> findOfPageByUser(@Param("po") CouponUnitCondition po, @Param("page") Pagination page);

	/**
	 * 客户端领卷中心
	 * 
	 * @param userId
	 * @return
	 */
	int countCouponUnitCenterOfPage(@Param("po") CouponUnitCondition po, @Param("companyIdByType") Long companyIdByType);

	List<CouponUnitCondition> findCouponUnitCenterOfPage(@Param("po") CouponUnitCondition po,@Param("companyIdByType") Long companyIdByType,
			@Param("page") Pagination page);

	/**
	 * 根据用户和su的id查询优惠卷列表
	 * 
	 * @param po
	 * @return
	 */
	List<CouponUnitCondition> findSUCouponBatchOfPage(@Param("po") CouponUnitCondition po,
			@Param("goodIdList")List<Long> goodIdList, @Param("companyIdByType") Long companyIdByType, @Param("page") Pagination page);

	/**
	 * 查询用户所有可用满减卷
	 * @param couponUnitDTO
	 * @return
	 */
	List<CouponUnitCondition> findCouponUnitOfAllByUser(@Param("po") CouponUnitCondition po);

    List<CouponUnitCondition> findCouponUnitListByBatchIdList(@Param("couponBatchList")List<Long> couponBatchList);

    Long findCouponUnitAllCount(@Param("po")CouponUnitPO couponUnitPO);

	int countCouponUnitExchangeOfPage(@Param("po")CouponUnitCondition po, @Param("companyIdByType")Long companyIdByType);

	List<CouponUnitCondition> findCouponUnitAndBatchExchange(@Param("po")CouponUnitCondition po, @Param("companyIdByType")Long companyIdByType);

    Long findCouponUnitCountOfFreezeByParams(@Param("couponBatchId")Long couponBatchId, @Param("startNum")Long startNum,@Param("endNum") Long endNum);

    Integer countUnreadCouponUnit(@Param("userId")Long userId, @Param("couponType")Integer couponType);
    
}
