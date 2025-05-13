package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.UserRoleClient;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.service.read.UserRoleReadService;
import com.egeo.components.user.service.write.UserRoleWriteService;

@Controller
@RequestMapping("/client/user/userRole") 
public class UserRoleController implements UserRoleClient{ 

	@Autowired
	private UserRoleReadService userRoleReadService;
	@Autowired
	private UserRoleWriteService userRoleWriteService;


	@Override
	@RequestMapping(value = "/findUserRoleAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserRoleDTO> findUserRoleAll(@RequestBody UserRoleDTO dto) {
		return userRoleReadService.findUserRoleAll(dto);
	} 
} 
