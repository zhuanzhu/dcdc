package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StandardUnitClientDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitClientClient")
public interface StandardUnitClientClient {

	@RequestMapping(value = { "/client/product/standardUnitClient/findStandardUnitClientAll" }, method = { RequestMethod.POST }) 
	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto); 
	 
	} 
 
