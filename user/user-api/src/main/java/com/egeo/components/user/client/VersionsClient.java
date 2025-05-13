package com.egeo.components.user.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.user.dto.VersionsDTO;


@FeignClient(name = "service-user-fgj",contextId="VersionsClient")
public interface VersionsClient {

	@RequestMapping(value = { "/client/user/versions/findVersionsById" }, method = { RequestMethod.POST }) 
	public VersionsDTO findVersionsById(VersionsDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/versions/queryVerisonByVersionCode" }, method = { RequestMethod.POST }) 
	public VersionsDTO queryVerisonByVersionCode(@RequestParam("vCode") Integer vCode,@RequestParam("type")  Integer type,@RequestParam("user")  Integer user,@RequestParam("platformId")  Long platformId); 
 
 
	@RequestMapping(value = { "/client/user/versions/queryLatestOfficialVersion" }, method = { RequestMethod.POST }) 
	public VersionsDTO queryLatestOfficialVersion(VersionsDTO dto); 
 
}
