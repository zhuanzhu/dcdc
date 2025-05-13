package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.BuyCardBaseDTO;


public interface BuyCardBaseWriteService {

	public Long insertBuyCardBaseWithTx(BuyCardBaseDTO dto);

	public int updateBuyCardBaseWithTx(BuyCardBaseDTO dto);

	public int deleteBuyCardBaseWithTx(BuyCardBaseDTO dto);
}
