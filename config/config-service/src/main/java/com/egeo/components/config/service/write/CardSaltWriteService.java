package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.CardSaltDTO;


public interface CardSaltWriteService {

	public Long insertCardSaltWithTx(CardSaltDTO dto);

	public int updateCardSaltWithTx(CardSaltDTO dto);

	public int deleteCardSaltWithTx(CardSaltDTO dto);
}
	