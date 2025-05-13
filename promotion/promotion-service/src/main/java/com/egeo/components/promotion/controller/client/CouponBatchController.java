package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.service.write.CouponBatchWriteService;

@Controller
@RequestMapping("/client/promotion/couponBatch") 
public class CouponBatchController implements CouponBatchClient{ 

	@Autowired
	private CouponBatchReadService couponBatchReadService;
	@Autowired
	private CouponBatchWriteService couponBatchWriteService;


	@Override
	@RequestMapping(value = "/findCouponBatchById", method = { RequestMethod.POST })
	@ResponseBody
	public CouponBatchDTO findCouponBatchById(@RequestBody CouponBatchDTO dto) {
		return couponBatchReadService.findCouponBatchById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findRegisterCouponBatchByCouponId", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponId(@RequestParam("couponIdList") List<String> couponIdList,@RequestParam("platformId") Long platformId) {
		return couponBatchReadService.findRegisterCouponBatchByCouponId(com.egeo.utils.StringUtils.stringsToLongs(couponIdList), platformId);
	} 
 
	@Override
	@RequestMapping(value = "/findRegisterCouponBatchByCouponGroup", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponBatchDTO> findRegisterCouponBatchByCouponGroup(@RequestParam("couponIdList") List<String> couponIdList,@RequestParam("platformId") Long platformId) {
		return couponBatchReadService.findRegisterCouponBatchByCouponGroup(com.egeo.utils.StringUtils.stringsToLongs(couponIdList), platformId);
	} 
 
	@Override
	@RequestMapping(value = "/hideCouponBatch", method = { RequestMethod.POST })
	@ResponseBody
	public int hideCouponBatch() {
		return couponBatchWriteService.hideCouponBatch();
	}

	@Override
	@RequestMapping(value = "/findCouponBatchAllByBatchCode", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponBatchDTO> findCouponBatchAllByBatchCode(@RequestBody  String newBatchCode) {
		// TODO Auto-generated method stub
		return couponBatchReadService.findCouponBatchAllByBatchCode(newBatchCode);
	} 
 
}