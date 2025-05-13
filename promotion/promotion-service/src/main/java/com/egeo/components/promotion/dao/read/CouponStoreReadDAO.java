package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface CouponStoreReadDAO extends BaseReadDAO<CouponStorePO>{
    Long findCouponStoreCountByCouponIdAndStoreId(@Param("couponStorePO") CouponStorePO couponStorePO);
}
	