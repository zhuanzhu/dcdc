package com.egeo.components.product.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;

@FeignClient(name = "service-product-fgj",contextId="StandardProductUnitAttValueClient")
public interface StandardProductUnitAttValueClient {
	/**
	 * 根据skuid查询是否在app内使用
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/standardProductUnitAttValue/findMembershipAll" }, method = { RequestMethod.POST }) 
	public boolean isAppUseBySkuId(Long skuId);

	/**
	 * 根据skuid和属性id查询属性值Id
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/standardProductUnitAttValue/findMembershipAll" }, method = { RequestMethod.POST }) 
	public Long findAttValueIdBySkuIdAndAttNameId(@RequestParam("skuId") Long skuId,@RequestParam("attNameId") Long attNameId);
	
	

	@RequestMapping(value = { "/client/product/standardProductUnitAttValue/findStandardProductUnitAttValueAll" }, method = { RequestMethod.POST }) 
	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto);


	/**
	 * 根据spuid查询第三方对接参数值
	 * @param id
	 * @return
	 */

	@RequestMapping(value = { "/client/product/standardProductUnitAttValue/findThirdpartyAttBySpuId" }, method = { RequestMethod.POST }) 
	public int findThirdpartyAttBySpuId(Long id);
}
