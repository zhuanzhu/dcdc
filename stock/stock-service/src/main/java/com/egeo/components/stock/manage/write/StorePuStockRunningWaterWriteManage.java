package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.StorePuStockRunningWaterPO;


public interface StorePuStockRunningWaterWriteManage {

	Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po);

	int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po);

	int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po);
}
	