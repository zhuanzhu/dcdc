package com.egeo.components.promotion.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponClient")
public interface CouponClient {

	@RequestMapping(value = { "/client/promotion/coupon/findCouponById" }, method = { RequestMethod.POST }) 
	public CouponDTO findCouponById(CouponDTO dto); 
 
 
}