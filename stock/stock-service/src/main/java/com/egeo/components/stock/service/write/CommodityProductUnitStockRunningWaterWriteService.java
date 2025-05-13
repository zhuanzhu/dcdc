package com.egeo.components.stock.service.write;

import java.util.List;

import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;


public interface CommodityProductUnitStockRunningWaterWriteService {

	public Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);

	public int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);

	public int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);

	int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterDTO> dtos);
}
	