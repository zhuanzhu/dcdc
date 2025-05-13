package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.orm.BaseReadDAO;

public interface StoreProductUnitReadDAO extends BaseReadDAO<StoreProductUnitPO>{
	/**
	 * 根据门店id查询门店puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	List<Long> findStorePuIdsByStoreId(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
}
	