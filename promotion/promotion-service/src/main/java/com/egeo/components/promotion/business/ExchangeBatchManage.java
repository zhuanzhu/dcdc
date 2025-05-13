package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface ExchangeBatchManage {

	public ExchangeBatchDTO findExchangeBatchById(ExchangeBatchDTO dto);	

	public PageResult<Map<String, Object>> findExchangeBatchOfPage(ExchangeBatchDTO dto, Pagination page);

	public List<ExchangeBatchDTO> findExchangeBatchAll(ExchangeBatchDTO dto);

	Long insertExchangeBatchWithTx(ExchangeBatchDTO dto);

	int updateExchangeBatchWithTx(ExchangeBatchDTO dto);

	int deleteExchangeBatchWithTx(ExchangeBatchDTO dto);

    JsonResult checkExchangeValid(Long couponUnitId);

	JsonResult<Map<String, Object>> checkAndExchange(Long platformId, Integer type, Long id, String name, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId);

    JsonResult exchangeRightNow(Long id, String name, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId);
}
	