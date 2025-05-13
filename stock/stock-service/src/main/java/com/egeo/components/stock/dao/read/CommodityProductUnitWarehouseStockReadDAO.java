package com.egeo.components.stock.dao.read;

import java.util.List;

import com.egeo.components.stock.po.ResidueStockNumCondition;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.orm.BaseReadDAO;

public interface CommodityProductUnitWarehouseStockReadDAO extends BaseReadDAO<CommodityProductUnitWarehouseStockPO>{
	/**
	 * 根据skuid查询pu库存
	 */
	List<CommodityProductUnitWarehouseStockPO> findByPUId(@Param("ids")List<Long> commodityProductUnitIds);
	/**
	 * 根据puid查询pu库存信息(真实库存-冻结库存)
	 * @param id
	 * @return
	 */
	Long realStockNumByCommodityProductUnitId(@Param("commodityProductUnitId")Long commodityProductUnitId);
	/**
	 * 根据puid查询pu真实库存信息
	 * @param commodityProductUnitId
	 * @return
	 */
	Long findByCommodityProductUnitId(@Param("commodityProductUnitId")Long commodityProductUnitId);
	/**
	 * 根据puid查询pu库存信息
	 * @param puId
	 * @return
	 */
	CommodityProductUnitWarehouseStockPO findCommodityProductUnitWarehouseStockByPuId(@Param("puId")Long puId);
	/**
	 * 根据suId查询剩余库存数量
	 * @param standardUnitId
	 * @return
	 */
	Long residueStockNumByStandardUnitId(@Param("standardUnitId")Long standardUnitId);

	List<ResidueStockNumCondition> residueStockNumByStandardUnitIds(@Param("ids")List<Long> standardUnitIds);
	/**
	 * 根据puId集合查询剩余库存数量
	 * @param skuId
	 * @return
	 */
	Long residueStockNumByPuId(@Param("ids")List<Long> commodityProductUnitIds);
	/**
	 * 根据Skuid集合查询存在的sku库存信息
	 * @param skuIds
	 * @return
	 */
	List<Long> findIsSkuIdsBySkuIds(@Param("ids")List<Long> skuIds);
	/**
	 * 根据puId集合查询pu库存为0的数量
	 * @param puIds
	 * @return
	 */
	Integer findPuSellOutSumByPuIds(@Param("ids")List<Long> puIds);
	
}
	