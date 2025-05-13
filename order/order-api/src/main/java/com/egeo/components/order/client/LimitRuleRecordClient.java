package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.order.dto.LimitRuleRecordDTO;


@FeignClient(name = "service-order-fgj",contextId="LimitRuleRecordClient")
public interface LimitRuleRecordClient {

	@RequestMapping(value = { "/client/order/limitRuleRecord/selectLimitStatistic" }, method = { RequestMethod.POST }) 
	LimitRuleRecordDTO selectLimitStatistic(@RequestParam("orderStatus") Integer orderStatus,@RequestParam("limitRuleId") Long limitRuleId, @RequestParam("standardUnitId") Long standardUnitId,
			@RequestParam("standardUnitIdList") List<String> standardUnitIdList,@RequestParam("userId")  Long userId,@RequestParam("storeId")  Long storeId, @RequestParam("companyId") Long companyId, @RequestParam("periodType") Integer periodType); 
 
 
	@RequestMapping(value = { "/client/order/limitRuleRecord/findByUserIdLimitRuleId" }, method = { RequestMethod.POST }) 
	public List<LimitRuleRecordDTO> findByUserIdLimitRuleId(@RequestParam("limitRuleId") Long limitRuleId,@RequestParam("userId")  Long userId, @RequestParam("platformId") Long platformId); 
 
 
	@RequestMapping(value = { "/client/order/limitRuleRecord/findByPeriodTypeUserIdLimitRuleId" }, method = { RequestMethod.POST }) 
	public List<LimitRuleRecordDTO> findByPeriodTypeUserIdLimitRuleId(@RequestParam("periodType") Integer periodType, @RequestParam("memberId") Long memberId, @RequestParam("limitRuleId") Long limitRuleId,
			@RequestParam("platformId") Long platformId); 
 
}
