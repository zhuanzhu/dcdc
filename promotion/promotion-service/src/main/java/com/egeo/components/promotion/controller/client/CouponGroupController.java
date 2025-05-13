package com.egeo.components.promotion.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponGroupClient;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.service.read.CouponGroupReadService;
import com.egeo.components.promotion.service.write.CouponGroupWriteService;

@Controller
@RequestMapping("/client/promotion/couponGroup") 
public class CouponGroupController implements CouponGroupClient{ 

	@Autowired
	private CouponGroupReadService couponGroupReadService;
	@Autowired
	private CouponGroupWriteService couponGroupWriteService;


	@Override
	@RequestMapping(value = "/findCouponGroupById", method = { RequestMethod.POST })
	@ResponseBody
	public CouponGroupDTO findCouponGroupById(@RequestBody CouponGroupDTO dto) {
		return couponGroupReadService.findCouponGroupById(dto);
	} 
}  
