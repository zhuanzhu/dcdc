package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.MembershipDTO;


@FeignClient(name = "service-product-fgj",contextId="MembershipClient")
public interface MembershipClient {

	@RequestMapping(value = { "/client/product/membership/findMembershipAll" }, method = { RequestMethod.POST }) 
	public List<MembershipDTO> findMembershipAll(MembershipDTO dto); 

	@RequestMapping(value = { "/client/product/membership/findMembershipBySkuId" }, method = { RequestMethod.POST }) 
	public MembershipDTO findMembershipBySkuId(@RequestParam("skuId") Long skuId, @RequestParam("platformId") Long platformId);
	
	@RequestMapping(value = { "/client/product/membership/findNotifyMembership" }, method = { RequestMethod.POST }) 
	public List<MembershipDTO> findNotifyMembership(@RequestParam("remainDays") Integer remainDays);
	
	
} 
 
