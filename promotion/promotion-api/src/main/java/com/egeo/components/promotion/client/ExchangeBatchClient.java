package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.promotion.dto.ExchangeBatchDTO;


@FeignClient(name = "service-promotion-fgj",contextId="ExchangeBatchClient")
public interface ExchangeBatchClient {

	@RequestMapping(value = { "/client/promotion/exchangeBatch/findExchangeIdsByBatch" }, method = { RequestMethod.POST }) 
	public List<String> findExchangeIdsByBatch(ExchangeBatchDTO dto);
	
	@RequestMapping(value = { "/client/promotion/exchangeBatch/findExchangeActivityByOldCouponUnitId" }, method = { RequestMethod.POST }) 
	public List<String> findExchangeActivityByOldCouponUnitId(@RequestParam("batchId") Long batchId,@RequestParam("couponUnitStatus")  Integer couponUnitStatus);
}