package com.egeo.components.stock.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.SkuVirtualStockWarehouseStockClient;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.service.SkuVirtualStockWarehouseStockService;

@Controller
@RequestMapping("/client/stock/skuVirtualStockWarehouseStock") 
public class SkuVirtualStockWarehouseStockController implements SkuVirtualStockWarehouseStockClient{ 

	@Autowired
	private SkuVirtualStockWarehouseStockService skuVirtualStockWarehouseStockService;
	




	@Override
	@RequestMapping(value = "/updateMerchantProductVirtualStockDTO", method = { RequestMethod.POST })
	@ResponseBody
	public int updateMerchantProductVirtualStockDTO(@RequestBody MerchantProductVirtualStockDTO dto) {
		// TODO Auto-generated method stub
		return skuVirtualStockWarehouseStockService.updateMerchantProductVirtualStockDTO(dto);
	} 
 
 
}