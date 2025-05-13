package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StoreDTO;


public interface StoreWriteService {

	public Long insertStoreWithTx(StoreDTO dto);

	public int updateStoreWithTx(StoreDTO dto);

	public int deleteStoreWithTx(StoreDTO dto);
}
	