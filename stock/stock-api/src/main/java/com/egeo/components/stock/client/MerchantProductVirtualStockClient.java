package com.egeo.components.stock.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="MerchantProductVirtualStockClient")
public interface MerchantProductVirtualStockClient {

	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/saveMerchantProductVirtualStockDTO" }, method = { RequestMethod.POST }) 
	public Long saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/deductStockBySkuIdWithTx" }, method = { RequestMethod.POST }) 
	public int deductStockBySkuIdWithTx(@RequestParam("skuId") Long skuId,@RequestParam("count")  Long count); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/unfreezeStockWithTx" }, method = { RequestMethod.POST }) 
	public int unfreezeStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("puCount") Integer puCount); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/merchantProductVirtualStockBySkuId" }, method = { RequestMethod.POST }) 
	public MerchantProductVirtualStockDTO merchantProductVirtualStockBySkuId(Long skuId); 

	
	/**
	 * 解冻unit库存并且扣除库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/unfreezeAndDeductStockWithTx" }, method = { RequestMethod.POST }) 
	public int unfreezeAndDeductStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("integer") Integer integer);
	

	/**
	 * 冻结unit库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/freezeStockWithTx" }, method = { RequestMethod.POST }) 
	public int freezeStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("puCount") Integer puCount);
	/**
	 * 查询unit实际库存
	 * @param puId
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/merchantProductVirtualStock/queryUnitStockBySkuId" }, method = { RequestMethod.POST })
	public Long queryUnitStockBySkuId(Long puId);
	
 
}