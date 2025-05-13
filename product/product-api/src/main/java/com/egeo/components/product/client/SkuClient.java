package com.egeo.components.product.client;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.SkuDTO;


@FeignClient(name = "service-product-fgj",contextId="SkuClient")
public interface SkuClient {

	/**
	 * 根据skuId查询规格信息
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/sku/queryStandardBySkuId" }, method = { RequestMethod.POST }) 
	public Map<String, String> queryStandardBySkuId(Long skuId);
	
	@RequestMapping(value = { "/client/product/sku/findSkuById" }, method = { RequestMethod.POST }) 
	public SkuDTO findSkuById(SkuDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/sku/findSkuECardBySkuSerialNumber" }, method = { RequestMethod.POST }) 
	public SkuDTO findSkuECardBySkuSerialNumber(String skuSerialNumber); 
 
 
	@RequestMapping(value = { "/client/product/sku/findSkuAll" }, method = { RequestMethod.POST }) 
	public List<SkuDTO> findSkuAll(SkuDTO dto);
	
	@RequestMapping(value = { "/client/product/sku/findSkuByPuId" }, method = { RequestMethod.POST })
	public SkuDTO findSkuByPuId(Long puId);
	

	@RequestMapping(value = { "/client/product/sku/findSkuIdAndPreDaysByPreAttNameId" }, method = { RequestMethod.POST })
	public Map<Long, String> findSkuIdAndPreDaysByPreAttNameId(Long puId);
	
 
 
	 
	}