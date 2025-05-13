package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.PlatformDTO;


@FeignClient(name = "service-user-fgj",contextId="PlatformClient")
public interface PlatformClient {

	@RequestMapping(value = { "/client/user/platform/findAllPlatform" }, method = { RequestMethod.POST }) 
	public List<PlatformDTO> findAllPlatform(); 
	
	
	@RequestMapping(value = { "/client/user/platform/find" }, method = { RequestMethod.POST }) 
	public PlatformDTO find(Long id);
 
 
}