package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.service.read.CommodityProductUnitStockRunningWaterReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitStockRunningWaterWriteService;

@Controller
@RequestMapping("/client/stock/commodityProductUnitStockRunningWater") 
public class CommodityProductUnitStockRunningWaterController implements CommodityProductUnitStockRunningWaterClient{ 

	@Autowired
	private CommodityProductUnitStockRunningWaterReadService commodityProductUnitStockRunningWaterReadService;
	@Autowired
	private CommodityProductUnitStockRunningWaterWriteService commodityProductUnitStockRunningWaterWriteService;


	@Override
	@RequestMapping(value = "/findCommodityProductUnitStockRunningWaterByOrderCodes", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(@RequestBody  List<String> orderCodes){
		return commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(orderCodes);
	} 
 
	@Override
	@RequestMapping(value = "/insertBatchCommodityProductUnitStockRunningWaterWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int insertBatchCommodityProductUnitStockRunningWaterWithTx(@RequestBody List<CommodityProductUnitStockRunningWaterDTO> dtos) {
		return commodityProductUnitStockRunningWaterWriteService.insertBatchCommodityProductUnitStockRunningWaterWithTx(dtos);
	}

	@Override
	@RequestMapping(value = "/findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(
			@RequestParam("orderCodes") List<String> orderCodes, @RequestParam("status") Integer status) {
		// TODO Auto-generated method stub
		return commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(orderCodes, status);
	} 
 
}