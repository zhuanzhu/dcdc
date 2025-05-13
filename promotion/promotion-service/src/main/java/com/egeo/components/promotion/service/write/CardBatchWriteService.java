package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CardBatchDTO;


public interface CardBatchWriteService {

	public Long insertCardBatchWithTx(CardBatchDTO dto);

	public int updateCardBatchWithTx(CardBatchDTO dto);

	public int deleteCardBatchWithTx(CardBatchDTO dto);
}
	