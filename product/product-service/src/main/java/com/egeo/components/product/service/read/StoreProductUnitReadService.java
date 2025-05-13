package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreProductUnitReadService {

	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto);

	public PageResult<StoreProductUnitDTO> findStoreProductUnitOfPage(StoreProductUnitDTO dto,Pagination page);

	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto);
	/**
	 * 根据门店id查询门店puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public List<Long> findStorePuIdsByStoreId(Long storeId, Long platformId);
}
	