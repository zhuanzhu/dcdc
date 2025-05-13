package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitCombinationClient")
public interface StandardUnitCombinationClient {

	@RequestMapping(value = { "/client/product/standardUnitCombination/findStandardUnitCombinationById" }, method = { RequestMethod.POST }) 
	public StandardUnitCombinationDTO findStandardUnitCombinationById(StandardUnitCombinationDTO dto); 
 
}