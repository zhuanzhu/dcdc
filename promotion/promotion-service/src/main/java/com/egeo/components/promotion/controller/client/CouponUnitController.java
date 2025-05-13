package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.service.read.CouponUnitReadService;
import com.egeo.components.promotion.service.write.CouponUnitWriteService;

@Controller
@RequestMapping("/client/promotion/couponUnit") 
public class CouponUnitController implements CouponUnitClient{ 

	@Autowired
	private CouponUnitReadService couponUnitReadService;
	@Autowired
	private CouponUnitWriteService couponUnitWriteService;


	@Override
	@RequestMapping(value = "/updateCouponByPaySuccessWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCouponByPaySuccessWithTx(@RequestBody  Long orderId) {
		return couponUnitWriteService.updateCouponByPaySuccessWithTx(orderId);
	} 
 
	@Override
	@RequestMapping(value = "/updateCouponUnitRemoveLock", method = { RequestMethod.POST })
	@ResponseBody
	public  int updateCouponUnitRemoveLock(@RequestBody  String oldUnitCode) {
		return couponUnitWriteService.updateCouponUnitRemoveLock(oldUnitCode);
	} 
 
	@Override
	@RequestMapping(value = "/updateCouponUnitWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCouponUnitWithTx(@RequestBody CouponUnitDTO dto) {
		return couponUnitWriteService.updateCouponUnitWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateCouponByCancelOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCouponByCancelOrderWithTx(@RequestBody  Long orderId) {
		return couponUnitWriteService.updateCouponByCancelOrderWithTx(orderId);
	} 
 
	@Override
	@RequestMapping(value = "/updateCouponUnitLockedByCouponUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCouponUnitLockedByCouponUnitId(@RequestBody  Long couponUnitId) {
		return couponUnitWriteService.updateCouponUnitLockedByCouponUnitId(couponUnitId);
	} 
 
	@Override
	@RequestMapping(value = "/insertCouponUnitWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCouponUnitWithTx(@RequestBody CouponUnitDTO dto) {
		return couponUnitWriteService.insertCouponUnitWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findCouponUnitOfAllByUser", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponUnitDTO> findCouponUnitOfAllByUser(@RequestBody CouponUnitDTO couponUnitDTO) {
		return couponUnitReadService.findCouponUnitOfAllByUser(couponUnitDTO);
	} 
 
	@Override
	@RequestMapping(value = "/findCouponUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public CouponUnitDTO findCouponUnitById(@RequestBody CouponUnitDTO dto) {
		return couponUnitReadService.findCouponUnitById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findCouponUnitByOrderId", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCouponUnitByOrderId(@RequestBody  Long orderId) {
		return couponUnitReadService.findCouponUnitByOrderId(orderId);
	} 
 
	@Override
	@RequestMapping(value = "/findCouponUnitAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponUnitDTO> findCouponUnitAll(@RequestBody CouponUnitDTO dto) {
		return couponUnitReadService.findCouponUnitAll(dto);
	}

	@Override
	@RequestMapping(value = "/deleteCouponUnitByParamWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void deleteCouponUnitByParamWithTx(@RequestBody CouponUnitDTO unitDTO) {
		// TODO Auto-generated method stub
		couponUnitWriteService.deleteCouponUnitByParamWithTx(unitDTO);
	}

	@Override
	@RequestMapping(value = "/updateCouponUnitByParamWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void updateCouponUnitByParamWithTx(@RequestBody CouponUnitDTO couponUnitDTO) {
		// TODO Auto-generated method stub
		couponUnitWriteService.updateCouponUnitByParamWithTx(couponUnitDTO);
	}

	@Override
	@RequestMapping(value = "/findCouponUnitAllCount", method = { RequestMethod.POST })
	@ResponseBody
	public Long findCouponUnitAllCount(@RequestBody CouponUnitDTO couponUnitDTO) {
		// TODO Auto-generated method stub
		return couponUnitReadService.findCouponUnitAllCount(couponUnitDTO);
	}

	@Override
	@RequestMapping(value = "/findCouponUnitAllByCouponUnitCode", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponUnitDTO> findCouponUnitAllByCouponUnitCode(@RequestBody  String oldUnitCode) {
		// TODO Auto-generated method stub
		return couponUnitReadService.findCouponUnitAllByCouponUnitCode(oldUnitCode);
	} 
 
}