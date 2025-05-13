package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;


public interface SellPlatformStandardUnitRecordWriteManage {

	Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po);

	int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po);

	int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po);
}
	