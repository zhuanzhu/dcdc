package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreMenuNodeStandardUnitReadService {

	public StoreMenuNodeStandardUnitDTO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitDTO dto);

	public PageResult<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitDTO dto,Pagination page);

	public List<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitDTO dto);
}
	