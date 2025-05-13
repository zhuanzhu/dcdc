package com.egeo.components.user.client;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.user.dto.InfoDTO;


@FeignClient(name = "service-user-fgj",contextId="InfoClient")
public interface InfoClient {

	@RequestMapping(value = { "/client/user/info/findUserInfoForMsgBox" }, method = { RequestMethod.POST }) 
	public List<InfoDTO> findUserInfoForMsgBox(@RequestParam("userId") Long userId,@RequestParam("isRead")  Integer isRead,@RequestParam("type")  Integer type,@RequestParam("platformId")  Long platformId,@RequestParam("createTime")  Date createTime,@RequestParam("count")  Integer count); 
 
} 
