package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponGroupDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponGroupClient")
public interface CouponGroupClient {

	@RequestMapping(value = { "/client/promotion/couponGroup/findCouponGroupById" }, method = { RequestMethod.POST }) 
	public CouponGroupDTO findCouponGroupById(CouponGroupDTO dto); 
 
 
}