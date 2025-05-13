package com.egeo.components.product.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.CardThirdpartyAttValueClient;
import com.egeo.components.product.service.read.CardThirdpartyAttValueReadService;
import com.egeo.components.product.service.write.CardThirdpartyAttValueWriteService;

@Controller
@RequestMapping("/client/product/cardThirdpartyAttValue") 
public class CardThirdpartyAttValueController implements CardThirdpartyAttValueClient{ 

	@Autowired
	private CardThirdpartyAttValueReadService cardThirdpartyAttValueReadService;
	@Autowired
	private CardThirdpartyAttValueWriteService cardThirdpartyAttValueWriteService;


	@Override
	@RequestMapping(value = "/findCardTypeByStandardProductUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCardTypeByStandardProductUnitId(@RequestBody  Long standardProductUnitId) {
		return cardThirdpartyAttValueReadService.findCardTypeByStandardProductUnitId(standardProductUnitId);
	} 
}
