package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;


public interface CardThirdpartyAttValueWriteService {

	public Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);

	public int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);

	public int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);
}
	