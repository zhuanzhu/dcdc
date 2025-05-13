package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.StoreAdminDTO;


public interface StoreAdminWriteService {

	public Long insertStoreAdminWithTx(StoreAdminDTO dto);

	public int updateStoreAdminWithTx(StoreAdminDTO dto);

	public int deleteStoreAdminWithTx(StoreAdminDTO dto);
}
	