package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProdSalesRecordPO;


public interface MerchantProdSalesRecordWriteManage {

	Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po);

	int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po);

	int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po);
	/**
	 * 根据id增加销售数量
	 * @param merchantProdSalesRecordId
	 * @param salesVolume
	 * @return
	 */
	int addSalesVolumeByIdWithTx(Long merchantProdSalesRecordId, Long salesVolume);
}
	