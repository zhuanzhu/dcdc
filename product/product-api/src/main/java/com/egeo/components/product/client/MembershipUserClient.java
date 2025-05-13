package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.MembershipUserDTO;


@FeignClient(name = "service-product-fgj",contextId="MembershipUserClient")
public interface MembershipUserClient {

	@RequestMapping(value = { "/client/product/membershipUser/giveUserMembershipByOrder" }, method = { RequestMethod.POST }) 
	public void giveUserMembershipByOrder(@RequestParam("userId") Long userId, @RequestParam("platformId") Long platformId, @RequestParam("skuId") Long skuId); 
 
 
	@RequestMapping(value = { "/client/product/membershipUser/insertMembershipUserWithTx" }, method = { RequestMethod.POST }) 
	public Long insertMembershipUserWithTx(MembershipUserDTO dto); 

	@RequestMapping(value = { "/client/product/membershipUser/findMembershipUserAll" }, method = { RequestMethod.POST }) 
	public List<MembershipUserDTO> findMembershipUserAll(MembershipUserDTO dto);
} 
 
