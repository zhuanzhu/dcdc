package com.egeo.components.stock.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.orm.BaseReadDAO;

public interface StorePuWarehouseStockReadDAO extends BaseReadDAO<StorePuWarehouseStockPO>{
	/**
	 * 根据门店puid查询门店pu库存信息
	 * @param storePuId
	 * @return
	 */
	StorePuWarehouseStockPO findByStorePuId(@Param("storePuId")Long storePuId);
	/**
	 * 根据门店id获取缺货商品规格数量(门店PU分身的在线库存数量为0的PU数量)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findByStorePuSellOutSum(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据puId集合查询pu库存为0的数量
	 * @param storePuIds
	 * @return
	 */
	Integer findPuSellOutSumStoreByPuIds(@Param("ids")List<Long> storePuIds);
	
	/**
	 * 查询门店商品真实库存
	 * @param storeProductUnitId
	 * @return
	 */
	Long realStockNumByStoreProductUnitId(@Param("storeProductUnitId")Long storeProductUnitId, @Param("storeId")Long storeId);
}
	