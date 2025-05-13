package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreTreeManage {

	public StoreTreeDTO findStoreTreeById(StoreTreeDTO dto);	

	public PageResult<StoreTreeDTO> findStoreTreeOfPage(StoreTreeDTO dto,Pagination page);

	public List<StoreTreeDTO> findStoreTreeAll(StoreTreeDTO dto);

	Long insertStoreTreeWithTx(StoreTreeDTO dto);

	int updateStoreTreeWithTx(StoreTreeDTO dto);

	int deleteStoreTreeWithTx(StoreTreeDTO dto);
}
	