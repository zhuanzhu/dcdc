package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MembershipClient;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.service.read.MembershipReadService;
import com.egeo.components.product.service.write.MembershipWriteService;

@Controller
@RequestMapping("/client/product/membership") 
public class MembershipController implements MembershipClient{ 

	@Autowired
	private MembershipReadService membershipReadService;
	@Autowired
	private MembershipWriteService membershipWriteService;


	@Override
	@RequestMapping(value = "/findMembershipAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MembershipDTO> findMembershipAll(@RequestBody MembershipDTO dto) {
		return membershipReadService.findMembershipAll(dto);
	}


	@Override
	@RequestMapping(value = "/findMembershipBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public MembershipDTO findMembershipBySkuId(@RequestParam("skuId") Long skuId, @RequestParam("platformId") Long platformId) {
		// TODO Auto-generated method stub
		return membershipReadService.findMembershipBySkuId(skuId, platformId);
	}


	@Override
	@RequestMapping(value = "/findNotifyMembership", method = { RequestMethod.POST })
	@ResponseBody
	public List<MembershipDTO> findNotifyMembership(@RequestParam("remainDays") Integer remainDays) {
		// TODO Auto-generated method stub
		return membershipReadService.findNotifyMembership(remainDays);
	} 
} 
