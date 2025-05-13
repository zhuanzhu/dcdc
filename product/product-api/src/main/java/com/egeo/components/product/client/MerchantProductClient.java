package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.FindMerchantProductOfPageDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-product-fgj",contextId="MerchantProductClient")
public interface MerchantProductClient {

	@RequestMapping(value = { "/client/product/merchantProduct/findMerchantProductById" }, method = { RequestMethod.POST }) 
	public MerchantProductDTO findMerchantProductById(MerchantProductDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/merchantProduct/findMerchantProductOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<MerchantProductDTO> findMerchantProductOfPage(FindMerchantProductOfPageDTO dto); 
	 
	} 
 
