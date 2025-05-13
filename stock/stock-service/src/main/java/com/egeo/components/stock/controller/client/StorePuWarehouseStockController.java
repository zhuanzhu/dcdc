package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;
import com.egeo.components.stock.service.StorePuWarehouseStockService;
import com.egeo.components.stock.service.read.StorePuWarehouseStockReadService;
import com.egeo.components.stock.service.write.StorePuWarehouseStockWriteService;

@Controller
@RequestMapping("/client/stock/storePuWarehouseStock") 
public class StorePuWarehouseStockController implements StorePuWarehouseStockClient{ 

	@Autowired
	private StorePuWarehouseStockReadService storePuWarehouseStockReadService;
	@Autowired
	private StorePuWarehouseStockWriteService storePuWarehouseStockWriteService;
	@Autowired
	private StorePuWarehouseStockService storePuWarehouseStockService;

	@Override
	@RequestMapping(value = "/insertAllWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int insertAllWithTx(@RequestBody List<StorePuWarehouseStockDTO> list) {
		return storePuWarehouseStockWriteService.insertAllWithTx(list);
	} 
 
	@Override
	@RequestMapping(value = "/findStorePuWarehouseStockAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(@RequestBody StorePuWarehouseStockDTO dto) {
		return storePuWarehouseStockReadService.findStorePuWarehouseStockAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/realStockNumByStoreProductUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public Long realStockNumByStoreProductUnitId(@RequestParam("storeProductUnitId") Long storeProductUnitId,@RequestParam("storeId")  Long storeId) {
		return storePuWarehouseStockReadService.realStockNumByStoreProductUnitId(storeProductUnitId, storeId);
	} 
 
	@Override
	@RequestMapping(value = "/findByStorePuId", method = { RequestMethod.POST })
	@ResponseBody
	public StorePuWarehouseStockDTO findByStorePuId(@RequestBody  Long storePuId) {
		return storePuWarehouseStockReadService.findByStorePuId(storePuId);
	} 
 
	@Override
	@RequestMapping(value = "/findPuSellOutSumStoreByPuIds", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findPuSellOutSumStoreByPuIds(@RequestBody  List<String> storePuIds) {
		return storePuWarehouseStockReadService.findPuSellOutSumStoreByPuIds(com.egeo.utils.StringUtils.stringsToLongs(storePuIds));
	}

	@Override
	@RequestMapping(value = "/updateStorePuWarehouseStock", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean updateStorePuWarehouseStock(@RequestBody UpdateStorePuWarehouseStockDTO dto) {
		// TODO Auto-generated method stub
		return storePuWarehouseStockService.updateStorePuWarehouseStock(dto.getType(), dto.getSodto(), dto.getSoItems());
	} 
 
}