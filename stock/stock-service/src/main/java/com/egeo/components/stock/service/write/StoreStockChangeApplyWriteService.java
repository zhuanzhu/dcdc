package com.egeo.components.stock.service.write;

import java.util.List;

import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;


public interface StoreStockChangeApplyWriteService {

	public Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto,List<String> pictures);

	public int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto);

	public int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto);
}
	