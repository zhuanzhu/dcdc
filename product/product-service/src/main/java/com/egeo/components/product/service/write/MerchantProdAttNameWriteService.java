package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProdAttNameDTO;


public interface MerchantProdAttNameWriteService {

	public Long insertMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);

	public int updateMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);

	public int deleteMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);
}
	