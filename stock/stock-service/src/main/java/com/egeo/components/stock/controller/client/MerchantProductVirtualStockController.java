package com.egeo.components.stock.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;
import com.egeo.components.stock.service.write.MerchantProductVirtualStockWriteService;

@Controller
@RequestMapping("/client/stock/merchantProductVirtualStock") 
public class MerchantProductVirtualStockController implements MerchantProductVirtualStockClient{ 

	@Autowired
	private MerchantProductVirtualStockReadService merchantProductVirtualStockReadService;
	@Autowired
	private MerchantProductVirtualStockWriteService merchantProductVirtualStockWriteService;


	@Override
	@RequestMapping(value = "/saveMerchantProductVirtualStockDTO", method = { RequestMethod.POST })
	@ResponseBody
	public Long saveMerchantProductVirtualStockDTO(@RequestBody MerchantProductVirtualStockDTO merchantProductVirtualStockDTO) {
		return merchantProductVirtualStockWriteService.saveMerchantProductVirtualStockDTO(merchantProductVirtualStockDTO);
	} 
 
	@Override
	@RequestMapping(value = "/deductStockBySkuIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deductStockBySkuIdWithTx(@RequestParam("skuId") Long skuId,@RequestParam("count")  Long count) {
		return merchantProductVirtualStockWriteService.deductStockBySkuIdWithTx(skuId, count);
	} 
 
	@Override
	@RequestMapping(value = "/unfreezeStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int unfreezeStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("puCount") Integer puCount) {
		return merchantProductVirtualStockWriteService.unfreezeStockWithTx(skuId, puCount);
	} 
 
	@Override
	@RequestMapping(value = "/merchantProductVirtualStockBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public MerchantProductVirtualStockDTO merchantProductVirtualStockBySkuId(@RequestParam("skuId") Long skuId) {
		return merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(skuId);
	}

	@Override
	@RequestMapping(value = "/queryUnitStockBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public Long queryUnitStockBySkuId(@RequestParam("puId") Long puId) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockReadService.queryUnitStockBySkuId(puId);
	}

	@Override
	@RequestMapping(value = "/unfreezeAndDeductStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int unfreezeAndDeductStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("integer") Integer integer) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteService.unfreezeAndDeductStockWithTx(skuId, integer);
	}

	@Override
	@RequestMapping(value = "/freezeStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int freezeStockWithTx(@RequestParam("skuId") Long skuId, @RequestParam("puCount") Integer puCount) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteService.freezeStockWithTx(skuId, puCount);
	} 
 
}