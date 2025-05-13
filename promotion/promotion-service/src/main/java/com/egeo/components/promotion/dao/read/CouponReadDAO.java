package com.egeo.components.promotion.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.CouponPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CouponReadDAO extends BaseReadDAO<CouponPO>{

	/**
	 * 模糊查询优惠卷列表
	 * @param po
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")CouponPO po,@Param("couponIdList") List<Long> couponIdList);

	List<CouponPO> findOfPageByBlurry(@Param("po")CouponPO po,@Param("couponIdList") List<Long> couponIdList,  @Param("page")Pagination page);
	/**
	 * 根据优惠卷组id查询优惠卷信息
	 * @param couponRelId
	 * @return
	 */
	List<CouponPO> findCouponByCouponGroupId(@Param("couponRelId")Long couponRelId);
}
	