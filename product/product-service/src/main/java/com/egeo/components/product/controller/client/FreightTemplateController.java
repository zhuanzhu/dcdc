package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.FreightTemplateClient;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.service.read.FreightTemplateReadService;
import com.egeo.components.product.service.write.FreightTemplateWriteService;

@Controller
@RequestMapping("/client/product/freightTemplate") 
public class FreightTemplateController implements FreightTemplateClient{ 

	@Autowired
	private FreightTemplateReadService freightTemplateReadService;
	@Autowired
	private FreightTemplateWriteService freightTemplateWriteService;


	@Override
	@RequestMapping(value = "/findFreightTemplateAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<FreightTemplateDTO> findFreightTemplateAll(@RequestBody FreightTemplateDTO dto) {
		return freightTemplateReadService.findFreightTemplateAll(dto);
	} 
} 
