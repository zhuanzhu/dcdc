package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreTreeNodeReadService {

	public StoreTreeNodeDTO findStoreTreeNodeById(StoreTreeNodeDTO dto);

	public PageResult<StoreTreeNodeDTO> findStoreTreeNodeOfPage(StoreTreeNodeDTO dto,Pagination page);

	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto);
	/**
	 * 根据门店id查询是否是总店
	 * @param storeId
	 * @return
	 */
	public boolean findHeadStoreByStoreId(Long storeId);
}
	