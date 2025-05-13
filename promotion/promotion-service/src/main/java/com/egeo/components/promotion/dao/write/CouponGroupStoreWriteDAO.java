package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface CouponGroupStoreWriteDAO extends BaseWriteDAO<CouponGroupStorePO> {

    int deleteCouponGroupStore(@Param("spuId")Long spuId, @Param("cardType")Integer cardType);

}
	