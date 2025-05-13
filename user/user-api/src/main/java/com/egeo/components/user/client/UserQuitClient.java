package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.UserQuitDTO;


@FeignClient(name = "service-user-fgj",contextId="UserQuitClient")
public interface UserQuitClient {

	@RequestMapping(value = { "/client/user/userQuit/findUserQuitAll" }, method = { RequestMethod.POST }) 
	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/userQuit/deleteUserQuitWithTx" }, method = { RequestMethod.POST }) 
	public int deleteUserQuitWithTx(UserQuitDTO dto); 
 
 
}