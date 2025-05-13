package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.StoreProductUnitDTO;


public interface StoreProductUnitWriteService {

	public Long insertStoreProductUnitWithTx(StoreProductUnitDTO dto);

	public int updateStoreProductUnitWithTx(StoreProductUnitDTO dto);

	public int deleteStoreProductUnitWithTx(StoreProductUnitDTO dto);
	
	public int insertAllWithTx(List<StoreProductUnitDTO> list);
}
	