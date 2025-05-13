package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.promotion.dto.FuCoinDTO;


@FeignClient(name = "service-promotion-fgj",contextId="FuCoinClient")
public interface FuCoinClient {

	@RequestMapping(value = { "/client/promotion/fuCoin/findFCoinByUserId" }, method = { RequestMethod.POST }) 
	public FuCoinDTO findFCoinByUserId(@RequestParam("memberId") Long memberId,@RequestParam("platformId")  Long platformId); 
 
}
