package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponGroupStoreClient;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.service.read.CouponGroupStoreReadService;
import com.egeo.components.promotion.service.write.CouponGroupStoreWriteService;

@Controller
@RequestMapping("/client/promotion/couponGroupStore") 
public class CouponGroupStoreController implements CouponGroupStoreClient{ 

	@Autowired
	private CouponGroupStoreReadService couponGroupStoreReadService;
	@Autowired
	private CouponGroupStoreWriteService couponGroupStoreWriteService;


	@Override
	@RequestMapping(value = "/findCouponGroupStoreAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponGroupStoreDTO> findCouponGroupStoreAll(@RequestBody CouponGroupStoreDTO dto) {
		return couponGroupStoreReadService.findCouponGroupStoreAll(dto);
	} 
}  
