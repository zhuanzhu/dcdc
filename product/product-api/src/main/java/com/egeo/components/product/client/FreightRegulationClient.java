package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.FreightRegulationDTO;


@FeignClient(name = "service-product-fgj",contextId="FreightRegulationClient")
public interface FreightRegulationClient {

	@RequestMapping(value = { "/client/product/freightRegulation/findFreightRegulationAll" }, method = { RequestMethod.POST }) 
	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto); 
 
 
	 
}