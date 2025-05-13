package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;


public interface StoreStockChangeApplyPictureWriteService {

	public Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);

	public int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);

	public int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto);
}
	