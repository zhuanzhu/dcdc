package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExchangeBatchReadManage {

	public ExchangeBatchPO findExchangeBatchById(ExchangeBatchPO po);

	public PageResult<ExchangeBatchPO> findExchangeBatchOfPage(ExchangeBatchPO po,Pagination page);

	public List<ExchangeBatchPO> findExchangeBatchAll(ExchangeBatchPO po);

    List<Long> checkIsShowExchange(Long batchId, Integer unitStatus);

    List<Long> findExchangeIdsByBatch(ExchangeBatchPO exchangeBatchPO);

    List<Long> findExchangeActivityByOldCouponUnitId(Long batchId, Integer couponUnitStatus);
}
	