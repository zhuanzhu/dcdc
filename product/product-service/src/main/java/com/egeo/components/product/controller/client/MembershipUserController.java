package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.service.read.MembershipUserReadService;
import com.egeo.components.product.service.write.MembershipUserWriteService;

@Controller
@RequestMapping("/client/product/membershipUser") 
public class MembershipUserController implements MembershipUserClient{ 

	@Autowired
	private MembershipUserReadService membershipUserReadService;
	@Autowired
	private MembershipUserWriteService membershipUserWriteService;


	@Override
	@RequestMapping(value = "/giveUserMembershipByOrder", method = { RequestMethod.POST })
	@ResponseBody
    public  void giveUserMembershipByOrder(@RequestParam("userId") Long userId, @RequestParam("platformId") Long platformId, @RequestParam("skuId") Long skuId) {
		membershipUserWriteService.giveUserMembershipByOrder(userId, platformId, skuId);
	} 
 
	@Override
	@RequestMapping(value = "/insertMembershipUserWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertMembershipUserWithTx(@RequestBody MembershipUserDTO dto) {
		return membershipUserWriteService.insertMembershipUserWithTx(dto);
	}

	@Override
	@RequestMapping(value = "/findMembershipUserAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MembershipUserDTO> findMembershipUserAll(@RequestBody MembershipUserDTO dto) {
		// TODO Auto-generated method stub
		return membershipUserReadService.findMembershipUserAll(dto);
	} 
} 
