package com.egeo.components.stock.service;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;

/**
 * 请求异步执行的service
 * @author Administrator
 *
 */
public interface CommodityProductUnitWarehouseStockService {

	CommodityProductUnitWarehouseStockDTO findByProductUnitId(Long productUnitId);
	
	int updateCommodityProductUnitWarehouseStockDTO(CommodityProductUnitWarehouseStockDTO dto);
	
}
