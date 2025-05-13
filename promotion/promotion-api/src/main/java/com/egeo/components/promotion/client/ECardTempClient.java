package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.ECardTempDTO;


@FeignClient(name = "service-promotion-fgj",contextId="ECardTempClient")
public interface ECardTempClient {

	@RequestMapping(value = { "/client/promotion/eCardTemp/insertECardTempWithTx" }, method = { RequestMethod.POST }) 
	public Long insertECardTempWithTx(ECardTempDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/eCardTemp/deleteECardTempWithTx" }, method = { RequestMethod.POST }) 
	public int deleteECardTempWithTx(ECardTempDTO dto); 
 
}
