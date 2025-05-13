package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponGroupRelDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponGroupRelClient")
public interface CouponGroupRelClient {

	@RequestMapping(value = { "/client/promotion/couponGroupRel/findCouponGroupRelAll" }, method = { RequestMethod.POST }) 
	public List<CouponGroupRelDTO> findCouponGroupRelAll(CouponGroupRelDTO dto); 
} 
 
