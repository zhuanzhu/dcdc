package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.StoreProductUnitDTO;


@FeignClient(name = "service-product-fgj",contextId="StoreProductUnitClient")
public interface StoreProductUnitClient {

	@RequestMapping(value = { "/client/product/storeProductUnit/findStoreProductUnitAll" }, method = { RequestMethod.POST }) 
	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/storeProductUnit/findStorePuIdsByStoreId" }, method = { RequestMethod.POST }) 
	public List<String> findStorePuIdsByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId); 

	@RequestMapping(value = { "/client/product/storeProductUnit/findStoreProductUnitById" }, method = { RequestMethod.POST }) 
	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto);
	 
}
