package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreMenuTreeReadService {

	public StoreMenuTreeDTO findStoreMenuTreeById(StoreMenuTreeDTO dto);

	public PageResult<StoreMenuTreeDTO> findStoreMenuTreeOfPage(StoreMenuTreeDTO dto,Pagination page);

	public List<StoreMenuTreeDTO> findStoreMenuTreeAll(StoreMenuTreeDTO dto);
}
	