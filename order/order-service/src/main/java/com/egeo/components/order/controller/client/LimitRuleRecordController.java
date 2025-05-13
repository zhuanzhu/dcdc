package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.LimitRuleRecordClient;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.service.write.LimitRuleRecordWriteService;

@Controller
@RequestMapping("/client/order/limitRuleRecord") 
public class LimitRuleRecordController implements LimitRuleRecordClient{ 

	@Autowired
	private LimitRuleRecordReadService limitRuleRecordReadService;
	@Autowired
	private LimitRuleRecordWriteService limitRuleRecordWriteService;


	@Override
	@RequestMapping(value = "/selectLimitStatistic", method = { RequestMethod.POST })
	@ResponseBody
	public LimitRuleRecordDTO selectLimitStatistic(@RequestParam("orderStatus") Integer orderStatus,@RequestParam("limitRuleId") Long limitRuleId, @RequestParam("standardUnitId") Long standardUnitId,
			@RequestParam("standardUnitIdList") List<String> standardUnitIdList,@RequestParam("userId")  Long userId,@RequestParam("storeId")  Long storeId, @RequestParam("companyId") Long companyId, @RequestParam("periodType") Integer periodType) {
		return limitRuleRecordReadService.selectLimitStatistic(orderStatus, limitRuleId, standardUnitId, com.egeo.utils.StringUtils.stringsToLongs(standardUnitIdList), userId, storeId, companyId, periodType);
	} 
 
	@Override
	@RequestMapping(value = "/findByUserIdLimitRuleId", method = { RequestMethod.POST })
	@ResponseBody
	public List<LimitRuleRecordDTO> findByUserIdLimitRuleId(@RequestParam("limitRuleId") Long limitRuleId,@RequestParam("userId")  Long userId, @RequestParam("platformId") Long platformId) {
		return limitRuleRecordReadService.findByUserIdLimitRuleId(limitRuleId, userId, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/findByPeriodTypeUserIdLimitRuleId", method = { RequestMethod.POST })
	@ResponseBody
	public List<LimitRuleRecordDTO> findByPeriodTypeUserIdLimitRuleId(@RequestParam("periodType") Integer periodType, @RequestParam("memberId") Long memberId, @RequestParam("limitRuleId") Long limitRuleId,
			@RequestParam("platformId") Long platformId){
		return limitRuleRecordReadService.findByPeriodTypeUserIdLimitRuleId(periodType, memberId, limitRuleId, platformId);
	} 
} 
