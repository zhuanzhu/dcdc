package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ActivityClient;
import com.egeo.components.promotion.service.read.ActivityReadService;
import com.egeo.components.promotion.service.write.ActivityWriteService;

@Controller
@RequestMapping("/client/promotion/activity") 
public class ActivityController implements ActivityClient{ 

	@Autowired
	private ActivityReadService activityReadService;
	@Autowired
	private ActivityWriteService activityWriteService;


	@Override
	@RequestMapping(value = "/activityByMerchantProdIdAndDate", method = { RequestMethod.POST })
	@ResponseBody
	public boolean activityByMerchantProdIdAndDate(@RequestBody  Long merchantProdId) {
		return activityReadService.activityByMerchantProdIdAndDate(merchantProdId);
	} 
}
