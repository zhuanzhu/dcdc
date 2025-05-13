package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.FreightRegulationClient;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.service.read.FreightRegulationReadService;

@Controller
@RequestMapping("/client/product/freightRegulation") 
public class FreightRegulationController implements FreightRegulationClient{ 

	@Autowired
	private FreightRegulationReadService freightRegulationReadService;


	@Override
	@RequestMapping(value = "/findMembershipAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<FreightRegulationDTO> findFreightRegulationAll(@RequestBody FreightRegulationDTO dto) {
		return freightRegulationReadService.findFreightRegulationAll(dto);
	} 
} 
