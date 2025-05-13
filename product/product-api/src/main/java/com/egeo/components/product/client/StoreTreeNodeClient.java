package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StoreTreeNodeDTO;


@FeignClient(name = "service-product-fgj",contextId="StoreTreeNodeClient")
public interface StoreTreeNodeClient {

	@RequestMapping(value = { "/client/product/storeTreeNode/findStoreTreeNodeAll" }, method = { RequestMethod.POST }) 
	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto); 
 
}
