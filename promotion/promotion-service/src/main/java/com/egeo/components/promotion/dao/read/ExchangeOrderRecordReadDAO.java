package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExchangeOrderRecordReadDAO extends BaseReadDAO<ExchangeOrderRecordPO>{
    /**
     * 根据以旧唤醒活动ID
     * @param exchangeId
     * @return
     */
    int getCountOfCompletedOrderByExchangeId(@Param("exchangeId") Long exchangeId) ;

    int countOfFuzzQueryPage(@Param("po") ExchangeOrderRecordPO po);

    List<ExchangeOrderRecordPO> fuzzQueryExchangeOrderRecord (@Param("po") ExchangeOrderRecordPO po,
                                                              @Param("startTime") String startTime,
                                                              @Param("endTime") String endTime,
                                                              @Param("page") Pagination page);

    List<ExchangeOrderRecordPO> findAllLived(@Param("po")ExchangeOrderRecordPO po , @Param("page") Pagination page);
}
	
