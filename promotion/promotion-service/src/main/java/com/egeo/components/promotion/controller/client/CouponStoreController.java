package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponStoreClient;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.service.read.CouponStoreReadService;
import com.egeo.components.promotion.service.write.CouponStoreWriteService;

@Controller
@RequestMapping("/client/promotion/couponStore") 
public class CouponStoreController implements CouponStoreClient{ 

	@Autowired
	private CouponStoreReadService couponStoreReadService;
	@Autowired
	private CouponStoreWriteService couponStoreWriteService;


	@Override
	@RequestMapping(value = "/findCouponStoreAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponStoreDTO> findCouponStoreAll(@RequestBody CouponStoreDTO dto) {
		return couponStoreReadService.findCouponStoreAll(dto);
	} 
}  
