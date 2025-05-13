package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.service.read.CouponReadService;
import com.egeo.components.promotion.service.write.CouponWriteService;

@Controller
@RequestMapping("/client/promotion/coupon") 
public class CouponController implements CouponClient{ 

	@Autowired
	private CouponReadService couponReadService;
	@Autowired
	private CouponWriteService couponWriteService;


	@Override
	@RequestMapping(value = "/findCouponById", method = { RequestMethod.POST })
	@ResponseBody
	public CouponDTO findCouponById(@RequestBody CouponDTO dto) {
		return couponReadService.findCouponById(dto);
	} 
}
