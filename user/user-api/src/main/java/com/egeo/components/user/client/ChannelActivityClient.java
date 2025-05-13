package com.egeo.components.user.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.ChannelActivityDTO;


@FeignClient(name = "service-user-fgj",contextId="ChannelActivityClient")
public interface ChannelActivityClient {

	@RequestMapping(value = { "/client/user/channelActivity/findChannelActivityById" }, method = { RequestMethod.POST }) 
	public ChannelActivityDTO findChannelActivityById(ChannelActivityDTO dto); 
} 
 
