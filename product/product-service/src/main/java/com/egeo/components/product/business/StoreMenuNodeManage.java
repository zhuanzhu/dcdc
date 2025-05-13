package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuNodeManage {

	public Map<String, Object> findStoreMenuNodeById(StoreMenuNodeDTO dto);	

	public PageResult<StoreMenuNodeDTO> findStoreMenuNodeOfPage(StoreMenuNodeDTO dto,Pagination page);

	public List<Map<String, Object>> findStoreMenuNodeAll(StoreMenuNodeDTO dto);

	Long insertStoreMenuNodeWithTx(StoreMenuNodeDTO dto);

	int updateStoreMenuNodeWithTx(StoreMenuNodeDTO dto);

	int deleteStoreMenuNodeWithTx(StoreMenuNodeDTO dto);
	/**
	 * 根据门店查询门店菜单
	 * @param storeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	public Map<String, Object> findByStoreIdOfPage(Long storeId, Long platformId, Pagination page);
}
	