package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.promotion.dto.CouponCompanyDTO;


@FeignClient(name = "service-promotion-fgj",contextId="CouponCompanyClient")
public interface CouponCompanyClient {

	@RequestMapping(value = { "/client/promotion/couponCompany/findCouponCompanyAll" }, method = { RequestMethod.POST }) 
	public List<CouponCompanyDTO> findCouponCompanyAll(CouponCompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/couponCompany/findCompanyByCouponIdOrCouponGroupId" }, method = { RequestMethod.POST }) 
	/**
	 * 通过优惠卷的id或优惠卷分组的id查询公司id
	 * @param couponId
	 * @param couponGroupId
	 * @return
	 */
	public List<String> findCompanyByCouponIdOrCouponGroupId(@RequestParam("couponId") Long couponId, @RequestParam("couponGroupId") Long couponGroupId, @RequestParam("officialList") List<String> officialList,
			@RequestParam("competitiveList") List<String> competitiveList, @RequestParam("testList") List<String> testList);
 
}