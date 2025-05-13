package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardUnitStoreCondition;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StandardUnitStoreReadDAO extends BaseReadDAO<StandardUnitStorePO>{

	List<StandardUnitStoreCondition> findStandardUnitStoreAll(
			@Param("po")StandardUnitStorePO po);

	int countStandardUnitStoreOfPage(@Param("po")StandardUnitStorePO po);

	List<StandardUnitStoreCondition> findStandardUnitStoreOfPage(@Param("po")StandardUnitStorePO po, @Param("page")Pagination page);
	/**
	 * 根据门店id查询在售商品数量(上架商品)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer standardUnitSumByStoreId(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	List<Long> findByPuIdsByStoreId(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据商品id查询所有门店（包含id和名称）
	 * @param po
	 * @return
	 */
	List<StandardUnitStoreCondition> standardUnitStoreByStandardUnitId(@Param("po")StandardUnitStorePO po);
	
	List<StandardUnitStorePO> findByStoreAndSu(@Param("suIdList")List<Long> suIdList, @Param("storeId")Long storeId, @Param("platformId")Long platformId);

    int findStandardUnitStoreCount(@Param("suId")Long suId, @Param("storeId")Long storeId);
}
	