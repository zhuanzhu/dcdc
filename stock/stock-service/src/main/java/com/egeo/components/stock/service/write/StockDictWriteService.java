package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.StockDictDTO;


public interface StockDictWriteService {

	public Long insertStockDictWithTx(StockDictDTO dto);

	public int updateStockDictWithTx(StockDictDTO dto);

	public int deleteStockDictWithTx(StockDictDTO dto);
}
	