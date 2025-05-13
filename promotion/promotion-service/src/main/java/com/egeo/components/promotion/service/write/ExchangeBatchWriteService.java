package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ExchangeBatchDTO;


public interface ExchangeBatchWriteService {

	public Long insertExchangeBatchWithTx(ExchangeBatchDTO dto);

	public int updateExchangeBatchWithTx(ExchangeBatchDTO dto);

	public int deleteExchangeBatchWithTx(ExchangeBatchDTO dto);
}
	