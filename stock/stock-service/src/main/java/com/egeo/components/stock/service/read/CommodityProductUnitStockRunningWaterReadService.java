package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitStockRunningWaterReadService {

	public CommodityProductUnitStockRunningWaterDTO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterDTO dto);

	public PageResult<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterDTO dto,Pagination page);

	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterDTO dto);

	List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(
			List<String> orderCodes);

	/**
	 * 共用库存专用，
	 * @param orderCodes
	 * @param status
	 * @return
	 */
	List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(
			List<String> orderCodes, Integer status);
}
	