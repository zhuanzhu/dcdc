package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.CouponUnitDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponUnitClient")
public interface CouponUnitClient {

	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponByPaySuccessWithTx" }, method = { RequestMethod.POST }) 
	public int updateCouponByPaySuccessWithTx(Long orderId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponUnitRemoveLock" }, method = { RequestMethod.POST }) 
    int updateCouponUnitRemoveLock(String oldUnitCode); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponUnitWithTx" }, method = { RequestMethod.POST }) 
	public int updateCouponUnitWithTx(CouponUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponByCancelOrderWithTx" }, method = { RequestMethod.POST }) 
	public int updateCouponByCancelOrderWithTx(Long orderId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponUnitLockedByCouponUnitId" }, method = { RequestMethod.POST }) 
	int updateCouponUnitLockedByCouponUnitId(Long couponUnitId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/insertCouponUnitWithTx" }, method = { RequestMethod.POST }) 
	public Long insertCouponUnitWithTx(CouponUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitOfAllByUser" }, method = { RequestMethod.POST }) 
	public List<CouponUnitDTO> findCouponUnitOfAllByUser(CouponUnitDTO couponUnitDTO); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitById" }, method = { RequestMethod.POST }) 
	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitByOrderId" }, method = { RequestMethod.POST }) 
	public Integer findCouponUnitByOrderId(Long orderId); 
 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitAll" }, method = { RequestMethod.POST }) 
	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto); 
 
	@RequestMapping(value = { "/client/promotion/couponUnit/deleteCouponUnitByParamWithTx" }, method = { RequestMethod.POST }) 
	public void deleteCouponUnitByParamWithTx(CouponUnitDTO unitDTO);
	
	
	@RequestMapping(value = { "/client/promotion/couponUnit/updateCouponUnitByParamWithTx" }, method = { RequestMethod.POST }) 
	public void updateCouponUnitByParamWithTx(CouponUnitDTO couponUnitDTO);
 
	
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitAllCount" }, method = { RequestMethod.POST }) 
	public Long findCouponUnitAllCount(CouponUnitDTO couponUnitDTO);
	
	@RequestMapping(value = { "/client/promotion/couponUnit/findCouponUnitAllByCouponUnitCode" }, method = { RequestMethod.POST }) 
	public List<CouponUnitDTO> findCouponUnitAllByCouponUnitCode(String oldUnitCode);
}