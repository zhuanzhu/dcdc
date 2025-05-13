package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;


public interface SellPlatformMerchantProdWriteService {

	public Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);

	public int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);

	public int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);
}
	