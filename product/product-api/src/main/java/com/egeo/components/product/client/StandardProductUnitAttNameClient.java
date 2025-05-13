package com.egeo.components.product.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;

@FeignClient(name = "service-product-fgj",contextId="StandardProductUnitAttNameClient")
public interface StandardProductUnitAttNameClient {
	/**
	 * 根据skuid查询是否在app内使用
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/standardProductUnitAttName/findStandardProductUnitAttNameAll" }, method = { RequestMethod.POST }) 
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto);
/*
	*//**
	 * 根据skuid和属性id查询属性值Id
	 * @param skuId
	 * @return
	 *//*
	@RequestMapping(value = { "/client/product/standardProductUnitAttName/findMembershipAll" }, method = { RequestMethod.POST }) 
	public Long findAttValueIdBySkuIdAndAttNameId(@RequestParam("skuId") Long skuId,@RequestParam("attNameId") Long attNameId);*/
}
