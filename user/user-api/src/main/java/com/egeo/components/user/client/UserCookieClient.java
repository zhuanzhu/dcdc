package com.egeo.components.user.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.UserCookieDTO;


@FeignClient(name = "service-user-fgj",contextId="UserCookieClient")
public interface UserCookieClient {

	@RequestMapping(value = { "/client/user/userCookie/deleteCookieUserbyUserIdWithTx" }, method = { RequestMethod.POST }) 
	public void deleteCookieUserbyUserIdWithTx(Long userId); 
 
 
	@RequestMapping(value = { "/client/user/userCookie/getbyUserId" }, method = { RequestMethod.POST }) 
	public UserCookieDTO getbyUserId(Long userId); 
 
 
}