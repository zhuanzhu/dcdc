package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.StandardUnitStoreDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitStoreClient")
public interface StandardUnitStoreClient {
	@RequestMapping(value = { "/client/product/standardUnitStore/findStandardUnitStoreAll" }, method = { RequestMethod.POST }) 
    public List<StandardUnitStoreDTO> findStandardUnitStoreAll(StandardUnitStoreDTO dto);
	
	
	@RequestMapping(value = { "/client/product/standardUnitStore/findStandardUnitStoreCount" }, method = { RequestMethod.POST }) 
	public int findStandardUnitStoreCount(@RequestParam("suId") Long suId, @RequestParam("storeId") Long storeId); 
 
 
	@RequestMapping(value = { "/client/product/standardUnitStore/standardUnitSumByStoreId" }, method = { RequestMethod.POST }) 
	public Integer standardUnitSumByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId); 
 
 
	@RequestMapping(value = { "/client/product/standardUnitStore/findByPuIdsByStoreId" }, method = { RequestMethod.POST }) 
	public List<String> findByPuIdsByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId); 
	 
}
 
