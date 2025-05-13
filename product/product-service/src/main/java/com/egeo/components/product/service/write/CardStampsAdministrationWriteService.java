package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CardStampsAdministrationDTO;


public interface CardStampsAdministrationWriteService {

	public Long insertCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);

	public int updateCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);

	public int deleteCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto);
}
	