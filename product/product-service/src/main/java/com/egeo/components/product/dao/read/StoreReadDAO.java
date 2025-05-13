package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StoreCondition;
import com.egeo.components.product.po.StorePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StoreReadDAO extends BaseReadDAO<StorePO>{

	int countRootStoreOfPage(@Param("po")StorePO po);

	List<StoreCondition> findRootStoreOfPage(@Param("po")StorePO po, @Param("page")Pagination page);

	List<StoreCondition> findRootStoreAll(@Param("po")StorePO po);

	List<StoreCondition> findStoreAllByTreeId(@Param("storeTreeId")Long storeTreeId);

	StoreCondition findStoreById(@Param("po")StorePO storepo);
	/**
	 * 根据门店id查询总店信息
	 * @param storeId
	 * @return
	 */
	StorePO findHeadStoreByStoreId(@Param("storeId")Long storeId);

	StoreCondition findStoreByNodeId(@Param("nodeId")Long nodeId);

    List<Long> findStoreByName(@Param("storeName")String storeName);

    List<StorePO> findStoreByPlatformIdAndStoreMenu(@Param("po") StorePO storePO);
}
	