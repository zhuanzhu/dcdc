package com.egeo.components.stock.request;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;

/**
 * 重新加载商品库存的缓存
 * @author Administrator
 *
 */
public class ProductInventoryCacheRefreshRequest implements Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5774699876756663760L;
	/**
	 * 商品id
	 */
	private Long id;
	/**
	 * 商品库存Service
	 */
	private CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService;
	
	public ProductInventoryCacheRefreshRequest(Long commodityProductUnitId,
			CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService) {
		this.id = commodityProductUnitId;
		this.commodityProductUnitWarehouseStockReadService = commodityProductUnitWarehouseStockReadService;
	}
	
	@Override
	public void process() {
		// 从数据库中查询最新的商品库存数量
		CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(id);
		System.out.println("===========日志===========: 已查询到商品最新的库存数量，pu商品id=" + id + ", 商品库存数量=" + commodityProductUnitWarehouseStockDTO.getRealStockNum());  
		// 将最新的商品库存数量，刷新到redis缓存中去
		commodityProductUnitWarehouseStockReadService.setCommodityProductUnitWarehouseStockCache(commodityProductUnitWarehouseStockDTO);
		System.out.println("从数据库中查询最新的商品库存数量");
	}
	
	public Long getId() {
		return id;
	}
	
}
