package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.StartLimitRuleByStandardUnitIdDTO;


@FeignClient(name = "service-order-fgj",contextId="LimitRuleClient")
public interface LimitRuleClient {

	@RequestMapping(value = { "/client/order/limitRule/startLimitRuleByStandardUnitId" }, method = { RequestMethod.POST }) 
	public List<String> startLimitRuleByStandardUnitId(StartLimitRuleByStandardUnitIdDTO dto); 
 
 
	@RequestMapping(value = { "/client/order/limitRule/startLimitRuleDTOByStandardUnitId" }, method = { RequestMethod.POST }) 
	public List<LimitRuleDTO> startLimitRuleDTOByStandardUnitId(@RequestParam("standardUnitId") Long standardUnitId, @RequestParam("suCombIdList") List<String> suCombIdList, @RequestParam("platformId") Long platformId); 
 
 
}