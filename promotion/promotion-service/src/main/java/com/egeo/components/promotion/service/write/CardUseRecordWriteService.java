package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CardUseRecordDTO;

import java.math.BigDecimal;


public interface CardUseRecordWriteService {

	public Long insertCardUseRecordWithTx(CardUseRecordDTO dto);

	public int updateCardUseRecordWithTx(CardUseRecordDTO dto);

	public int deleteCardUseRecordWithTx(CardUseRecordDTO dto);

	public Boolean cancelUserCard();

}
