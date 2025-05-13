package com.egeo.components.finance.client;
import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-finance-fgj",contextId="SoFreezeFubiClient")
public interface SoFreezeFubiClient {

	@RequestMapping(value = { "/client/finance/soFreezeFubi/delBySoId" }, method = { RequestMethod.POST }) 
	public int delBySoId(Long soId); 
 
 
	@RequestMapping(value = { "/client/finance/soFreezeFubi/findSoFreezeBalanceBySoId" }, method = { RequestMethod.POST }) 
	public BigDecimal findSoFreezeBalanceBySoId(Long soId); 
 
 
}