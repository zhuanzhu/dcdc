package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.PictureDTO;


@FeignClient(name = "service-product-fgj",contextId="PictureClient")
public interface PictureClient {

	@RequestMapping(value = { "/client/product/picture/findById" }, method = { RequestMethod.POST }) 
    PictureDTO findById(PictureDTO dto); 
 
 
	 
}