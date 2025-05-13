package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdProductInnerIdDTO;


@FeignClient(name = "service-product-fgj",contextId="JdProductInnerIdClient")
public interface JdProductInnerIdClient {

	@RequestMapping(value = { "/client/product/jdProductInnerId/findJdProductById" }, method = { RequestMethod.POST }) 
	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto);
 
 
	 
}