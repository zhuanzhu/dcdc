package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExchangeCouponUnitStatusWriteDAO extends BaseWriteDAO<ExchangeCouponUnitStatusPO> {
    void insertStatusList(@Param("poList")List<ExchangeCouponUnitStatusPO> poList);
}
	