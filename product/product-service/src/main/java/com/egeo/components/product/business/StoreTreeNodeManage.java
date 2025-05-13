package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreTreeNodeManage {

	public StoreTreeNodeDTO findStoreTreeNodeById(StoreTreeNodeDTO dto);	

	public PageResult<StoreTreeNodeDTO> findStoreTreeNodeOfPage(StoreTreeNodeDTO dto,Pagination page);

	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto);

	Long insertStoreTreeNodeWithTx(StoreTreeNodeDTO dto,StoreDTO storedto);

	int updateStoreTreeNodeWithTx(StoreTreeNodeDTO dto);

	int deleteStoreTreeNodeWithTx(StoreTreeNodeDTO dto);
}
	