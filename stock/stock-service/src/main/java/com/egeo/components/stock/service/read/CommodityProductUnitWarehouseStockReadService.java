package com.egeo.components.stock.service.read;


import java.io.Serializable;
import java.util.List;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.ResidueStockNumConditionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitWarehouseStockReadService extends Serializable{

	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockDTO dto);

	public PageResult<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockDTO dto,Pagination page);

	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockDTO dto);
	/**
	 * 根据puid查询pu库存信息(真实库存-冻结库存)
	 * @param id
	 * @return
	 */
	public Long realStockNumByCommodityProductUnitId(Long id);
	
	/**
	 * 根据puid查询pu库存信息
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockByPuId(Long puId);
	/**
	 * 设置pu商品库存的缓存
	 * @param productInventory 商品库存
	 */
	public void setCommodityProductUnitWarehouseStockCache(
			CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO);
	
	/**
	 * 获取pu商品库存的缓存
	 * @param productId
	 * @return
	 */
	CommodityProductUnitWarehouseStockDTO getCommodityProductUnitWarehouseStockCache(Long puId);
	/**
	 * 根据skuid查询pu库存
	 * @param commodityProductUnitIds
	 * @return
	 */
	public List<CommodityProductUnitWarehouseStockDTO> findByPUId(List<Long> commodityProductUnitIds);
	/**
	 * 根据suId查询剩余库存数量
	 * @param standardUnitId
	 * @return
	 */
	public Long residueStockNumByStandardUnitId(Long standardUnitId);

	List<ResidueStockNumConditionDTO> residueStockNumByStandardUnitIds(List<Long> standardUnitIds);

	/**
	 * 根据puId集合查询剩余库存数量
	 * @param skuId
	 * @return
	 */
	public Long residueStockNumByPuId(List<Long> commodityProductUnitIds);
	/**
	 * 根据Skuid集合查询存在的sku库存信息
	 * @param skuIds
	 * @return
	 */
	public List<Long> findIsSkuIdsBySkuIds(List<Long> skuIds);
	/**
	 * 根据puId集合查询pu库存为0的数量
	 * @param subList
	 * @return
	 */
	public Integer findPuSellOutSumByPuIds(List<Long> puIds);

}
	