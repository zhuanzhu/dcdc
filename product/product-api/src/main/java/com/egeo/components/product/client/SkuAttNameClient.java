package com.egeo.components.product.client;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "service-product-fgj",contextId="SkuAttNameClient")
public interface SkuAttNameClient {

	 
	}