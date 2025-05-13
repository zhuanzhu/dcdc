package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MembershipAuthorityClient;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.service.read.MembershipAuthorityReadService;
import com.egeo.components.product.service.write.MembershipAuthorityWriteService;

@Controller
@RequestMapping("/client/product") 
public class MembershipAuthorityController implements MembershipAuthorityClient{ 

	@Autowired
	private MembershipAuthorityReadService membershipAuthorityReadService;
	@Autowired
	private MembershipAuthorityWriteService membershipAuthorityWriteService;


	@Override
	@RequestMapping(value = "/findMembershipAuthorityAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(@RequestBody MembershipAuthorityDTO dto) {
		// TODO Auto-generated method stub
		return membershipAuthorityReadService.findMembershipAuthorityAll(dto);
	}


	@Override
	@RequestMapping(value = "/findModifyYesterday", method = { RequestMethod.POST })
	@ResponseBody
	public List<MembershipAuthorityDTO> findModifyYesterday() {
		// TODO Auto-generated method stub
		return membershipAuthorityReadService.findModifyYesterday();
	} 
} 
