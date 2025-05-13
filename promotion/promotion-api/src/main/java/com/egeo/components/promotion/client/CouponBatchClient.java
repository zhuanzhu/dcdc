package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.promotion.dto.CouponBatchDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponBatchClient")
public interface CouponBatchClient {

	@RequestMapping(value = { "/client/promotion/couponBatch/findCouponBatchById" }, method = { RequestMethod.POST }) 
	public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/couponBatch/findRegisterCouponBatchByCouponId" }, method = { RequestMethod.POST }) 
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponId(@RequestParam("couponIdList") List<String> couponIdList,@RequestParam("platformId") Long platformId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponBatch/findRegisterCouponBatchByCouponGroup" }, method = { RequestMethod.POST }) 
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponGroup(@RequestParam("couponIdList") List<String> couponIdList,@RequestParam("platformId") Long platformId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponBatch/hideCouponBatch" }, method = { RequestMethod.POST }) 
	public int hideCouponBatch(); 
 
	@RequestMapping(value = { "/client/promotion/couponBatch/findCouponBatchAllByBatchCode" }, method = { RequestMethod.POST }) 
	public List<CouponBatchDTO> findCouponBatchAllByBatchCode(String newBatchCode);
 
}