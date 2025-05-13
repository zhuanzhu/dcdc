package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ECardTempClient;
import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.components.promotion.service.read.ECardTempReadService;
import com.egeo.components.promotion.service.write.ECardTempWriteService;

@Controller
@RequestMapping("/client/promotion/eCardTemp") 
public class ECardTempController implements ECardTempClient{ 

	@Autowired
	private ECardTempReadService eCardTempReadService;
	@Autowired
	private ECardTempWriteService eCardTempWriteService;


	@Override
	@RequestMapping(value = "/insertECardTempWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertECardTempWithTx(@RequestBody ECardTempDTO dto) {
		return eCardTempWriteService.insertECardTempWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteECardTempWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteECardTempWithTx(@RequestBody ECardTempDTO dto) {
		return eCardTempWriteService.deleteECardTempWithTx(dto);
	} 
} 
