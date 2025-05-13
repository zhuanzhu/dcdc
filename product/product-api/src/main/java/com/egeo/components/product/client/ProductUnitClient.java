package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.ProductUnitDTO;


@FeignClient(name = "service-product-fgj",contextId="ProductUnitClient")
public interface ProductUnitClient {

	@RequestMapping(value = { "/client/product/productUnit/findProductUnitById" }, method = { RequestMethod.POST }) 
	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto); 
 
	 
	} 
