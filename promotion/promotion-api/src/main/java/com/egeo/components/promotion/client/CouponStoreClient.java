package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponStoreDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponStoreClient")
public interface CouponStoreClient {

	@RequestMapping(value = { "/client/promotion/couponStore/findCouponStoreAll" }, method = { RequestMethod.POST }) 
	public List<CouponStoreDTO> findCouponStoreAll(CouponStoreDTO dto); 
 
 

}