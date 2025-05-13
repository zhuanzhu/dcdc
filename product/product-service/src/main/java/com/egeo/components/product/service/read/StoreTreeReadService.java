package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreTreeReadService {

	public StoreTreeDTO findStoreTreeById(StoreTreeDTO dto);

	public PageResult<StoreTreeDTO> findStoreTreeOfPage(StoreTreeDTO dto,Pagination page);

	public List<StoreTreeDTO> findStoreTreeAll(StoreTreeDTO dto);
}
	