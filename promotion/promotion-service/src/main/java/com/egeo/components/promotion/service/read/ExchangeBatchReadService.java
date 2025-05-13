package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ExchangeBatchReadService {

	public ExchangeBatchDTO findExchangeBatchById(ExchangeBatchDTO dto);

	public PageResult<ExchangeBatchDTO> findExchangeBatchOfPage(ExchangeBatchDTO dto,Pagination page);

	public List<ExchangeBatchDTO> findExchangeBatchAll(ExchangeBatchDTO dto);

    List<Long> checkIsShowExchange(Long batchId, Integer unitStatus);

    List<Long> findExchangeIdsByBatch(ExchangeBatchDTO dto);

    List<Long> findExchangeActivityByOldCouponUnitId(Long batchId, Integer couponUnitStatus);
}
	