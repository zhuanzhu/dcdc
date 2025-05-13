package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantDTO;


public interface MerchantWriteService {

	public Long insertMerchantWithTx(MerchantDTO dto);

	public int updateMerchantWithTx(MerchantDTO dto);

	public int deleteMerchantWithTx(MerchantDTO dto);
}
	