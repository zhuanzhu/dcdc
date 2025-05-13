package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.MembershipAuthorityDTO;


@FeignClient(name = "service-product-fgj",contextId="MembershipAuthorityClient")
public interface MembershipAuthorityClient {

	@RequestMapping(value = { "/client/product/membershipAuthorityClient/findMembershipAuthorityAll" }, method = { RequestMethod.POST }) 
	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto); 

	@RequestMapping(value = { "/client/product/membershipAuthorityClient/findModifyYesterday" }, method = { RequestMethod.POST }) 
	public List<MembershipAuthorityDTO> findModifyYesterday(); 
	
	
}