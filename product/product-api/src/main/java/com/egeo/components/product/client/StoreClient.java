package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StoreDTO;


@FeignClient(name = "service-product-fgj",contextId="StoreClient")
public interface StoreClient {

	@RequestMapping(value = { "/client/product/store/findStoreById" }, method = { RequestMethod.POST }) 
	public StoreDTO findStoreById(StoreDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/store/findStoreAll" }, method = { RequestMethod.POST }) 
	public List<StoreDTO> findStoreAll(StoreDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/store/findHeadStoreByStoreId" }, method = { RequestMethod.POST }) 
	public StoreDTO findHeadStoreByStoreId(Long storeId); 
 
	 
	}
