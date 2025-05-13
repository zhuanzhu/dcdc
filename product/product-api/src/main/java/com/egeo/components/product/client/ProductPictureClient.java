package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.ProductPictureDTO;


@FeignClient(name = "service-product-fgj",contextId="ProductPictureClient")
public interface ProductPictureClient {

	@RequestMapping(value = { "/client/product/productPicture/findProductPictureAll" }, method = { RequestMethod.POST }) 
	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto); 
 
 
	 
	}