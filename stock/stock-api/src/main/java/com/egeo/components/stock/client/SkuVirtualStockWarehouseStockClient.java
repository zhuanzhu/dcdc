package com.egeo.components.stock.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="SkuVirtualStockWarehouseStockClient")
public interface SkuVirtualStockWarehouseStockClient {

	@RequestMapping(value = { "/client/stock/skuVirtualStockWarehouseStock/updateMerchantProductVirtualStockDTO" }, method = { RequestMethod.POST }) 
	public int updateMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO dto);
 

 
}