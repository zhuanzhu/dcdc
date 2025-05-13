package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;


public interface StoreTreeNodeWriteService {

	public Long insertStoreTreeNodeWithTx(StoreTreeNodeDTO storeTreeNodedto,StoreDTO storedto);

	public int updateStoreTreeNodeWithTx(StoreTreeNodeDTO dto);

	public int deleteStoreTreeNodeWithTx(StoreTreeNodeDTO dto);
}
	