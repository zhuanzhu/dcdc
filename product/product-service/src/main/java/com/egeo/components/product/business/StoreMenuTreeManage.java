package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuTreeManage {

	public StoreMenuTreeDTO findStoreMenuTreeById(StoreMenuTreeDTO dto);	

	public PageResult<StoreMenuTreeDTO> findStoreMenuTreeOfPage(StoreMenuTreeDTO dto,Pagination page);

	public List<StoreMenuTreeDTO> findStoreMenuTreeAll(StoreMenuTreeDTO dto);

	Long insertStoreMenuTreeWithTx(StoreMenuTreeDTO dto);

	int updateStoreMenuTreeWithTx(StoreMenuTreeDTO dto);

	int deleteStoreMenuTreeWithTx(StoreMenuTreeDTO dto);
}
	