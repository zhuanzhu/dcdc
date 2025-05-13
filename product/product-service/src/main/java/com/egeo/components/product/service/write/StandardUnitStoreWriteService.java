package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitStoreDTO;

import java.util.List;


public interface StandardUnitStoreWriteService {

	public Long insertStandardUnitStoreWithTx(StandardUnitStoreDTO dto);

	public int updateStandardUnitStoreWithTx(StandardUnitStoreDTO dto);

	public int deleteStandardUnitStoreWithTx(StandardUnitStoreDTO dto);

    void saveStandardUnitStore(List<Long> suIdList);
}
	