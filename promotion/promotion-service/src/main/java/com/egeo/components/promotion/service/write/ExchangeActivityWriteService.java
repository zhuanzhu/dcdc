package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


public interface ExchangeActivityWriteService {

	public Long insertExchangeActivityWithTx(ExchangeActivityDTO dto);

	public int updateExchangeActivityWithTx(ExchangeActivityDTO dto);

	public int deleteExchangeActivityWithTx(ExchangeActivityDTO dto);

    int insertOrUpdateExchangeActivityWithTx(ExchangeActivityDTO exchangeActivityDTO);
}
	