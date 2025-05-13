package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreProductUnitReadManage {

	public StoreProductUnitPO findStoreProductUnitById(StoreProductUnitPO po);

	public PageResult<StoreProductUnitPO> findStoreProductUnitOfPage(StoreProductUnitPO po,Pagination page);

	public List<StoreProductUnitPO> findStoreProductUnitAll(StoreProductUnitPO po);
	/**
	 * 根据门店id查询门店puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public List<Long> findStorePuIdsByStoreId(Long storeId, Long platformId);
}
	