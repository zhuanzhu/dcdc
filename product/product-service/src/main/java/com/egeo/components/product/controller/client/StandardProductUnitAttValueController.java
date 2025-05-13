package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardProductUnitAttValueClient;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;

@Controller
@RequestMapping("/client/product/standardProductUnitAttValue") 
public class StandardProductUnitAttValueController implements StandardProductUnitAttValueClient{ 

	@Autowired
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;
	


	@Override
	@RequestMapping(value = "/isAppUseBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public boolean isAppUseBySkuId(@RequestBody  Long skuId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.isAppUseBySkuId(skuId);
	}

	@Override
	@RequestMapping(value = "/findAttValueIdBySkuIdAndAttNameId", method = { RequestMethod.POST })
	@ResponseBody
	public Long findAttValueIdBySkuIdAndAttNameId(@RequestParam("skuId") Long skuId,@RequestParam("attNameId") Long attNameId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.findAttValueIdBySkuIdAndAttNameId(skuId, attNameId);
	}

	@Override
	@RequestMapping(value = "/findStandardProductUnitAttValueAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(@RequestBody StandardProductUnitAttValueDTO dto) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(dto);
	}

	@Override
	@RequestMapping(value = "/findThirdpartyAttBySpuId", method = { RequestMethod.POST })
	@ResponseBody
	public int findThirdpartyAttBySpuId(@RequestBody Long id) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.findThirdpartyAttBySpuId(id);
	} 
} 
