package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuNodeStandardUnitManage {

	public StoreMenuNodeStandardUnitDTO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitDTO dto);	

	public PageResult<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitDTO dto,Pagination page);

	public List<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitDTO dto);

	Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);

	int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);

	int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);
	/**
	 * 批量插入门店与su商品关系
	 * @param standardUnitIds
	 * @param platformId
	 * @return
	 */
	public int insertAllWithTx(Long storeMenuNodeId,List<Long> standardUnitIds, Long platformId);
}
	