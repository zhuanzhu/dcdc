package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;


public interface StoreMenuNodeStandardUnitWriteService {

	public Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);

	public int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);

	public int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto);
	/**
	 * 批量插入门店与su商品关系
	 * @param standardUnitIds
	 * @param platformId
	 * @return
	 */
	public int insertAllWithTx(Long storeMenuNodeId,List<Long> standardUnitIds, Long platformId);
}
	