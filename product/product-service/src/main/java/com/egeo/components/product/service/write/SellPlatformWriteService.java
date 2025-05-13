package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SellPlatformDTO;


public interface SellPlatformWriteService {

	public Long insertSellPlatformWithTx(SellPlatformDTO dto);

	public int updateSellPlatformWithTx(SellPlatformDTO dto);

	public int deleteSellPlatformWithTx(SellPlatformDTO dto);
}
	