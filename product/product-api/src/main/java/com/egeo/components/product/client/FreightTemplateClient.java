package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.FreightTemplateDTO;


@FeignClient(name = "service-product-fgj",contextId="FreightTemplateClient")
public interface FreightTemplateClient {

	@RequestMapping(value = { "/client/product/freightTemplate/findFreightTemplateAll" }, method = { RequestMethod.POST }) 
	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto); 
 
 
	 
}