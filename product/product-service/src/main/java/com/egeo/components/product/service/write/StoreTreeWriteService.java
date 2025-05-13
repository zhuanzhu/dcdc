package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StoreTreeDTO;


public interface StoreTreeWriteService {

	public Long insertStoreTreeWithTx(StoreTreeDTO dto);

	public int updateStoreTreeWithTx(StoreTreeDTO dto);

	public int deleteStoreTreeWithTx(StoreTreeDTO dto);
}
	