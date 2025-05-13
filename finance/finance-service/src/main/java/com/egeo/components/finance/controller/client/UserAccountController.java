package com.egeo.components.finance.controller.client;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.service.read.UserAccountReadService;
import com.egeo.components.finance.service.write.UserAccountWriteService;

@Controller
@RequestMapping("/client/finance/userAccount") 
public class UserAccountController implements UserAccountClient{ 

	@Autowired
	private UserAccountReadService userAccountReadService;
	@Autowired
	private UserAccountWriteService userAccountWriteService;
	@Resource(name = "account")
	private AccountManage accountManage;


	@Override
	@RequestMapping(value = "/updateUserAccountWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateUserAccountWithTx(@RequestBody UserAccountDTO dto) {
		return userAccountWriteService.updateUserAccountWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertUserAccountWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertUserAccountWithTx(@RequestBody UserAccountDTO dto) {
		return userAccountWriteService.insertUserAccountWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateAccountNameByUserId", method = { RequestMethod.POST })
	@ResponseBody	
	public void updateAccountNameByUserId(@RequestParam("id") String id,@RequestParam("mail")  String mail) {
		userAccountWriteService.updateAccountNameByUserId(id, mail);
	} 
 
	@Override
	@RequestMapping(value = "/queryUserAccountByUserIdAndType", method = { RequestMethod.POST })
	@ResponseBody
	public UserAccountDTO queryUserAccountByUserIdAndType(@RequestParam("id") Long id, @RequestParam("type") Integer type) {
		UserAccountDTO ua = accountManage.userAccount(id, type);
		
		
		
		return ua;
	} 
 
	@Override
	@RequestMapping(value = "/findUserAccountAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserAccountDTO> findUserAccountAll(@RequestBody UserAccountDTO dto) {
		return userAccountReadService.findUserAccountAll(dto);
	}

	@Override
	@RequestMapping(value = "/updateBeforeDisableBalanceWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateBeforeDisableBalanceWithTx(@RequestParam("userId") Long userId) {
		// TODO Auto-generated method stub
		return userAccountWriteService.updateBeforeDisableBalanceWithTx(userId);
	}

	@Override
	@RequestMapping(value = "/updateUserAccountDisabled", method = { RequestMethod.POST })
	@ResponseBody
	public int updateUserAccountDisabled(@RequestParam("userId") Long userId,@RequestParam("disabled")  int disabled) {
		// TODO Auto-generated method stub
		return userAccountWriteService.updateUserAccountDisabled(userId,disabled);
	}

	@Override
	@RequestMapping(value = "/queryUserAccountByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserAccountDTO> queryUserAccountByUserId(@RequestParam("userId") Long userId) {
		// TODO Auto-generated method stub
		return userAccountReadService.queryUserAccountByUserId(userId);
	} 
 
}