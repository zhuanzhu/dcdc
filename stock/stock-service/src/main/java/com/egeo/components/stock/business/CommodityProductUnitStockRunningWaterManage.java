package com.egeo.components.stock.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitStockRunningWaterManage {

	public CommodityProductUnitStockRunningWaterDTO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterDTO dto);	

	public PageResult<Map<String, Object>> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterDTO dto,Pagination page);

	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterDTO dto);

	Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);

	int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);

	int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto);
}
	