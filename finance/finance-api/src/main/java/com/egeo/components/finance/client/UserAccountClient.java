package com.egeo.components.finance.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.finance.dto.UserAccountDTO;

@FeignClient(name = "service-finance-fgj",contextId="UserAccountClient")
public interface UserAccountClient {

	@RequestMapping(value = { "/client/finance/userAccount/updateBeforeDisableBalanceWithTx" }, method = { RequestMethod.POST })
	public int updateBeforeDisableBalanceWithTx(Long userId);

	

	@RequestMapping(value = { "/client/finance/userAccount/updateUserAccountDisabled" }, method = { RequestMethod.POST })
	public int updateUserAccountDisabled(@RequestParam("userId") Long userId,@RequestParam("disabled")  int disabled);

	

	@RequestMapping(value = { "/client/finance/userAccount/updateUserAccountWithTx" }, method = { RequestMethod.POST })
	public int updateUserAccountWithTx(UserAccountDTO dto);

	@RequestMapping(value = { "/client/finance/userAccount/queryUserAccountByUserId" }, method = { RequestMethod.POST })
	public List<UserAccountDTO> queryUserAccountByUserId(@RequestParam("userId") Long userId);
	
	
	@RequestMapping(value = { "/client/finance/userAccount/insertUserAccountWithTx" }, method = { RequestMethod.POST }) 
	public Long insertUserAccountWithTx(UserAccountDTO dto); 
 
 
	@RequestMapping(value = { "/client/finance/userAccount/updateAccountNameByUserId" }, method = { RequestMethod.POST }) 
	public void updateAccountNameByUserId(@RequestParam("id") String id,@RequestParam("mail")  String mail); 

	
	
	@RequestMapping(value = { "/client/finance/userAccount/queryUserAccountByUserIdAndType" }, method = { RequestMethod.POST })
	public UserAccountDTO queryUserAccountByUserIdAndType(@RequestParam("id") Long id, @RequestParam("type") Integer type);

	@RequestMapping(value = { "/client/finance/userAccount/findUserAccountAll" }, method = { RequestMethod.POST })
	public List<UserAccountDTO> findUserAccountAll(UserAccountDTO dto);

	
	
	
/*
	@RequestMapping(value = { "/client/pay/salt/insertSaltWithTx" }, method = { RequestMethod.POST })
	public Long insertSaltWithTx(SaltDTO saltDTO);*/
	
}
