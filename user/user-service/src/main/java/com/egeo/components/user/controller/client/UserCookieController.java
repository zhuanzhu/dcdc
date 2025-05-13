package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.UserCookieClient;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.service.read.UserCookieReadService;
import com.egeo.components.user.service.write.UserCookieWriteService;

@Controller
@RequestMapping("/client/user/userCookie") 
public class UserCookieController implements UserCookieClient{ 

	@Autowired
	private UserCookieReadService userCookieReadService;
	@Autowired
	private UserCookieWriteService userCookieWriteService;


	@Override
	@RequestMapping(value = "/deleteCookieUserbyUserIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void deleteCookieUserbyUserIdWithTx(@RequestBody  Long userId) {
		userCookieWriteService.deleteCookieUserbyUserIdWithTx(userId);
	} 
 
	@Override
	@RequestMapping(value = "/getbyUserId", method = { RequestMethod.POST })
	@ResponseBody
	public UserCookieDTO getbyUserId(@RequestBody  Long userId) {
		return userCookieReadService.getbyUserId(userId);
	} 
 
}