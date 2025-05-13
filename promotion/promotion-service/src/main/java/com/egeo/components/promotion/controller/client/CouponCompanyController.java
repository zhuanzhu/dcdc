package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.CouponCompanyClient;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.service.read.CouponCompanyReadService;
import com.egeo.components.promotion.service.write.CouponCompanyWriteService;

@Controller
@RequestMapping("/client/promotion/couponCompany") 
public class CouponCompanyController implements CouponCompanyClient{ 

	@Autowired
	private CouponCompanyReadService couponCompanyReadService;
	@Autowired
	private CouponCompanyWriteService couponCompanyWriteService;


	@Override
	@RequestMapping(value = "/findCouponCompanyAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CouponCompanyDTO> findCouponCompanyAll(@RequestBody CouponCompanyDTO dto) {
		return couponCompanyReadService.findCouponCompanyAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyByCouponIdOrCouponGroupId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findCompanyByCouponIdOrCouponGroupId(@RequestParam("couponId") Long couponId, @RequestParam("couponGroupId") Long couponGroupId, @RequestParam("officialList") List<String> officialList,
			@RequestParam("competitiveList") List<String> competitiveList, @RequestParam("testList") List<String> testList){
		return com.egeo.utils.StringUtils.longsToStrings(couponCompanyReadService.findCompanyByCouponIdOrCouponGroupId(couponId, couponGroupId, com.egeo.utils.StringUtils.stringsToLongs(officialList), com.egeo.utils.StringUtils.stringsToLongs(competitiveList), com.egeo.utils.StringUtils.stringsToLongs(testList)));
	} 
}
