package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExchangeBatchReadDAO extends BaseReadDAO<ExchangeBatchPO>{
    List<Long> checkIsShowExchange(@Param("time") Date time,@Param("batchId") Long batchId, @Param("unitStatus")Integer unitStatus);

    List<Long> findExchangeIdsByBatch(@Param("po")ExchangeBatchPO po);

    List<Long> findExchangeActivityByOldCouponUnitId(@Param("batchId")Long batchId, @Param("couponUnitStatus")Integer couponUnitStatus);
}
	