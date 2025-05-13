package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;


public interface StoreMenuNodeStandardUnitWriteManage {

	Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po);

	int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po);

	int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po);
	/**
	 * 批量插入门店与su商品关系
	 * @param storeMenuNodeId
	 * @param standardUnitIds
	 * @param platformId
	 * @return
	 */
	int insertAllWithTx(Long storeMenuNodeId, List<Long> standardUnitIds, Long platformId);
}
	