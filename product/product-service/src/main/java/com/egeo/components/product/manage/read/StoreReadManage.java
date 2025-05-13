package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.StoreCondition;
import com.egeo.components.product.po.StorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreReadManage {

	public StoreCondition findStoreById(StorePO po);

	public PageResult<StorePO> findStoreOfPage(StorePO po,Pagination page);

	public List<StorePO> findStoreAll(StorePO po);

	public List<StoreCondition> findRootStoreAll(StorePO po);

	public PageResult<StoreCondition> findRootStoreOfPage(StorePO po, Pagination page);

	public List<StoreCondition> findStoreAllByTreeId(Long storeTreeId);
	/**
	 * 根据门店id查询总店信息
	 * @param storeId
	 * @return
	 */
	public StorePO findHeadStoreByStoreId(Long storeId);

	public StoreCondition findStoreByNodeId(Long nodeId);

    List<Long> findStoreByName(String storeName);

	public List<StorePO> findStoreByPlatformIdAndStoreMenu(StorePO storePO);
}
	