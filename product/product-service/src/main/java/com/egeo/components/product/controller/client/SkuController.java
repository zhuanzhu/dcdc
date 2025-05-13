package com.egeo.components.product.controller.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.write.SkuWriteService;

@Controller
@RequestMapping("/client/product/sku") 
public class SkuController implements SkuClient{ 

	@Autowired
	private SkuReadService skuReadService;
	@Autowired
	private SkuWriteService skuWriteService;
	
	


	@Override
	@RequestMapping(value = "/findSkuById", method = { RequestMethod.POST })
	@ResponseBody
	public SkuDTO findSkuById(@RequestBody SkuDTO dto) {
		return skuReadService.findSkuById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findSkuECardBySkuSerialNumber", method = { RequestMethod.POST })
	@ResponseBody
	public SkuDTO findSkuECardBySkuSerialNumber(@RequestBody  String skuSerialNumber) {
		return skuReadService.findSkuECardBySkuSerialNumber(skuSerialNumber);
	} 
 
	@Override
	@RequestMapping(value = "/findSkuAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<SkuDTO> findSkuAll(@RequestBody SkuDTO dto) {
		return skuReadService.findSkuAll(dto);
	}

	@Override
	@RequestMapping(value = "/findSkuByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public SkuDTO findSkuByPuId(@RequestBody  Long puId) {
		// TODO Auto-generated method stub
		return skuReadService.findSkuByPuId(puId);
	}

	@Override
	@RequestMapping(value = "/queryStandardBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, String> queryStandardBySkuId(@RequestBody  Long skuId) {
		// TODO Auto-generated method stub
		return skuReadService.queryStandardBySkuId(skuId);
	}

	@Override
	public Map<Long, String> findSkuIdAndPreDaysByPreAttNameId(Long precautiousAttNameId) {
		// TODO Auto-generated method stub
		return skuReadService.findSkuIdAndPreDaysByPreAttNameId(precautiousAttNameId);
	} 
} 
