package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-product-fgj",contextId="CardThirdpartyAttValueClient")
public interface CardThirdpartyAttValueClient {

	@RequestMapping(value = { "/client/product/cardThirdpartyAttValue/findCardTypeByStandardProductUnitId" }, method = { RequestMethod.POST }) 
	public Integer findCardTypeByStandardProductUnitId(Long standardProductUnitId); 
 
 
	 
	}