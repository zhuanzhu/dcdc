package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;


public interface MerchantProdSalesRecordWriteService {

	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	public int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	public int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	public int addSalesVolumeByIdWithTx(Long merchantProdSalesRecordId, Long salesVolume);
}
	