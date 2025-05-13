package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExchangeActivityReadDAO extends BaseReadDAO<ExchangeActivityPO>{
    int countOfFuzzQueryPage(@Param("po") ExchangeActivityPO po) ;

    List<ExchangeActivityPO> fuzzQueryExchangeActivity(@Param("po") ExchangeActivityPO po,@Param("page") Pagination page) ;

    List<ExchangeActivityPO> findExchangeActivityByIds(@Param("idList")List<Long> exchangeList);
}
	
