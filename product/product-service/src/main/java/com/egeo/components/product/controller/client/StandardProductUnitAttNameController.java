package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardProductUnitAttNameClient;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;

@Controller
@RequestMapping("/client/product/standardProductUnitAttName") 
public class StandardProductUnitAttNameController implements StandardProductUnitAttNameClient{ 

	@Autowired
	private StandardProductUnitAttNameReadService standardProductUnitAttValueNameService;
	


	@Override
	@RequestMapping(value = "/findStandardProductUnitAttNameAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(@RequestBody StandardProductUnitAttNameDTO dto) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueNameService.findStandardProductUnitAttNameAll(dto);
	}


} 
