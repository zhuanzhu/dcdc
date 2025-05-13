package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.BuyCardItemDTO;


public interface BuyCardItemWriteService {

	public Long insertBuyCardItemWithTx(BuyCardItemDTO dto);

	public int updateBuyCardItemWithTx(BuyCardItemDTO dto);

	public int deleteBuyCardItemWithTx(BuyCardItemDTO dto);
}

