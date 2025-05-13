package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.service.read.MerchantProductWarehouseStockReadService;
import com.egeo.components.stock.service.write.MerchantProductWarehouseStockWriteService;

@Controller
@RequestMapping("/client/stock/merchantProductWarehouseStock") 
public class MerchantProductWarehouseStockController implements MerchantProductWarehouseStockClient{ 

	@Autowired
	private MerchantProductWarehouseStockReadService merchantProductWarehouseStockReadService;
	@Autowired
	private MerchantProductWarehouseStockWriteService merchantProductWarehouseStockWriteService;


	@Override
	@RequestMapping(value = "/findAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MerchantProductWarehouseStockDTO> findAll(@RequestBody MerchantProductWarehouseStockDTO dto) {
		return merchantProductWarehouseStockReadService.findAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/batchLockMerchantProductStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean batchLockMerchantProductStockWithTx(@RequestBody List<MerchantProductWarehouseStockDTO> mpsDtoList) {
		return merchantProductWarehouseStockWriteService.batchLockMerchantProductStockWithTx(mpsDtoList);
	} 
 
	@Override
	@RequestMapping(value = "/batchChangeStockStatusLockToRealWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean batchChangeStockStatusLockToRealWithTx(@RequestBody List<MerchantProductWarehouseStockDTO> mpsDtoList) {
		return merchantProductWarehouseStockWriteService.batchChangeStockStatusLockToRealWithTx(mpsDtoList);
	} 
 
	@Override
	@RequestMapping(value = "/batchUnlockItemsStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean batchUnlockItemsStockWithTx(@RequestBody List<MerchantProductWarehouseStockDTO> mpsDtoList) {
		return merchantProductWarehouseStockWriteService.batchUnlockItemsStockWithTx(mpsDtoList);
	} 
 
}