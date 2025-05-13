package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProdCauseDTO;


public interface MerchantProdCauseWriteService {

	public Long insertMerchantProdCauseWithTx(MerchantProdCauseDTO dto);

	public int updateMerchantProdCauseWithTx(MerchantProdCauseDTO dto);

	public int deleteMerchantProdCauseWithTx(MerchantProdCauseDTO dto);
}
	