package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.ChannelActivityClient;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.write.ChannelActivityWriteService;

@Controller
@RequestMapping("/client/user/channelActivity") 
public class ChannelActivityController implements ChannelActivityClient{ 

	@Autowired
	private ChannelActivityReadService channelActivityReadService;
	@Autowired
	private ChannelActivityWriteService channelActivityWriteService;


	@Override
	@RequestMapping(value = "/findChannelActivityById", method = { RequestMethod.POST })
	@ResponseBody
	public ChannelActivityDTO findChannelActivityById(@RequestBody ChannelActivityDTO dto) {
		return channelActivityReadService.findChannelActivityById(dto);
	} 
}
