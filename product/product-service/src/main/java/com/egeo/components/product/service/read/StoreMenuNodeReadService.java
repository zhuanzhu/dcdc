package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreMenuNodeReadService {

	public StoreMenuNodeDTO findStoreMenuNodeById(StoreMenuNodeDTO dto);

	public PageResult<StoreMenuNodeDTO> findStoreMenuNodeOfPage(StoreMenuNodeDTO dto,Pagination page);

	public List<StoreMenuNodeDTO> findStoreMenuNodeAll(StoreMenuNodeDTO dto);
}
	