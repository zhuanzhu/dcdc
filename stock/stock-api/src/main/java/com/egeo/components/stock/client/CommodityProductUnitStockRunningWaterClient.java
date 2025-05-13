package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;


@FeignClient(name = "service-stock-fgj",contextId="CommodityProductUnitStockRunningWaterClient")
public interface CommodityProductUnitStockRunningWaterClient {

	@RequestMapping(value = { "/client/stock/commodityProductUnitStockRunningWater/findCommodityProductUnitStockRunningWaterByOrderCodes" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(List<String> orderCodes);
 

	@RequestMapping(value = { "/client/stock/commodityProductUnitStockRunningWater/insertBatchCommodityProductUnitStockRunningWaterWithTx" }, method = { RequestMethod.POST }) 
	public int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterDTO> dtos); 

	/**
	 * 共用库存专用，
	 * @param orderCodes
	 * @param status
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/commodityProductUnitStockRunningWater/findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus" }, method = { RequestMethod.POST })
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(@RequestParam("orderCodes") List<String> orderCodes, @RequestParam("status") Integer status);
 
}