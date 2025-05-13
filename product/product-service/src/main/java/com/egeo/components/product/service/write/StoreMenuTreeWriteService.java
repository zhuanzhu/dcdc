package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StoreMenuTreeDTO;


public interface StoreMenuTreeWriteService {

	public Long insertStoreMenuTreeWithTx(StoreMenuTreeDTO dto);

	public int updateStoreMenuTreeWithTx(StoreMenuTreeDTO dto);

	public int deleteStoreMenuTreeWithTx(StoreMenuTreeDTO dto);
}
	