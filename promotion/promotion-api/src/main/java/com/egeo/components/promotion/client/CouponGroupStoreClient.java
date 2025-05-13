package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponGroupStoreDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponGroupStoreClient")
public interface CouponGroupStoreClient {

	@RequestMapping(value = { "/client/promotion/couponGroupStore/findCouponGroupStoreAll" }, method = { RequestMethod.POST }) 
	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto); 
} 
 
