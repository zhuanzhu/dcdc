package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponGroupRelReadDAO extends BaseReadDAO<CouponGroupRelPO>{
    List<Long> findCouponIdListByGroupId(@Param("couponGroupId")Long couponGroupId);
}
	