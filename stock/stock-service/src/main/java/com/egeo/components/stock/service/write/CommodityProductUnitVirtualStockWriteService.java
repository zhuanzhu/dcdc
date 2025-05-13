package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;


public interface CommodityProductUnitVirtualStockWriteService {

	public Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);

	public int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);

	public int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto);
}
	