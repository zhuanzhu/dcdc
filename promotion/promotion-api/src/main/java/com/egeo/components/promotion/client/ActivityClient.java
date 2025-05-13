package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-promotion-fgj",contextId="ActivityClient")
public interface ActivityClient {

	@RequestMapping(value = { "/client/promotion/activity/activityByMerchantProdIdAndDate" }, method = { RequestMethod.POST }) 
	public boolean activityByMerchantProdIdAndDate(Long merchantProdId); 
}
 
