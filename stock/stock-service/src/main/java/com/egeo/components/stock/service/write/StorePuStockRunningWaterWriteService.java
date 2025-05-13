package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;


public interface StorePuStockRunningWaterWriteService {

	public Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);

	public int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);

	public int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);
}
	