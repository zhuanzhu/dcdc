package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.LimitRuleClient;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.StartLimitRuleByStandardUnitIdDTO;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.components.order.service.write.LimitRuleWriteService;

@Controller
@RequestMapping("/client/order/limitRule") 
public class LimitRuleController implements LimitRuleClient{ 

	@Autowired
	private LimitRuleReadService limitRuleReadService;
	@Autowired
	private LimitRuleWriteService limitRuleWriteService;


	@Override
	@RequestMapping(value = "/startLimitRuleByStandardUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> startLimitRuleByStandardUnitId(@RequestBody StartLimitRuleByStandardUnitIdDTO dto) {
		return limitRuleReadService.startLimitRuleByStandardUnitId(dto.getStandardUnitId(), dto.getCompanyId(),dto.getCompanyAllId(), dto.getPlatformId(), dto.getUserId(),dto.getStoreId(),dto.getSuCombMap());
	} 
 
	@Override
	@RequestMapping(value = "/startLimitRuleDTOByStandardUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public List<LimitRuleDTO> startLimitRuleDTOByStandardUnitId(@RequestParam("standardUnitId") Long standardUnitId, @RequestParam("suCombIdList") List<String> suCombIdList, @RequestParam("platformId") Long platformId) {
		return limitRuleReadService.startLimitRuleDTOByStandardUnitId(standardUnitId, com.egeo.utils.StringUtils.stringsToLongs(suCombIdList), platformId);
	} 
} 
