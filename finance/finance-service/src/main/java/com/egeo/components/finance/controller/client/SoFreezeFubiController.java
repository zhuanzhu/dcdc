package com.egeo.components.finance.controller.client;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.service.read.SoFreezeFubiReadService;
import com.egeo.components.finance.service.write.SoFreezeFubiWriteService;

@Controller
@RequestMapping("/client/finance/soFreezeFubi") 
public class SoFreezeFubiController implements SoFreezeFubiClient{ 

	@Autowired
	private SoFreezeFubiReadService soFreezeFubiReadService;
	@Autowired
	private SoFreezeFubiWriteService soFreezeFubiWriteService;


	@Override
	@RequestMapping(value = "/delBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public int delBySoId(@RequestBody  Long soId) {
		return soFreezeFubiWriteService.delBySoId(soId);
	} 
 
	@Override
	@RequestMapping(value = "/findSoFreezeBalanceBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public BigDecimal findSoFreezeBalanceBySoId(@RequestBody Long soId) {
		return soFreezeFubiReadService.findSoFreezeBalanceBySoId(soId);
	} 
 
}