package com.egeo.components.product.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardUnitCombinationClient;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.service.read.StandardUnitCombinationReadService;
import com.egeo.components.product.service.write.StandardUnitCombinationWriteService;

@Controller
@RequestMapping("/client/product/standardUnitCombination") 
public class StandardUnitCombinationController implements StandardUnitCombinationClient{ 

	@Autowired
	private StandardUnitCombinationReadService standardUnitCombinationReadService;
	@Autowired
	private StandardUnitCombinationWriteService standardUnitCombinationWriteService;


	@Override
	@RequestMapping(value = "/findStandardUnitCombinationById", method = { RequestMethod.POST })
	@ResponseBody
	public StandardUnitCombinationDTO findStandardUnitCombinationById(@RequestBody StandardUnitCombinationDTO dto) {
		return standardUnitCombinationReadService.findStandardUnitCombinationById(dto);
	} 
} 
