package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StandardProductUnitDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardProductUnitClient")
public interface StandardProductUnitClient {

	@RequestMapping(value = { "/client/product/standardProductUnit/findStandardProductUnitById" }, method = { RequestMethod.POST }) 
	public StandardProductUnitDTO findStandardProductUnitById(StandardProductUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/standardProductUnit/findStandardProductUnitAll" }, method = { RequestMethod.POST }) 
	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto); 
	
	
	@RequestMapping(value = { "/client/product/standardProductUnit/querySpuBySuId" }, method = { RequestMethod.POST }) 
	public StandardProductUnitDTO querySpuBySuId(Long suId);
	 
}