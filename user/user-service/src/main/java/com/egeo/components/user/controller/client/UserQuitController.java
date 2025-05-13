package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.UserQuitClient;
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.components.user.service.read.UserQuitReadService;
import com.egeo.components.user.service.write.UserQuitWriteService;

@Controller
@RequestMapping("/client/user/userQuit") 
public class UserQuitController implements UserQuitClient{ 

	@Autowired
	private UserQuitReadService userQuitReadService;
	@Autowired
	private UserQuitWriteService userQuitWriteService;


	@Override
	@RequestMapping(value = "/findUserQuitAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserQuitDTO> findUserQuitAll(@RequestBody UserQuitDTO dto) {
		return userQuitReadService.findUserQuitAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteUserQuitWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteUserQuitWithTx(@RequestBody UserQuitDTO dto) {
		return userQuitWriteService.deleteUserQuitWithTx(dto);
	} 
 
}