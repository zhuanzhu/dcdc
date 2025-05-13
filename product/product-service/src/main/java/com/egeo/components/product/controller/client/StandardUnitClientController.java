package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardUnitClientClient;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.service.read.StandardUnitClientReadService;
import com.egeo.components.product.service.write.StandardUnitClientWriteService;

@Controller
@RequestMapping("/client/product/standardUnitClient") 
public class StandardUnitClientController implements StandardUnitClientClient{ 

	@Autowired
	private StandardUnitClientReadService standardUnitClientReadService;
	@Autowired
	private StandardUnitClientWriteService standardUnitClientWriteService;


	@Override
	@RequestMapping(value = "/findStandardUnitClientAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitClientDTO> findStandardUnitClientAll(@RequestBody StandardUnitClientDTO dto) {
		return standardUnitClientReadService.findStandardUnitClientAll(dto);
	} 
} 
