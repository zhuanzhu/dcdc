package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StoreMenuNodeDTO;


public interface StoreMenuNodeWriteService {

	public Long insertStoreMenuNodeWithTx(StoreMenuNodeDTO dto);

	public int updateStoreMenuNodeWithTx(StoreMenuNodeDTO dto);

	public int deleteStoreMenuNodeWithTx(StoreMenuNodeDTO dto);
}
	