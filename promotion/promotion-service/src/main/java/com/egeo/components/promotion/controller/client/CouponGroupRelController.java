package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponGroupRelClient;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.service.read.CouponGroupRelReadService;
import com.egeo.components.promotion.service.write.CouponGroupRelWriteService;

@Controller
@RequestMapping("/client/promotion/couponGroupRel") 
public class CouponGroupRelController implements CouponGroupRelClient{ 

	@Autowired
	private CouponGroupRelReadService couponGroupRelReadService;
	@Autowired
	private CouponGroupRelWriteService couponGroupRelWriteService;


	@Override
	@RequestMapping(value = "/findCouponGroupRelAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponGroupRelDTO> findCouponGroupRelAll(@RequestBody CouponGroupRelDTO dto) {
		return couponGroupRelReadService.findCouponGroupRelAll(dto);
	} 
} 
