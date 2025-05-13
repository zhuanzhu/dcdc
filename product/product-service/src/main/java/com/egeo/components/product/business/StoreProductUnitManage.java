package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreProductUnitManage {

	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto);	

	public PageResult<Map<String, Object>> findStoreProductUnitOfPage(StoreProductUnitDTO dto,Pagination page);

	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto);

	Long insertStoreProductUnitWithTx(StoreProductUnitDTO dto);

	int updateStoreProductUnitWithTx(StoreProductUnitDTO dto);

	int deleteStoreProductUnitWithTx(StoreProductUnitDTO dto);
}
	