package com.egeo.components.user.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-user-fgj",contextId="DownloadClient")
public interface DownloadClient {

	@RequestMapping(value = { "/client/user/download/refreshDownloadDailyDownloadCount" }, method = { RequestMethod.POST }) 
	public int refreshDownloadDailyDownloadCount(); 
 
 
}