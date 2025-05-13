package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.components.stock.po.ResidueStockNumCondition;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitWarehouseStockReadManage {

	public CommodityProductUnitWarehouseStockPO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockPO po);

	public PageResult<CommodityProductUnitWarehouseStockPO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockPO po,Pagination page);

	public List<CommodityProductUnitWarehouseStockPO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockPO po);
	/**
	 * 根据skuid查询pu库存
	 * @param commodityProductUnitIds
	 * @return
	 */
	public List<CommodityProductUnitWarehouseStockPO> findByPUId(List<Long> commodityProductUnitIds);
	/**
	 * 根据puid查询pu库存信息(真实库存-冻结库存)
	 * @param id
	 * @return
	 */
	public Long realStockNumByCommodityProductUnitId(Long commodityProductUnitId);
	/**
	 * 根据puid查询pu真实库存信息
	 * @param commodityProductUnitId
	 * @return
	 */
	public Long findByCommodityProductUnitId(Long commodityProductUnitId);
	/**
	 * 根据puid查询pu库存信息
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitWarehouseStockPO findCommodityProductUnitWarehouseStockByPuId(Long puId);
	/**
	 * 根据suId查询剩余库存数量
	 * @param standardUnitId
	 * @return
	 */
	public Long residueStockNumByStandardUnitId(Long standardUnitId);

	List<ResidueStockNumCondition> residueStockNumByStandardUnitIds(List<Long> standardUnitIds);

	/**
	 * 根据puId集合查询剩余库存数量
	 * @param skuId
	 * @return
	 */
	public Long residueStockNumByPuId(List<Long> commodityProductUnitIds);
	/**
	 *根据Skuid集合查询存在的sku库存信息
	 * @param skuIds
	 * @return
	 */
	public List<Long> findIsSkuIdsBySkuIds(List<Long> skuIds);
	/**
	 * 根据puId集合查询pu库存为0的数量
	 * @param puIds
	 * @return
	 */
	public Integer findPuSellOutSumByPuIds(List<Long> puIds);
}
	