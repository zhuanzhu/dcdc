package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.MerchantDTO;


@FeignClient(name = "service-product-fgj",contextId="MerchantClient")
public interface MerchantClient {

	@RequestMapping(value = { "/client/product/merchant/findMerchantById" }, method = { RequestMethod.POST }) 
	public MerchantDTO findMerchantById(MerchantDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/merchant/findMerchantAll" }, method = { RequestMethod.POST }) 
	public List<MerchantDTO> findMerchantAll(MerchantDTO dto); 
	 
	} 
 
